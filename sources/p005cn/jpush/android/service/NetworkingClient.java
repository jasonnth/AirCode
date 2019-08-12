package p005cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.helpers.ConnectingHelper;
import p005cn.jpush.android.helpers.PushMessageProcessor;
import p005cn.jpush.android.helpers.ServiceHelper;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.proto.common.commands.AckNormal;
import p005cn.jpush.proto.common.commands.IMResponse;
import p005cn.jpush.proto.common.commands.JCommands;
import p005cn.jpush.proto.common.commands.JResponse;
import p005cn.jpush.proto.common.imcommands.IMProtocol;
import p005cn.jpush.proto.common.imcommands.ImResponseHelper;

/* renamed from: cn.jpush.android.service.NetworkingClient */
public class NetworkingClient implements Runnable {
    public static final String EXTRA_CONNECTION = "connection";
    public static final int MSG_CLOSE_CONN_RESUME = 7307;
    public static final int MSG_CONNECTION_CREATED = 7305;
    public static final int MSG_HEARTBEAT_SUCCEED = 7303;
    public static final int MSG_LOGIN_FAILED = 7306;
    public static final int MSG_ON_DISCONNECTED = 7301;
    public static final int MSG_ON_LOGGEDIN = 7304;
    public static final int MSG_RESPONSE = 7302;
    private static final String TAG = "NetworkingClient";
    public static AtomicBoolean sCloseSoon = new AtomicBoolean(false);
    public static AtomicLong sConnection = new AtomicLong(0);
    private Context mContext;
    private Handler mMainHandler;
    private boolean mRegisterOnly;
    private volatile boolean mWantStop = false;

    public NetworkingClient(Context context, Handler handler, boolean registerOnly) {
        this.mContext = context;
        this.mMainHandler = handler;
        this.mRegisterOnly = registerOnly;
    }

    public void tryStop() {
        Logger.m1418dd(TAG, "Action - tryStop - connection:" + sConnection.get());
        this.mWantStop = true;
        closeConnection();
    }

    public void run() {
        Logger.m1426ii(TAG, "Begin to run in ConnectingThread - id:" + Thread.currentThread().getId());
        sConnection.set(PushProtocol.InitConn());
        Logger.m1416d(TAG, "Created connection - " + sConnection.get());
        SisInfo sisInfo = ConnectingHelper.sendSis(this.mContext);
        if (sisInfo != null) {
            sisInfo.configure();
        }
        if (!ConnectingHelper.openConnection(this.mContext, sConnection.get(), sisInfo)) {
            closeConnection();
        } else if (Configs.isValidRegistered() || ConnectingHelper.register(this.mContext, sConnection.get(), this.mRegisterOnly)) {
            int loginResult = ConnectingHelper.login(this.mContext, sConnection.get());
            if (loginResult < 0) {
                closeConnection();
            } else if (loginResult > 0) {
                onLoginFailed(sConnection.get(), loginResult);
                closeConnection();
            } else if (0 != sConnection.get()) {
                onLoggedIn(sConnection.get());
                byte[] buf = new byte[8192];
                while (true) {
                    if (this.mWantStop) {
                        break;
                    }
                    Logger.m1418dd(TAG, "Network listening...");
                    int ret = PushProtocol.RecvPush(sConnection.get(), buf, JPushConstants.STOPED_RTC_RESTART);
                    Logger.m1418dd(TAG, "Received bytes - len:" + ret + ", connection:" + sConnection.get() + ", pkg:" + JPush.PKG_NAME);
                    if (0 != sConnection.get()) {
                        if (ret <= 0) {
                            if (ret != -994) {
                                Logger.m1418dd(TAG, "Exception received. Now break - ret:" + ret);
                                break;
                            }
                            Logger.m1416d(TAG, "Timeout in JNI. Do not break.");
                        } else {
                            receivedCommand(this.mContext, ret, buf);
                        }
                    } else {
                        Logger.m1434ww(TAG, "mConnection is reset to 0 when network listening. Break now.");
                        return;
                    }
                }
                if (this.mWantStop) {
                    Logger.m1418dd(TAG, "Break receiving by wantStop - connection:" + sConnection.get());
                }
                closeConnection();
            } else {
                Logger.m1432w(TAG, "mConnection is reset to 0 when state between login and onLoggedOn. Exit directly.");
            }
        } else {
            if (86400 == Configs.getHeartbeatInterval()) {
                Logger.m1428v(TAG, "Registe failed, stop the service!");
                this.mMainHandler.sendEmptyMessageDelayed(1001, 100);
            } else {
                Logger.m1428v(TAG, "Registe failed, close the connection!");
            }
            closeConnection();
        }
    }

