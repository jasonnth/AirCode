package p005cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.api.JPushInterface.ErrorCode;
import p005cn.jpush.android.helpers.ConnectingHelper;
import p005cn.jpush.android.helpers.ServiceHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.proto.common.commands.IMRequest;
import p005cn.jpush.proto.common.commands.JRequest;
import p005cn.jpush.proto.common.commands.JResponse;
import p005cn.jpush.proto.common.commands.TagaliasRequest;
import p005cn.jpush.proto.common.imcommands.ImResponseHelper;

/* renamed from: cn.jpush.android.service.RequestingThread */
public class RequestingThread extends HandlerThread {
    private static final int MSG_REQUEST = 7401;
    private static final int MSG_REQUEST_TIMEOUT = 7403;
    private static final int MSG_RESPONSE = 7402;
    private static final int MSG_SEND_OLD_QUEUE = 7405;
    private static final int MSG_SENT_TIMEOUT = 7404;
    private static final int QUEUE_MAX_SIZE = 100;
    private static final String TAG = "RequestingThread";
    private static final int TIMEOUT_SENT = 10000;
    private Context mContext;
    private int mCurrentSid = 0;
    private boolean mLoggedIn = false;
    private Handler mMainHandler;
    private Handler mRequestHandler;
    /* access modifiers changed from: private */
    public Map<Long, Requesting> mRequestingCache = new ConcurrentHashMap();
    private Deque<Requesting> mRequestingQueue = new LinkedBlockingDeque();
    private Deque<Requesting> mSentQueue = new LinkedBlockingDeque();

    /* renamed from: cn.jpush.android.service.RequestingThread$MyHandler */
    private static class MyHandler extends Handler {
        private WeakReference<RequestingThread> mThread;

