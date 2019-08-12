package p005cn.jpush.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.support.p000v4.app.NotificationCompat;
import com.airbnb.android.airdate.AirDateConstants;
import com.miteksystems.misnap.params.SDKConstants;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.IDataShare.Stub;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.api.NotificationHelper;
import p005cn.jpush.android.data.JPushLocalNotification;
import p005cn.jpush.android.helpers.ConnectingHelper;
import p005cn.jpush.android.helpers.MultiStubHelper;
import p005cn.jpush.android.helpers.ServiceHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;
import p005cn.jpush.proto.common.commands.IMRequest;
import p005cn.jpush.proto.common.commands.JRequest;
import p005cn.jpush.proto.common.commands.TagaliasRequest;
import p005cn.jpush.proto.common.imcommands.ImBaseRequest;
import p005cn.jpush.proto.common.imcommands.ImResponseHelper;

/* renamed from: cn.jpush.android.service.PushService */
public class PushService extends Service {
    private static final int MSG_HEARTBEAT_TIMEOUT = 1022;
    private static final int MSG_KEEP_ALIVE_FORCE = 1004;
    public static final int MSG_KEEP_ALIVE_NOMAL = 1005;
    private static final int MSG_NETWORK_CONNECTED = 1006;
    private static final int MSG_NETWORK_DISCONNECTED = 1007;
    private static final int MSG_RESTART_CONN = 1011;
    public static final int MSG_SELF_STOP_HAPPEN = 1001;
    private static final int MSG_SELF_STOP_SOON = 1003;
    private static final int MSG_SHUTDOWN_CONN = 1010;
    public static final int MSG_TAGALIAS_RESPONSE = 1009;
    private static final String TAG = "PushService";
    public static boolean mAutoOpenWifi = false;
    public static boolean mIsGetGoogleAddress = false;
    public static long mainThreadId;
    private Stub mBinder = null;
    /* access modifiers changed from: private */
    public ExecutorService mConnectServcie;
    private int mDisconnectedTimes = 0;
    private MyHandler mHandler;
    private int mHeartbeatTimeoutTimes = 0;
    private long mLastRtcTime = 0;
    private boolean mLoggedIn = false;
    private NetworkingClient mNetworkingClient;
    /* access modifiers changed from: private */
    public RequestingThread mRequestingThread;
    private boolean mValidJPushRunning = true;

    /* renamed from: cn.jpush.android.service.PushService$MyHandler */
    private static class MyHandler extends Handler {
        private final WeakReference<PushService> mService;