    private void receivedCommand(Context context, int len, byte[] buf) {
        byte[] actualBuffer = new byte[len];
        System.arraycopy(buf, 0, actualBuffer, 0, len);
        JResponse response = JCommands.parseResponseInbound(actualBuffer);
        if (response == null) {
            Logger.m1420e(TAG, "Null response. Maybe unknown command. ");
            return;
        }
        Logger.m1418dd(TAG, "Action - receivedCommand - cmd:" + response.getCommand());
        Logger.m1430vv(TAG, response.toString());
        if (!isPushCommands(response)) {
            ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mMainHandler, MSG_RESPONSE, response), sConnection.get());
        }
        if (response.code != 0) {
            Logger.m1434ww(TAG, "Received error response - code:" + response.code + ", error:" + response.error);
            return;
        }
        switch (response.getCommand()) {
            case 3:
                PushMessageProcessor.parsePushMessage(context, this.mMainHandler, sConnection.get(), response);
                return;
            case 10:
                Logger.m1432w(TAG, "Should not exist - ignore tag alias response.");
                return;
            case 19:
                handleNormalAck(response, sConnection.get());
                return;
            case 100:
                ImResponseHelper.handleImResponse(this.mContext, this.mMainHandler, response, actualBuffer);
                return;
            default:
                Logger.m1432w(TAG, "Unhandled response command - " + response.getCommand());
                return;
        }
    }

    private void handleNormalAck(JResponse response, long connection) {
        int request = ((AckNormal) response).getRequestCommand();
        if (request == 2) {
            Logger.m1416d(TAG, "Heartbeat ack succeed! ");
            ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mMainHandler, MSG_HEARTBEAT_SUCCEED), connection);
        } else if (request == 10) {
            Logger.m1416d(TAG, "Tag alias ack succeed! ");
        } else {
            Logger.m1432w(TAG, "Unknown Ack request - cmd:" + request);
        }
    }

    private void onLoggedIn(long connection) {
        Logger.m1430vv(TAG, "Action - onLoggedIn - connection:" + connection);
        ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mMainHandler, MSG_ON_LOGGEDIN), connection);
    }

    private void onLoginFailed(long connection, int respCode) {
        Logger.m1428v(TAG, "Action - onLoginFailed - respCode:" + respCode);
        ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mMainHandler, MSG_LOGIN_FAILED), connection);
    }

    private void closeConnection() {
        Logger.m1418dd(TAG, "Action - closeConnection - connection:" + sConnection.get());
        if (0 != sConnection.get()) {
            try {
                sCloseSoon.set(true);
                sConnection.set((long) PushProtocol.Close(sConnection.get()));
                Logger.m1418dd(TAG, "Return of Close jni connection - " + sConnection.get());
                sCloseSoon.set(false);
            } catch (Exception e) {
                Logger.m1433w(TAG, "Close connection error", e);
            }
            ConnectingHelper.sendConnectionToHandler(Message.obtain(this.mMainHandler, MSG_ON_DISCONNECTED), sConnection.get());
            return;
        }
        Logger.m1416d(TAG, "Unexpected - No connection to close. ");
    }

    private boolean isPushCommands(JResponse response) {
        switch (response.getCommand()) {
            case 3:
                return true;
            case 19:
                AckNormal ackNormal = (AckNormal) response;
                if (ackNormal.getRequestCommand() == 10 || ackNormal.getRequestCommand() == 2) {
                    return true;
                }
            case 100:
                IMProtocol imProtocol = ((IMResponse) response).getIMProtocol();
                if (imProtocol != null) {
                    return ServiceHelper.isImPushCommand(imProtocol.getCommand());
                }
                Logger.m1420e(TAG, "imProtocol is null, maybe caused by on IMProtocol(byte[])");
                return false;
        }
        return false;
    }
}