        public MyHandler(Looper looper, RequestingThread requestingThread) {
            super(looper);
            this.mThread = new WeakReference<>(requestingThread);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.m1428v(RequestingThread.TAG, "Handle msg - threadId:" + Thread.currentThread().getId());
            RequestingThread thread = (RequestingThread) this.mThread.get();
            if (thread == null) {
                Logger.m1416d(RequestingThread.TAG, "When received msg, the instance already gc.");
                return;
            }
            switch (msg.what) {
                case RequestingThread.MSG_REQUEST /*7401*/:
                    if (msg.obj == null) {
                        Logger.m1434ww(RequestingThread.TAG, "Unexpected - want to send null request.");
                        return;
                    } else {
                        thread.sendRequestInternal((JRequest) msg.obj, msg.arg1);
                        return;
                    }
                case RequestingThread.MSG_RESPONSE /*7402*/:
                    thread.handleResponseInternal(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION), msg.obj);
                    return;
                case RequestingThread.MSG_REQUEST_TIMEOUT /*7403*/:
                    Requesting requesting = (Requesting) thread.mRequestingCache.get((Long) msg.obj);
                    if (requesting == null) {
                        Logger.m1432w(RequestingThread.TAG, "Unexpected - not found request in cache.");
                        return;
                    } else {
                        thread.onRequestTimeout(requesting);
                        return;
                    }
                case RequestingThread.MSG_SENT_TIMEOUT /*7404*/:
                    Requesting requesting2 = (Requesting) thread.mRequestingCache.get((Long) msg.obj);
                    if (requesting2 == null) {
                        Logger.m1432w(RequestingThread.TAG, "Unexpected: no cached request when sent timeout.");
                        return;
                    } else {
                        thread.onSentTimeout(requesting2);
                        return;
                    }
                case RequestingThread.MSG_SEND_OLD_QUEUE /*7405*/:
                    thread.resendRequestingQueue();
                    return;
                default:
                    Logger.m1432w(RequestingThread.TAG, "Unhandled msg - " + msg.what);
                    return;
            }
        }
    }

    /* renamed from: cn.jpush.android.service.RequestingThread$Requesting */
    private static class Requesting {
        JRequest request;
        int timeout;
        int times = 0;

        public Requesting(JRequest request2, int timeout2) {
            this.request = request2;
            this.timeout = timeout2;
        }

        public void retryAgain() {
            this.timeout -= 10000;
            this.times++;
        }

        public String toString() {
            return "[Requesting] - timeout:" + this.timeout + ", times:" + this.times + ", request:" + this.request.toString();
        }
    }

    public RequestingThread(Context context, Handler mainHandler) {
        super(TAG);
        this.mContext = context;
        this.mMainHandler = mainHandler;
        start();
        this.mRequestHandler = new MyHandler(getLooper(), this);
    }

    public void run() {
        Logger.m1416d(TAG, "RequestingThread started - threadId:" + Thread.currentThread().getId());
        super.run();
    }

    public void handleResponse(long connection, Object obj) {
        ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mRequestHandler, MSG_RESPONSE, obj), connection);
    }

    public void sendRequest(JRequest request, int timeout) {
        Message.obtain(this.mRequestHandler, MSG_REQUEST, timeout, 0, request).sendToTarget();
    }

    public void onLoggedIn() {
        Logger.m1418dd(TAG, "Action - onLoggedIn");
        this.mLoggedIn = true;
        this.mCurrentSid = Configs.getSid();
        this.mRequestHandler.sendEmptyMessage(MSG_SEND_OLD_QUEUE);
    }

    public void onDisconnected() {
        Logger.m1430vv(TAG, "Action - onDisconnected");
        NetworkingClient.sCloseSoon.set(false);
        this.mCurrentSid = 0;
        this.mLoggedIn = false;
        restoreSentQueue();
    }

    public void quitThread() {
        if (VERSION.SDK_INT >= 18) {
            quitSafely();
        } else {
            quit();
        }
    }

    private void restoreSentQueue() {
        Logger.m1416d(TAG, "Action - restoreSentQueue - sentQueueSize:" + this.mSentQueue.size());
        this.mRequestHandler.removeMessages(MSG_SENT_TIMEOUT);
        while (true) {
            Requesting requesting = (Requesting) this.mSentQueue.pollLast();
            if (requesting != null) {
                this.mRequestingQueue.offerFirst(requesting);
            } else {
                printRequestingQueue();
                printRequestingCache();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleResponseInternal(long connection, Object obj) {
        JResponse response = (JResponse) obj;
        Logger.m1416d(TAG, "Action - handleResponse - connection:" + connection + ", response:" + response.toString());
        if (connection != NetworkingClient.sConnection.get()) {
            Logger.m1432w(TAG, "Response connection is out-dated. ");
        }
        Long rid = response.getHead().getRid();
        Requesting origin = dequeSentQueue(rid);
        if (origin == null) {
            Logger.m1432w(TAG, "Not found the request in SentQueue when response.");
        } else {
            rid = origin.request.getHead().getRid();
            endSentTimeout(rid);
        }
        Requesting requesting = (Requesting) this.mRequestingCache.get(rid);
        if (requesting != null) {
            endRequestTimeout(requesting);
        } else {
            Logger.m1432w(TAG, "Not found requesting in RequestingCache when response.");
        }
    }

    /* access modifiers changed from: private */
    public void onSentTimeout(Requesting requesting) {
        Logger.m1428v(TAG, "Action - onSentTimeout - " + requesting.toString());
        dequeSentQueue(requesting.request.getHead().getRid());
        if (requesting.timeout > 0) {
            if (this.mLoggedIn) {
                Logger.m1428v(TAG, "Retry to send request - " + requesting.toString());
                requesting.retryAgain();
                sendCommandWithLoggedIn(requesting);
            } else {
                Logger.m1428v(TAG, "Want retry to send but not logged in. Sent move to RequestingQueue");
                this.mRequestingQueue.offerFirst(requesting);
            }
            if (requesting.times >= 2) {
                this.mMainHandler.sendEmptyMessageDelayed(1005, 2000);
                return;
            }
            return;
        }
        onRequestTimeout(requesting);
    }

    /* access modifiers changed from: private */
    public void onRequestTimeout(Requesting requesting) {
        Logger.m1416d(TAG, "Action - onRequestTimeout - " + requesting.toString());
        int cmd = requesting.request.getCommand();
        Long rid = requesting.request.getHead().getRid();
        endRequestTimeout(requesting);
        switch (cmd) {
            case 2:
                return;
            case 10:
                onTagaliasTimeout(requesting.request);
                return;
            case 100:
                onImTimeoutToReceiver(((IMRequest) requesting.request).getIMProtocol().getCommand(), rid.longValue());
                return;
            default:
                Logger.m1416d(TAG, "Ignore other command timeout.");
                return;
        }
    }

    private void onTagaliasTimeout(JRequest request) {
        Logger.m1416d(TAG, "Action - onTagaliasTimeout");
        Intent tagAliasIntent = new Intent();
        tagAliasIntent.addCategory(JPush.PKG_NAME);
        tagAliasIntent.setAction(ServiceInterface.ACTION_TAG_ALIAS_TIMEOUT);
        tagAliasIntent.putExtra(ServiceInterface.EXTRA_TAGALIAS_CALLBACKCODE, ErrorCode.TIMEOUT);
        tagAliasIntent.putExtra(ServiceInterface.EXTRA_TAGALIAS_SEQID, request.getHead().getRid().longValue());
        this.mContext.sendBroadcast(tagAliasIntent);
    }

    /* access modifiers changed from: private */
    public void resendRequestingQueue() {
        Logger.m1416d(TAG, "Action - resendRequestingQueue - size:" + this.mRequestingQueue.size());
        printRequestingQueue();
        printRequestingCache();
        while (true) {
            Requesting requesting = (Requesting) this.mRequestingQueue.pollFirst();
            if (requesting == null) {
                return;
            }
            if (requesting.request.getCommand() == 2) {
                this.mRequestingQueue.remove(requesting);
                this.mRequestingCache.remove(requesting.request.getHead().getRid());
            } else {
                requesting.retryAgain();
                sendCommandWithLoggedIn(requesting);
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendRequestInternal(JRequest request, int timeout) {
        Logger.m1418dd(TAG, "Action - sendRequestInternal - connection:" + NetworkingClient.sConnection.get() + ", timeout:" + timeout + ", threadId:" + Thread.currentThread().getId());
        Logger.m1428v(TAG, request.toString());
        Long rid = request.getHead().getRid();
        boolean isImPushCommand = false;
        if (request.getCommand() == 100 && ServiceHelper.isImPushCommand(((IMRequest) request).getIMProtocol().getCommand())) {
            isImPushCommand = true;
        }
        Requesting requesting = new Requesting(request, timeout);
        if (!isImPushCommand) {
            this.mRequestingCache.put(rid, requesting);
        }
        if (timeout > 10000) {
            startRequestTimeout(requesting);
        }
        preProcessRequests(request);
        if (NetworkingClient.sCloseSoon.get() || !this.mLoggedIn) {
            Logger.m1424i(TAG, "Not logged in currently. Give up to send now.");
            this.mRequestingQueue.offerLast(requesting);
            return;
        }
        requesting.retryAgain();
        sendCommandWithLoggedIn(requesting);
    }

    private void sendCommandWithLoggedIn(Requesting requesting) {
        Logger.m1418dd(TAG, "Action - sendCommandWithLoggedIn");
        Logger.m1430vv(TAG, requesting.toString());
        JRequest request = requesting.request;
        Long rid = request.getHead().getRid();
        int cmd = request.getCommand();
        long juid = Configs.getUid();
        boolean isImPushReceived = false;
        Logger.m1418dd(TAG, "Request params - cmd:" + cmd + ", sid:" + this.mCurrentSid + ", juid:" + juid);
        switch (cmd) {
            case 2:
                PushProtocol.HbJPush(NetworkingClient.sConnection.get(), rid.longValue(), this.mCurrentSid, juid, ConnectingHelper.getIMLoginFlag());
                break;
            case 10:
                PushProtocol.TagAlias(NetworkingClient.sConnection.get(), rid.longValue(), this.mCurrentSid, juid, JPush.APP_KEY, ((TagaliasRequest) request).getAction());
                break;
            case 100:
                request.setSid(this.mCurrentSid);
                request.setJuid(juid);
                PushProtocol.IMProtocol(NetworkingClient.sConnection.get(), request.writeBodyAndToBytes(), 0);
                if (ServiceHelper.isImPushCommand(((IMRequest) request).getIMProtocol().getCommand())) {
                    isImPushReceived = true;
                    break;
                }
                break;
            default:
                Logger.m1432w(TAG, "Unprocessed request yet.");
                break;
        }
        if (!isImPushReceived) {
            enqueSentQueue(requesting);
            startSentTimeout(rid);
            return;
        }
        Logger.m1428v(TAG, "Don't need join the queue for push response");
    }

    private void preProcessRequests(JRequest request) {
        switch (request.getCommand()) {
            case 100:
                if (((IMRequest) request).getCommand() == 2) {
                    Configs.setImLoggedIn(false);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private synchronized void enqueSentQueue(Requesting requesting) {
        Logger.m1416d(TAG, "Action - enqueSentQueue");
        if (!isInSentQueue(requesting.request.getHead().getRid().longValue())) {
            this.mSentQueue.offerLast(requesting);
            printSentQueue();
        }
    }

    private synchronized Requesting dequeSentQueue(Long rid) {
        Requesting found;
        Logger.m1416d(TAG, "Action - dequeSentQueue");
        found = null;
        for (Requesting requesting : this.mSentQueue) {
            if (rid.longValue() == requesting.request.getHead().getRid().longValue()) {
                this.mSentQueue.remove(requesting);
                found = requesting;
            }
        }
        return found;
    }

    private boolean isInSentQueue(long responseRid) {
        for (Requesting requesting : this.mSentQueue) {
            if (requesting.request.getHead().getRid().longValue() == responseRid) {
                return true;
            }
        }
        return false;
    }

    private void startRequestTimeout(Requesting requesting) {
        Logger.m1428v(TAG, "Action - startRequestTimeout");
        this.mRequestHandler.sendMessageDelayed(Message.obtain(this.mRequestHandler, MSG_REQUEST_TIMEOUT, requesting.request.getHead().getRid()), (long) requesting.timeout);
    }

    private void endRequestTimeout(Requesting requesting) {
        Logger.m1428v(TAG, "Action - endRequestTimeout");
        Long rid = requesting.request.getHead().getRid();
        if (((Requesting) this.mRequestingCache.remove(rid)) == null) {
            Logger.m1432w(TAG, "Unexpected - failed to remove requesting from cache.");
        }
        this.mRequestingQueue.remove(requesting);
        this.mRequestHandler.removeMessages(MSG_REQUEST_TIMEOUT, rid);
    }

    private void startSentTimeout(Long rid) {
        Logger.m1416d(TAG, "Action - startSentTimeout");
        this.mRequestHandler.sendMessageDelayed(Message.obtain(this.mRequestHandler, MSG_SENT_TIMEOUT, rid), 9800);
    }

    private void endSentTimeout(Long rid) {
        Logger.m1416d(TAG, "Action - endSentTimeout - rid:" + rid);
        this.mRequestHandler.removeMessages(MSG_SENT_TIMEOUT, rid);
    }

    private void onImTimeoutToReceiver(int imCmd, long rid) {
        Logger.m1416d(TAG, "Action - onImTimeoutToReceiver, imCmd:" + imCmd);
        switch (imCmd) {
            case 1:
                ServiceHelper.resetPushStatus(JPush.mApplicationContext);
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean(ImResponseHelper.EXTRA_IM_TIMEOUT, true);
        bundle.putLong(ImResponseHelper.EXTRA_RID, rid);
        bundle.putInt("im_cmd", imCmd);
        AndroidUtil.sendBroadcast(this.mContext, ImResponseHelper.ACTION_IM_RESPONSE, bundle);
    }

    private void printSentQueue() {
        if (this.mSentQueue != null) {
            Logger.m1428v(TAG, "Action - printSentQueue - size:" + this.mSentQueue.size());
        }
    }

    private void printRequestingQueue() {
        int size = 0;
        if (this.mRequestingQueue != null) {
            size = this.mRequestingQueue.size();
        }
        Logger.m1428v(TAG, "Action - printRequestingQueue - size:" + size);
        int index = 0;
        for (Requesting requesting : this.mRequestingQueue) {
            index++;
            Logger.m1428v(TAG, index + "/" + size + " - " + requesting.toString());
        }
    }

    private void printRequestingCache() {
        int size = 0;
        if (this.mRequestingCache != null) {
            size = this.mRequestingCache.size();
        }
        Logger.m1428v(TAG, "Action - printRequestingCache - size:" + size);
        int index = 0;
        for (Requesting requesting : this.mRequestingCache.values()) {
            index++;
            Logger.m1428v(TAG, index + "/" + size + " - " + requesting.toString());
        }
    }
}