        public MyHandler(PushService service) {
            this.mService = new WeakReference<>(service);
        }

        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.m1428v(PushService.TAG, "handleMessage:" + msg.toString());
            PushService service = (PushService) this.mService.get();
            if (service == null || service.mRequestingThread == null || !service.mRequestingThread.isAlive()) {
                Logger.m1432w(PushService.TAG, "The service already gc.");
                return;
            }
            Context context = service.getApplicationContext();
            switch (msg.what) {
                case 1001:
                    Logger.m1416d(PushService.TAG, "Service killed by itself due to self killed mode");
                    service.stopSelf();
                    ConnectingHelper.sendConnectionChanged(context, ConnectionState.disconnected);
                    return;
                case 1003:
                    service.stopSelf();
                    return;
                case 1004:
                    service.rtcKeepAlive(true);
                    return;
                case 1005:
                    service.rtcKeepAlive(false);
                    return;
                case 1006:
                    removeMessages(1011);
                    removeMessages(1010);
                    sendEmptyMessageDelayed(1011, 3000);
                    return;
                case 1007:
                    sendEmptyMessageDelayed(1010, 200);
                    return;
                case 1009:
                    service.mRequestingThread.handleResponse(NetworkingClient.sConnection.get(), msg.obj);
                    return;
                case 1010:
                    service.shutdownNetworkingClient(service.mConnectServcie);
                    return;
                case 1011:
                    service.restartThenHeartbeat();
                    return;
                case 1022:
                    service.onHeartbeatTimeout();
                    return;
                case NetworkingClient.MSG_ON_DISCONNECTED /*7301*/:
                    service.onDisconnected(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION));
                    return;
                case NetworkingClient.MSG_RESPONSE /*7302*/:
                    service.mRequestingThread.handleResponse(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION), msg.obj);
                    return;
                case NetworkingClient.MSG_HEARTBEAT_SUCCEED /*7303*/:
                    service.onHeartbeatSucceed(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION));
                    return;
                case NetworkingClient.MSG_ON_LOGGEDIN /*7304*/:
                    service.onLoggedIn(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION));
                    return;
                case NetworkingClient.MSG_LOGIN_FAILED /*7306*/:
                    service.onLoginFailed(msg.getData().getLong(NetworkingClient.EXTRA_CONNECTION), msg.arg2);
                    return;
                case NetworkingClient.MSG_CLOSE_CONN_RESUME /*7307*/:
                    NetworkingClient.sCloseSoon.set(false);
                    return;
                case ImResponseHelper.MSG_EVENT_NOTIFICATION_BACK /*7501*/:
                    service.mRequestingThread.sendRequest((JRequest) msg.obj, 0);
                    return;
                case ImResponseHelper.MSG_CHAT_MSG_SYNC_BACK /*7502*/:
                    service.mRequestingThread.sendRequest((JRequest) msg.obj, 0);
                    return;
                default:
                    Logger.m1428v(PushService.TAG, "Unexpected: unhandled msg - " + msg.what);
                    return;
            }
        }
    }

    public IBinder onBind(Intent arg0) {
        Logger.m1416d(TAG, "action - onBind");
        return this.mBinder;
    }

    public boolean onUnbind(Intent intent) {
        Logger.m1420e(TAG, "action - onUnBind");
        return super.onUnbind(intent);
    }

    public void onDestroy() {
        super.onDestroy();
        Logger.m1416d(TAG, "onDestroy - processId:" + Process.myPid());
        JPush.runInPushProcess = false;
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        AndroidUtil.unregisterPushReceiver(getApplicationContext());
        if (this.mRequestingThread != null) {
            this.mRequestingThread.quitThread();
        }
        if (!(this.mNetworkingClient == null || NetworkingClient.sConnection.get() == 0)) {
            this.mNetworkingClient.tryStop();
        }
        if (this.mConnectServcie != null && !this.mConnectServcie.isShutdown()) {
            shutdownNetworkingClient(this.mConnectServcie);
        }
    }

    public void onCreate() {
        Logger.m1424i(TAG, "onCreate()");
        Logger.m1428v(TAG, "Service main thread - threadId:" + Thread.currentThread().getId());
        JPush.runInPushProcess = true;
        this.mBinder = new MultiStubHelper(this);
        mainThreadId = Thread.currentThread().getId();
        ServiceInterface.setPushReceiverEnable(getApplicationContext(), true);
        super.onCreate();
        this.mHandler = new MyHandler(this);
        initService();
        resetAlarm();
        if (JPush.canLaunchedStoppedService) {
            startOtherAppService(0);
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.m1418dd(TAG, "onStartCommand - intent:" + intent + ", pkg:" + JPush.PKG_NAME + ", connection:" + NetworkingClient.sConnection.get());
        if (!this.mValidJPushRunning) {
            Logger.m1418dd(TAG, "onStartCommand - not valid JPush running - Should not be here.");
            this.mHandler.sendEmptyMessageDelayed(1003, 100);
        } else {
            Bundle bundle = null;
            String action = null;
            if (intent != null) {
                action = intent.getAction();
                bundle = intent.getExtras();
            }
            if (bundle != null) {
                Logger.m1428v(TAG, "Service bundle - " + bundle.toString());
            }
            if (!(action == null || bundle == null)) {
                handleServiceAction(action, bundle);
            }
        }
        return 1;
    }

    private void initService() {
        Logger.m1418dd(TAG, "Action: init PushService");
        if (JPush.init(getApplicationContext())) {
            this.mValidJPushRunning = AndroidUtil.checkValidRunning(getApplicationContext());
            if (!this.mValidJPushRunning) {
                Logger.m1434ww(TAG, "JPush running checked failed!");
                return;
            }
            this.mRequestingThread = new RequestingThread(getApplicationContext(), this.mHandler);
            ServiceHelper.checkAppkeyChanged(getApplicationContext());
            AndroidUtil.checkDeviceInfo(getApplicationContext());
        }
    }

    private void handleServiceAction(String action, Bundle bundle) {
        Logger.m1418dd(TAG, "Action - handleServiceAction - action:" + action);
        if (ImBaseRequest.ACTION_IM_REQUEST.equals(action)) {
            String json = bundle.getString(ImBaseRequest.EXTRA_IM_REQUEST);
            int imCmd = bundle.getInt("im_cmd");
            if (json == null || imCmd == 0) {
                Logger.m1422ee(TAG, "Unexpected - illegal IM request.");
                return;
            }
            IMRequest imRequest = ImBaseRequest.fromJson(json, imCmd).toProtocolBuffer(JPush.APP_KEY);
            int timeout = JPushConstants.ONE_MINUTE;
            if (imCmd == 3 || imCmd == 4) {
                timeout = 300000;
            }
            if (imCmd == 1) {
                if (ServiceInterface.isStoped(getApplicationContext())) {
                    Configs.setStopExecuted(getApplicationContext(), true);
                    Configs.setServiceStopedFlag(getApplicationContext(), 0);
                    restartNetworkingClient();
                } else {
                    Configs.setStopExecuted(getApplicationContext(), false);
                }
            }
            this.mRequestingThread.sendRequest(imRequest, timeout);
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_RTC.equals(action)) {
            AndroidUtil.acquiredWakelock(this);
            if (NetworkingClient.sConnection.get() == 0) {
                restartThenHeartbeat();
            } else {
                int delayTime = bundle.getInt(p005cn.jpush.android.JPushConstants.PushService.DELAY_TIME, 0);
                if (StringUtils.isEmpty(bundle.getString("rtc"))) {
                    this.mHandler.sendEmptyMessage(1005);
                } else if (delayTime == 0) {
                    this.mHandler.removeMessages(1005);
                    if (!this.mHandler.hasMessages(1004)) {
                        this.mHandler.sendEmptyMessage(1005);
                    }
                } else {
                    this.mHandler.removeMessages(1005);
                    this.mHandler.removeMessages(1004);
                    this.mHandler.sendEmptyMessageDelayed(1004, (long) delayTime);
                }
            }
            AndroidUtil.releaseWakelock();
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_CONNECTIVITY_CHANGE.equals(action)) {
            AndroidUtil.acquiredWakelock(this);
            String connectionState = bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_CONNECTIONSTATE);
            if (StringUtils.isEmpty(connectionState)) {
                Logger.m1416d(TAG, "Unexpected - connectionState cannot be null");
            } else if (connectionState.equals(ConnectionState.connected.name())) {
                Logger.m1416d(TAG, "Handle connected state.");
                if (NetworkingClient.sConnection.get() == 0) {
                    restartThenHeartbeat();
                } else {
                    this.mHandler.sendEmptyMessage(1006);
                }
            } else if (connectionState.equals(ConnectionState.disconnected.name())) {
                Logger.m1416d(TAG, "Handle disconnected state.");
            } else {
                Logger.m1416d(TAG, "Other connection state");
            }
            AndroidUtil.releaseWakelock();
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_INIT.equals(action)) {
            Configs.setServiceStopedFlag(getApplicationContext(), 0);
            if (NetworkingClient.sConnection.get() == 0) {
                restartNetworkingClient();
            } else {
                Logger.m1416d(TAG, "The networking client is connected. Give up.");
            }
        } else if ("cn.jpush.android.intent.STOPPUSH".equals(action)) {
            Configs.setServiceStopedFlag(getApplicationContext(), 1);
            handleStopPush(bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_APP), JPush.APP_KEY);
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_REPORT.equals(action)) {
            if (bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_REPORT) != null) {
            }
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_USER_GROUND.equals(action)) {
            int ground = bundle.getInt(p005cn.jpush.android.JPushConstants.PushService.PARAM_USER_GROUND, -1);
            if (ground == -1) {
                return;
            }
            if (ground == 1) {
                onForeground();
            } else {
                onBackground();
            }
        } else if (p005cn.jpush.android.JPushConstants.PushService.ACTION_ALIAS_TAGS.equals(action)) {
            if (NetworkingClient.sConnection.get() == 0) {
                restartNetworkingClient();
            }
            String alias = bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_ALIAS);
            String tags = bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_TAGS);
            long seqId = bundle.getLong(p005cn.jpush.android.JPushConstants.PushService.PARAM_SEQ_ID, 0);
            if (alias != null || tags != null) {
                JSONObject jsonTagalias = new JSONObject();
                if (alias != null) {
                    try {
                        jsonTagalias.put(p005cn.jpush.android.JPushConstants.PushService.PARAM_ALIAS, alias);
                    } catch (JSONException e) {
                        Logger.m1432w(TAG, "tagalias exception:" + e.getMessage());
                        return;
                    }
                }
                if (tags != null) {
                    jsonTagalias.put(p005cn.jpush.android.JPushConstants.PushService.PARAM_TAGS, tags);
                }
                TagaliasRequest tagaliasRequest = new TagaliasRequest(seqId, JPush.APP_KEY, jsonTagalias.toString());
                this.mRequestingThread.sendRequest(tagaliasRequest, SDKConstants.CAM_INIT_CAMERA);
            }
        } else if ("cn.jpush.android.intent.RESTOREPUSH".equals(action)) {
            Configs.setServiceStopedFlag(getApplicationContext(), 0);
            if (NetworkingClient.sConnection.get() == 0) {
                restartNetworkingClient();
            } else {
                Logger.m1416d(TAG, "The networking client is connected. Give up.");
            }
        } else if (!p005cn.jpush.android.JPushConstants.PushService.STOP_THREAD.equals(action) && !p005cn.jpush.android.JPushConstants.PushService.ACTION_REGISTER.equals(action)) {
            if (p005cn.jpush.android.JPushConstants.PushService.ACTION_MULTI_PROCESS.equals(action)) {
                handleActionForMultiType(bundle);
            } else {
                Logger.m1432w(TAG, "Unhandled service action - " + action);
            }
        }
    }

    private void handleActionForMultiType(Bundle bundle) {
        int multiType = bundle.getInt(p005cn.jpush.android.JPushConstants.PushService.PARAM_MULTI_TYPE);
        Logger.m1428v(TAG, "action type:" + multiType);
        switch (multiType) {
            case 1:
                Configs.setCustomBuilder(getApplicationContext(), bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_NOTI_BUILDER_id), bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_NOTI_BUILDER));
                return;
            case 2:
                int mNumber = bundle.getInt(p005cn.jpush.android.JPushConstants.PushService.PARAM_NOTI_MAXNUM);
                int oldMaxNumber = Configs.getNotificationMaxNum(getApplicationContext());
                if (mNumber < oldMaxNumber) {
                    Logger.m1428v(TAG, "decreaseNotification:" + (oldMaxNumber - mNumber));
                    NotificationHelper.decreaseNotificationQueue(getApplicationContext(), mNumber);
                }
                Configs.setNotificationMaxNum(getApplicationContext(), mNumber);
                return;
            case 3:
                Configs.setPushTime(getApplicationContext(), bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_PUSH_TIME));
                return;
            case 4:
                Configs.setSilencePushTime(getApplicationContext(), bundle.getString(p005cn.jpush.android.JPushConstants.PushService.PARAM_SILENCE_PUSH_TIME));
                return;
            case 5:
                Configs.setStopExecuted(getApplicationContext(), bundle.getBoolean(p005cn.jpush.android.JPushConstants.PushService.PARAM_PUSH_STOPPED));
                return;
            case 6:
                JPushLocalNotificationCenter.getInstance(getApplicationContext()).add(getApplicationContext(), (JPushLocalNotification) bundle.getSerializable(p005cn.jpush.android.JPushConstants.PushService.PARAM_LOCAL_NOTIFICATION));
                return;
            case 7:
                JPushLocalNotificationCenter.getInstance(getApplicationContext()).remove(getApplicationContext(), bundle.getLong(p005cn.jpush.android.JPushConstants.PushService.PARAM_LOCAL_NOTIFICATION_ID));
                return;
            case 8:
                JPushLocalNotificationCenter.getInstance(getApplicationContext()).clear(getApplicationContext());
                return;
            default:
                return;
        }
    }

    private void handleStopPush(String app, String sender) {
        try {
            Logger.m1416d(TAG, "Action: handleStopPush - appKey:" + sender);
            if (StringUtils.isEmpty(app) || StringUtils.isEmpty(sender)) {
                Logger.m1420e(TAG, String.format("handleStopPush app, sender error: %s %s", new Object[]{app, sender}));
                if (this.mHandler.hasMessages(1005)) {
                    Logger.m1428v(TAG, "remove message for MSG_KEEP_ALIVE_NORMAL");
                    this.mHandler.removeMessages(1005);
                }
                ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.disconnected);
                if (this.mNetworkingClient == null) {
                    Logger.m1432w(TAG, "mNetworkingClient is null");
                } else {
                    this.mNetworkingClient.tryStop();
                }
                this.mRequestingThread.onDisconnected();
                stopSelf();
                return;
            }
            if (this.mHandler.hasMessages(1005)) {
                Logger.m1428v(TAG, "remove message for MSG_KEEP_ALIVE_NORMAL");
                this.mHandler.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.disconnected);
            if (this.mNetworkingClient == null) {
                Logger.m1432w(TAG, "mNetworkingClient is null");
            } else {
                this.mNetworkingClient.tryStop();
            }
            this.mRequestingThread.onDisconnected();
            stopSelf();
        } catch (Exception e) {
            if (this.mHandler.hasMessages(1005)) {
                Logger.m1428v(TAG, "remove message for MSG_KEEP_ALIVE_NORMAL");
                this.mHandler.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.disconnected);
            if (this.mNetworkingClient == null) {
                Logger.m1432w(TAG, "mNetworkingClient is null");
            } else {
                this.mNetworkingClient.tryStop();
            }
            this.mRequestingThread.onDisconnected();
            stopSelf();
        } catch (Throwable th) {
            if (this.mHandler.hasMessages(1005)) {
                Logger.m1428v(TAG, "remove message for MSG_KEEP_ALIVE_NORMAL");
                this.mHandler.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.disconnected);
            if (this.mNetworkingClient == null) {
                Logger.m1432w(TAG, "mNetworkingClient is null");
            } else {
                this.mNetworkingClient.tryStop();
            }
            this.mRequestingThread.onDisconnected();
            stopSelf();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public void restartThenHeartbeat() {
        Logger.m1430vv(TAG, "Action - restartThenHeartbeat");
        if (isConnecting()) {
            Logger.m1418dd(TAG, "Is connecting now. Give up to restart.");
        } else if (!this.mLoggedIn || isTimeoutNeedRestart()) {
            this.mHandler.removeMessages(1011);
            this.mHandler.removeMessages(1005);
            restartNetworkingClient();
        } else {
            Logger.m1418dd(TAG, "Already logged in. Give up to restart.");
        }
    }

    private synchronized void restartNetworkingClient() {
        Logger.m1418dd(TAG, "Action - restartNetworkingClient, pid:" + Process.myPid());
        if (!AndroidUtil.isConnected(getApplicationContext())) {
            Logger.m1426ii(TAG, "No network connection. Give up to start connection thread.");
        } else {
            if (this.mConnectServcie != null && !this.mConnectServcie.isShutdown()) {
                Logger.m1416d(TAG, "Old connect service is not shutdown! ");
                shutdownNetworkingClient(this.mConnectServcie);
            }
            this.mConnectServcie = Executors.newSingleThreadExecutor();
            this.mNetworkingClient = new NetworkingClient(getApplicationContext(), this.mHandler, false);
            this.mConnectServcie.execute(this.mNetworkingClient);
        }
    }

    /* access modifiers changed from: private */
    public void rtcKeepAlive(boolean isForceRtc) {
        if (isForceRtc || System.currentTimeMillis() - this.mLastRtcTime >= 30000) {
            Logger.m1418dd(TAG, "Send heart beat");
            this.mHandler.removeMessages(1005);
            if (NetworkingClient.sCloseSoon.get() || !this.mLoggedIn) {
                Logger.m1428v(TAG, "socket is closed or push isn't login");
                return;
            }
            Long rid = Long.valueOf(Configs.getNextRid());
            PushProtocol.HbJPush(NetworkingClient.sConnection.get(), rid.longValue(), Configs.getSid(), Configs.getUid(), ConnectingHelper.getIMLoginFlag());
            if (!this.mHandler.hasMessages(1022)) {
                this.mHandler.sendEmptyMessageDelayed(1022, 10000);
                return;
            }
            return;
        }
        Logger.m1428v(TAG, "No need to rtc, Because it have succeed recently");
    }

    private void sendMsgKeepAliveForce(int delayTime) {
        Logger.m1416d(TAG, "rtc in " + delayTime + " later");
        this.mHandler.removeMessages(1005);
        this.mHandler.removeMessages(1004);
        this.mHandler.sendEmptyMessageDelayed(1004, (long) delayTime);
    }

    private void resetAlarm() {
        Logger.m1428v(TAG, "Action - resetAlarm");
        long interval = ((long) Configs.getHeartbeatInterval()) * 1000;
        ((AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM)).setInexactRepeating(0, System.currentTimeMillis() + interval, interval, PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0));
    }

    /* access modifiers changed from: private */
    public void onLoggedIn(long connection) {
        Logger.m1418dd(TAG, "Action - onLoggedIn - connection:" + connection);
        this.mLoggedIn = true;
        this.mDisconnectedTimes = 0;
        this.mHeartbeatTimeoutTimes = 0;
        ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.connected);
        this.mHandler.sendEmptyMessageDelayed(1005, 15000);
        this.mRequestingThread.onLoggedIn();
    }

    private void startOtherAppService(final long time) {
        Logger.m1416d(TAG, "Action - startOtherAppService");
        new Thread(new Runnable() {
            public void run() {
                try {
                    long localTime = System.currentTimeMillis() / 1000;
                    long lastLaunchTime = Configs.getLaunchedTime();
                    Logger.m1416d(PushService.TAG, "time now:" + localTime + ", last launched time:" + lastLaunchTime);
                    if (-1 == lastLaunchTime || Math.abs(localTime - lastLaunchTime) > time) {
                        List<ComponentName> serviceAll = AndroidUtil.getAllPushService(PushService.this.getApplicationContext());
                        Configs.setLaunchedTime(localTime);
                        int count = 0;
                        if (serviceAll != null) {
                            count = serviceAll.size();
                        }
                        for (int i = 0; i < count; i++) {
                            Intent serviceIntent = new Intent();
                            serviceIntent.setComponent((ComponentName) serviceAll.get(i));
                            if (VERSION.SDK_INT >= 12) {
                                serviceIntent.setFlags(32);
                            }
                            PushService.this.startService(serviceIntent);
                        }
                        return;
                    }
                    Logger.m1428v(PushService.TAG, "localTime - lastLaunchTime = " + (localTime - lastLaunchTime));
                } catch (SecurityException e) {
                    Logger.m1434ww(PushService.TAG, "Can't start other push services duo to security!");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void onLoginFailed(long connection, int respCode) {
        Logger.m1418dd(TAG, "onLoginFailed - connection:" + connection + ", respCode:" + respCode);
    }

    /* access modifiers changed from: private */
    public void onDisconnected(long connection) {
        boolean isStopped;
        Logger.m1418dd(TAG, "Action - onDisconnected - connection:" + connection);
        if (Configs.getServiceStoppedFlag(getApplicationContext()) > 0) {
            isStopped = true;
        } else {
            isStopped = false;
        }
        if (NetworkingClient.sConnection.get() != 0 || !isStopped) {
            this.mRequestingThread.onDisconnected();
            this.mLoggedIn = false;
            this.mHeartbeatTimeoutTimes = 0;
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), ConnectionState.disconnected);
            shutdownNetworkingClient(this.mConnectServcie);
            if (AndroidUtil.isConnected(getApplicationContext())) {
                retryConnect();
            }
            this.mDisconnectedTimes++;
            return;
        }
        Logger.m1416d(TAG, "push already stopped!!!");
    }

    /* access modifiers changed from: private */
    public void onHeartbeatSucceed(long connection) {
        Logger.m1418dd(TAG, "Action - onHeartbeatSucceed - connection:" + connection);
        if (connection != NetworkingClient.sConnection.get()) {
            Logger.m1416d(TAG, "Heartbeat succeed connection is already out-dated. Ignore it.");
            return;
        }
        if (this.mHandler.hasMessages(1022)) {
            this.mHandler.removeMessages(1022);
        }
        this.mLastRtcTime = System.currentTimeMillis();
        this.mHeartbeatTimeoutTimes = 0;
        periodTasks();
        if (JPush.canLaunchedStoppedService) {
            startOtherAppService(AirDateConstants.SECONDS_PER_HOUR);
        }
    }

    /* access modifiers changed from: private */
    public void onHeartbeatTimeout() {
        this.mHeartbeatTimeoutTimes++;
        Logger.m1418dd(TAG, "Action - onHeartbeatTimeout - timeoutTimes:" + this.mHeartbeatTimeoutTimes);
        Logger.m1430vv(TAG, "============================================================");
        if (isConnecting()) {
            Logger.m1418dd(TAG, "Is connecting now. Give up to retry.");
            this.mHandler.sendEmptyMessageDelayed(1005, 10000);
        } else if (!this.mLoggedIn || isTimeoutNeedRestart()) {
            wantStopAliveNetworking();
            retryConnect();
        } else {
            Logger.m1418dd(TAG, "Already logged in. Give up to retry.");
            this.mHandler.sendEmptyMessageDelayed(1005, 5000);
        }
    }

    private void retryConnect() {
        Logger.m1418dd(TAG, "Action - retryConnect - disconnectedTimes:" + this.mDisconnectedTimes);
        int chargedLever = AndroidUtil.getChargedStatus(getApplicationContext());
        int delayTime = (int) (Math.pow(2.0d, (double) this.mDisconnectedTimes) * 3.0d * 1000.0d);
        int heartbeatInterval = Configs.getHeartbeatInterval();
        if (delayTime > (heartbeatInterval * 1000) / 2) {
            delayTime = (heartbeatInterval * 1000) / 2;
        }
        if (this.mDisconnectedTimes >= 5 && chargedLever != 1) {
            Logger.m1416d(TAG, "Give up to retry connect.");
        } else if (!this.mHandler.hasMessages(1011)) {
            Logger.m1418dd(TAG, "onDisconnected and retry restart conn - delay:" + delayTime);
            this.mHandler.sendEmptyMessageDelayed(1011, (long) delayTime);
        } else {
            Logger.m1416d(TAG, "Already has MSG_RESTART_CONN");
        }
    }

    private void periodTasks() {
        JPushLocalNotificationCenter.getInstance(getApplicationContext()).onHeartBeat(getApplicationContext());
    }

    private void wantStopAliveNetworking() {
        if (this.mNetworkingClient != null) {
            this.mNetworkingClient.tryStop();
        }
    }

    /* access modifiers changed from: private */
    public void shutdownNetworkingClient(ExecutorService executor) {
        Logger.m1428v(TAG, "Action - shutdownNetworkingClient");
        if (executor != null) {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    executor.shutdownNow();
                    if (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                        Logger.m1428v(TAG, "Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Logger.m1428v(TAG, "current thread is interrupted by self");
                Thread.currentThread().interrupt();
            }
            Logger.m1428v(TAG, "Action - shutdownNetworkingClient - end");
        }
    }

    private boolean isTimeoutNeedRestart() {
        return this.mHeartbeatTimeoutTimes > 1;
    }

    private boolean isConnecting() {
        return NetworkingClient.sConnection.get() != 0 && !this.mLoggedIn;
    }

    private void onForeground() {
        Logger.m1416d(TAG, "Action - onForeground");
    }

    private void onBackground() {
        Logger.m1416d(TAG, "Action - onBackground");
    }
}
