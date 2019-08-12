package p005cn.jpush.android.helpers;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.google.gson.jpush.JsonArray;
import com.google.gson.jpush.JsonElement;
import com.google.gson.jpush.JsonObject;
import com.google.gson.jpush.JsonParser;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.service.ConnectionState;
import p005cn.jpush.android.service.NetworkingClient;
import p005cn.jpush.android.service.PushProtocol;
import p005cn.jpush.android.service.SisInfo;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.IndexStats;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;
import p005cn.jpush.proto.common.commands.JCommands;
import p005cn.jpush.proto.common.commands.JResponse;
import p005cn.jpush.proto.common.commands.LoginResponse;
import p005cn.jpush.proto.common.commands.RegisterResponse;
import p005cn.jpush.proto.common.imcommands.ImResponseHelper;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.android.helpers.ConnectingHelper */
public class ConnectingHelper {
    private static final int INVALID_RET = -1;
    private static final List<String> SIS_INFO_LIST = new ArrayList();
    private static final String TAG = "ConnectingHelper";

    /* renamed from: cn.jpush.android.helpers.ConnectingHelper$CheckDnsThread */
    private static class CheckDnsThread extends Thread {
        private InetAddress addr = null;
        private String hostname = null;

        public CheckDnsThread(String hostname2) {
            this.hostname = hostname2;
        }

        public void run() {
            try {
                Logger.m1424i(ConnectingHelper.TAG, "resolved DNS - host:" + this.hostname);
                this.addr = InetAddress.getByName(this.hostname);
            } catch (UnknownHostException e) {
                Logger.m1435ww(ConnectingHelper.TAG, "Unknown host exception!", e);
            } catch (Exception e2) {
                Logger.m1435ww(ConnectingHelper.TAG, "The failure appears to have been a lack of INTERNET !", e2);
            }
        }

        public synchronized InetAddress getAddr() {
            InetAddress inetAddress;
            if (this.addr != null) {
                inetAddress = this.addr;
            } else {
                Logger.m1432w(ConnectingHelper.TAG, "Resolved DNS fail from host: " + this.hostname);
                inetAddress = null;
            }
            return inetAddress;
        }
    }

    public static boolean register(Context context, long connection, boolean registerOnly) {
        byte[] buf = new byte[128];
        String key = ServiceHelper.getRegistrationKey(context);
        String aVersion = ServiceHelper.getApkVersion(context);
        String clientInfo = AndroidUtil.getClientInfo(context, JPushConstants.SDK_VERSION);
        String extKey = ServiceHelper.getRegistrationExtKey(context);
        Logger.m1416d(TAG, "Register with: key:" + key + ", apkVersion:" + aVersion + ", clientInfo:" + clientInfo + ", extKey:" + extKey);
        PushProtocol.RegPush(connection, Configs.getNextRid(), key, aVersion, clientInfo, extKey);
        int ret = PushProtocol.RecvPush(connection, buf, 30);
        if (ret > 0) {
            JResponse response = JCommands.parseResponseInbound(buf);
            if (response == null) {
                Logger.m1420e(TAG, "Register failed - unknown command");
                return false;
            }
            Logger.m1416d(TAG, response.toString());
            if (response.getCommand() != 0) {
                Logger.m1420e(TAG, "Register failed - it is not register response.");
                return false;
            }
            RegisterResponse registerReponse = (RegisterResponse) response;
            int respCode = registerReponse.code;
            Configs.setRegisterCode(context, respCode);
            if (respCode == 0) {
                long uid = registerReponse.getJuid();
                String password = registerReponse.getPassword();
                String regId = registerReponse.getRegId();
                String deviceId = registerReponse.getDeviceId();
                Logger.m1426ii(TAG, "Register succeed - juid:" + uid + ", registrationId:" + regId + ", deviceId:" + deviceId);
                Logger.m1428v(TAG, "password:" + password);
                if (StringUtils.isEmpty(regId) || 0 == uid) {
                    Logger.m1422ee(TAG, "Unexpected: registrationId/juid should not be empty. ");
                }
                AndroidUtil.saveDeviceIdFromServer(context, deviceId);
                Configs.setRegisteredUserInfo(uid, password, regId, deviceId, JPush.APP_KEY);
                JPush.CURRENT_UID = uid;
                JPush.CURRENT_PWD = password;
                if (!registerOnly) {
                    AndroidUtil.sendBroadcast(context, JPushInterface.ACTION_REGISTRATION_ID, JPushInterface.EXTRA_REGISTRATION_ID, regId);
                }
                return true;
            }
            handleRegisterError(context, respCode, registerReponse.error);
        } else {
            Logger.m1422ee(TAG, "Register failed - ret:" + ret);
        }
        return false;
    }

    public static int login(Context context, long connection) {
        int st_failed = 0;
        byte[] buf = new byte[128];
        long uid = Configs.getUid();
        String pwd = StringUtils.toMD5(Configs.getPassword());
        String appKey = Configs.getAppKey();
        int sdkVersion = AndroidUtil.getVersionFromString(JPushConstants.SDK_VERSION);
        Logger.m1426ii(TAG, "Login with - juid:" + uid + ", appKey:" + appKey + ", sdkVersion:" + sdkVersion);
        long startLoginTime = System.currentTimeMillis();
        int ret = PushProtocol.LogPush(connection, Configs.getNextRid(), buf, uid, pwd, appKey, (long) sdkVersion, getIMLoginFlag());
        long endLoginTime = System.currentTimeMillis();
        int respCode = 0;
        if (ret == 0 || ret == 9999) {
            LoginResponse loginResponse = (LoginResponse) JCommands.parseResponseInbound(buf);
            if (loginResponse == null) {
                Logger.m1434ww(TAG, "Login failed with return code:" + ret + " loginResponse is null");
                IndexStats.insertData(context, ret, endLoginTime - startLoginTime, 1);
                return -1;
            }
            Logger.m1416d(TAG, loginResponse.toString());
            respCode = loginResponse.code;
            if (respCode == 0) {
                int sid = loginResponse.getSid();
                long serverTime = ((long) loginResponse.getServerTime()) * 1000;
                Configs.setSid(sid);
                Configs.setLoginServerTime(serverTime);
                Logger.m1426ii(TAG, "Login succeed - sid:" + sid + ", serverTime;" + serverTime);
                sendServerTimer(context, serverTime);
            } else if (respCode == 10000) {
                Logger.m1434ww(TAG, "Login failed with Local error - code:" + respCode + ", error:" + loginResponse.error);
                st_failed = 1;
            } else {
                Logger.m1434ww(TAG, "Login failed with server error - code:" + respCode + ", error:" + loginResponse.error);
            }
        } else {
            st_failed = 1;
            Logger.m1434ww(TAG, "Login failed with return code:" + ret);
        }
        IndexStats.insertData(context, ret, endLoginTime - startLoginTime, st_failed);
        if (respCode == 10000) {
            ret = -1;
        }
        return ret;
    }

    public static short getIMLoginFlag() {
        return 0;
    }

    public static void sendServerTimer(Context context, long serverTime) {
        Logger.m1418dd(TAG, "Action - sendServerTimer");
        try {
            Bundle bundle = new Bundle();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ImResponseHelper.EXTRA_LOGIN_SERVER_TIME, serverTime);
            jsonObject.put(ImResponseHelper.EXTRA_LOGIN_LOCAL_TIME, System.currentTimeMillis());
            bundle.putString(ImResponseHelper.EXTRA_PUSH2IM_DATA, jsonObject.toString());
            AndroidUtil.sendBroadcast(context, ImResponseHelper.ACTION_IM_RESPONSE, bundle);
        } catch (JSONException e) {
            Logger.m1432w(TAG, "jsonException - " + e.getMessage());
        }
    }

    public static void sendConnectionChanged(Context context, ConnectionState state) {
        Logger.m1418dd(TAG, "Action - sendConnectionChanged");
        if (state == Configs.getConnectionState(context)) {
            Logger.m1428v(TAG, "state is not changed - " + state);
            return;
        }
        Configs.setConnectionState(context, state);
        Bundle bundle = new Bundle();
        bundle.putBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, state.name().equals("connected"));
        AndroidUtil.sendBroadcast(context, JPushInterface.ACTION_CONNECTION_CHANGE, bundle);
    }

    private static int injectConnection(long mConnection, String connIp, int connPort) {
        int ret = PushProtocol.InitPush(mConnection, connIp, connPort);
        if (ret != 0) {
            Logger.m1418dd(TAG, "Open connection failed - ret:" + ret);
        }
        return ret;
    }

    public static synchronized boolean openConnection(Context context, long mConnection, SisInfo sisInfo) {
        int ret;
        boolean z;
        synchronized (ConnectingHelper.class) {
            if (sisInfo == null) {
                String connIp = Configs.getLastGoodConnIp();
                int connPort = Configs.getLastGoodConnPort();
                if (connIp != null) {
                    Logger.m1418dd(TAG, "Open connection with last good - ip:" + connIp + ", port:" + connPort);
                    if (injectConnection(mConnection, connIp, connPort) == 0) {
                        z = true;
                    }
                }
                z = openDefaultConnection(mConnection) == 0;
            } else {
                connIp = sisInfo.getMainConnIp();
                int connPort2 = sisInfo.getMainConnPort();
                Logger.m1418dd(TAG, "Open connection with main - ip:" + connIp + ", port:" + connPort2);
                if (connIp == null || connPort2 == 0) {
                    ret = -1;
                    Logger.m1434ww(TAG, "Invalid main conn");
                } else {
                    ret = injectConnection(mConnection, connIp, connPort2);
                }
                if (ret != 0) {
                    int index = 0;
                    for (String connIp2 : sisInfo.getOptionConnIp()) {
                        connPort2 = ((Integer) sisInfo.getOptionConnPort().get(index)).intValue();
                        Logger.m1418dd(TAG, "Open connection with options - ip:" + connIp2 + ", port:" + connPort2 + ", index:" + index);
                        ret = injectConnection(mConnection, connIp2, connPort2);
                        if (ret == 0) {
                            break;
                        }
                        index++;
                    }
                }
                if (ret != 0) {
                    ret = openDefaultConnection(mConnection);
                }
                if (ret == 0) {
                    Configs.setLastGoodConnIp(connIp2);
                    Configs.setLastGoodConnPort(connPort2);
                    Logger.m1426ii(TAG, "Succeed to open connection - ip:" + connIp2 + ", port:" + connPort2);
                    z = true;
                } else {
                    Logger.m1434ww(TAG, "Failed with all conns.");
                    z = false;
                }
            }
        }
        return z;
    }

    private static int openDefaultConnection(long connection) {
        String connIp = Configs.getDefaultConnIp();
        int connPort = Configs.getDefaultConnPort();
        Logger.m1418dd(TAG, "Open connection with default - ip:" + connIp + ", port:" + connPort);
        if (connIp == null || connPort == 0) {
            Logger.m1418dd(TAG, "Invalid default conn.");
            return -1;
        }
        int ret = injectConnection(connection, connIp, connPort);
        if (ret == 0) {
            return ret;
        }
        try {
            InetAddress hostAddress = resolveDns(JPushConstants.DEFAULT_CONN_HOST);
            if (hostAddress == null) {
                throw new Exception();
            }
            String connIp2 = hostAddress.getHostAddress();
            Logger.m1416d(TAG, "Open connection with hardcoded host - ip:" + connIp2 + ", port:" + 3000);
            return injectConnection(connection, connIp2, 3000);
        } catch (Exception e) {
            Logger.m1416d(TAG, "Exception to connect host - im64.jpush.cn");
            return ret;
        }
    }

    public static synchronized SisInfo sendSis(Context context) {
        SisInfo sendSis;
        synchronized (ConnectingHelper.class) {
            sendSis = sendSis(context, false, 0, JPushConstants.DEFAULT_SIS_PORT);
        }
        return sendSis;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0204 A[SYNTHETIC, Splitter:B:50:0x0204] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x024e A[SYNTHETIC, Splitter:B:58:0x024e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static p005cn.jpush.android.service.SisInfo sendSis(android.content.Context r23, boolean r24, int r25, int r26) {
        /*
            java.lang.String r19 = p005cn.jpush.android.util.AndroidUtil.getCurrentNetType(r23)
            boolean r6 = p005cn.jpush.android.Configs.isConnectionTypeChanged(r19)
            if (r24 != 0) goto L_0x0024
            if (r6 != 0) goto L_0x0024
            boolean r19 = p005cn.jpush.android.Configs.isSisRequestNeeded()
            if (r19 != 0) goto L_0x0024
            java.lang.String r19 = "ConnectingHelper"
            java.lang.String r20 = "Do not need SIS again within 3 mins. Use last good sis response. "
            p005cn.jpush.android.util.Logger.m1424i(r19, r20)
            java.lang.String r19 = p005cn.jpush.android.Configs.getLastGoodSis()
            cn.jpush.android.service.SisInfo r14 = parseSisInfo(r19)
        L_0x0023:
            return r14
        L_0x0024:
            java.lang.String r13 = ""
            r17 = 0
            r5 = 0
            java.net.DatagramSocket r18 = new java.net.DatagramSocket     // Catch:{ AssertionError -> 0x027c, Exception -> 0x0279 }
            r18.<init>()     // Catch:{ AssertionError -> 0x027c, Exception -> 0x0279 }
            r19 = 1024(0x400, float:1.435E-42)
            r0 = r19
            byte[] r8 = new byte[r0]     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.util.List<java.lang.String> r19 = SIS_INFO_LIST     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r19
            r1 = r25
            java.lang.Object r19 = r0.get(r1)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r19
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r13 = r0
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20.<init>()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = "To get sis - host:"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = ", port:"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            r1 = r26
            java.lang.StringBuilder r20 = r0.append(r1)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = ", selection:"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            r1 = r25
            java.lang.StringBuilder r20 = r0.append(r1)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r20 = r20.toString()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            p005cn.jpush.android.util.Logger.m1418dd(r19, r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r12 = 0
            r19 = 2
            r0 = r25
            r1 = r19
            if (r0 > r1) goto L_0x00f0
            java.net.InetAddress r12 = resolveDns(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            if (r12 != 0) goto L_0x00f4
            java.lang.Exception r19 = new java.lang.Exception     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20.<init>()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = "Failed to resolve host dns - "
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r20 = r20.toString()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r19.<init>(r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            throw r19     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
        L_0x00a9:
            r16 = move-exception
            r17 = r18
        L_0x00ac:
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ all -> 0x024b }
            r20.<init>()     // Catch:{ all -> 0x024b }
            java.lang.String r21 = "Get sis info error - sisHost:"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ all -> 0x024b }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ all -> 0x024b }
            java.lang.String r20 = r20.toString()     // Catch:{ all -> 0x024b }
            r0 = r19
            r1 = r20
            r2 = r16
            p005cn.jpush.android.util.Logger.m1435ww(r0, r1, r2)     // Catch:{ all -> 0x024b }
            int r25 = r25 + 1
            r5 = 1
            if (r17 == 0) goto L_0x00d6
            r17.close()     // Catch:{ AssertionError -> 0x022a }
        L_0x00d6:
            if (r5 == 0) goto L_0x0272
            r19 = 4
            r0 = r25
            r1 = r19
            if (r0 >= r1) goto L_0x0272
            r19 = 1
            r0 = r23
            r1 = r19
            r2 = r25
            r3 = r26
            cn.jpush.android.service.SisInfo r14 = sendSis(r0, r1, r2, r3)
            goto L_0x0023
        L_0x00f0:
            java.net.InetAddress r12 = java.net.InetAddress.getByName(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
        L_0x00f4:
            java.lang.String r19 = p005cn.jpush.android.JPush.APP_KEY     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            long r20 = p005cn.jpush.android.Configs.getUid()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r23
            r1 = r19
            r2 = r20
            byte[] r10 = prepareSisSendBuffer(r0, r1, r2)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r11 = 0
            java.net.DatagramPacket r11 = new java.net.DatagramPacket     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            int r0 = r10.length     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r19 = r0
            r0 = r19
            r1 = r26
            r11.<init>(r10, r0, r12, r1)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r19 = 6000(0x1770, float:8.408E-42)
            r18.setSoTimeout(r19)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r18
            r0.send(r11)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.net.DatagramPacket r9 = new java.net.DatagramPacket     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            int r0 = r8.length     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r19 = r0
            r0 = r19
            r9.<init>(r8, r0)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r19 = "ConnectingHelper"
            java.lang.String r20 = "SIS Receiving..."
            p005cn.jpush.android.util.Logger.m1418dd(r19, r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r18
            r0.receive(r9)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            int r19 = r9.getLength()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r19
            byte[] r7 = new byte[r0]     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            byte[] r19 = r9.getData()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20 = 0
            r21 = 0
            int r0 = r7.length     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r22 = r0
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            java.lang.System.arraycopy(r0, r1, r7, r2, r3)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r15 = new java.lang.String     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r15.<init>(r7)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20.<init>()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = "SIS Received String: "
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r15)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r20 = r20.toString()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            p005cn.jpush.android.util.Logger.m1424i(r19, r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            cn.jpush.android.service.SisInfo r14 = parseSisInfo(r15)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            if (r14 == 0) goto L_0x01bf
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20.<init>()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = "Get sis info succeed with host: "
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r20 = r20.toString()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            p005cn.jpush.android.util.Logger.m1426ii(r19, r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            p005cn.jpush.android.Configs.setLastSisRequestNow()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
        L_0x0197:
            if (r18 == 0) goto L_0x0023
            r18.close()     // Catch:{ AssertionError -> 0x019e }
            goto L_0x0023
        L_0x019e:
            r16 = move-exception
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r20.<init>()
            java.lang.String r21 = "Exception when close udp socket - "
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = r16.toString()
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            p005cn.jpush.android.util.Logger.m1420e(r19, r20)
            goto L_0x0023
        L_0x01bf:
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r20.<init>()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r21 = "Can not get sis info from host: "
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            java.lang.String r20 = r20.toString()     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            p005cn.jpush.android.util.Logger.m1434ww(r19, r20)     // Catch:{ AssertionError -> 0x00a9, Exception -> 0x01dc, all -> 0x0275 }
            goto L_0x0197
        L_0x01dc:
            r4 = move-exception
            r17 = r18
        L_0x01df:
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ all -> 0x024b }
            r20.<init>()     // Catch:{ all -> 0x024b }
            java.lang.String r21 = "Get sis info error - sisHost:"
            java.lang.StringBuilder r20 = r20.append(r21)     // Catch:{ all -> 0x024b }
            r0 = r20
            java.lang.StringBuilder r20 = r0.append(r13)     // Catch:{ all -> 0x024b }
            java.lang.String r20 = r20.toString()     // Catch:{ all -> 0x024b }
            r0 = r19
            r1 = r20
            p005cn.jpush.android.util.Logger.m1435ww(r0, r1, r4)     // Catch:{ all -> 0x024b }
            int r25 = r25 + 1
            r5 = 1
            if (r17 == 0) goto L_0x00d6
            r17.close()     // Catch:{ AssertionError -> 0x0209 }
            goto L_0x00d6
        L_0x0209:
            r16 = move-exception
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r20.<init>()
            java.lang.String r21 = "Exception when close udp socket - "
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = r16.toString()
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            p005cn.jpush.android.util.Logger.m1420e(r19, r20)
            goto L_0x00d6
        L_0x022a:
            r16 = move-exception
            java.lang.String r19 = "ConnectingHelper"
            java.lang.StringBuilder r20 = new java.lang.StringBuilder
            r20.<init>()
            java.lang.String r21 = "Exception when close udp socket - "
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r21 = r16.toString()
            java.lang.StringBuilder r20 = r20.append(r21)
            java.lang.String r20 = r20.toString()
            p005cn.jpush.android.util.Logger.m1420e(r19, r20)
            goto L_0x00d6
        L_0x024b:
            r19 = move-exception
        L_0x024c:
            if (r17 == 0) goto L_0x0251
            r17.close()     // Catch:{ AssertionError -> 0x0252 }
        L_0x0251:
            throw r19
        L_0x0252:
            r16 = move-exception
            java.lang.String r20 = "ConnectingHelper"
            java.lang.StringBuilder r21 = new java.lang.StringBuilder
            r21.<init>()
            java.lang.String r22 = "Exception when close udp socket - "
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r22 = r16.toString()
            java.lang.StringBuilder r21 = r21.append(r22)
            java.lang.String r21 = r21.toString()
            p005cn.jpush.android.util.Logger.m1420e(r20, r21)
            goto L_0x0251
        L_0x0272:
            r14 = 0
            goto L_0x0023
        L_0x0275:
            r19 = move-exception
            r17 = r18
            goto L_0x024c
        L_0x0279:
            r4 = move-exception
            goto L_0x01df
        L_0x027c:
            r16 = move-exception
            goto L_0x00ac
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.jpush.android.helpers.ConnectingHelper.sendSis(android.content.Context, boolean, int, int):cn.jpush.android.service.SisInfo");
    }

    public static SisInfo parseSisInfo(String sisResponse) {
        try {
            SisInfo sisInfo = SisInfo.fromJson(sisResponse);
            if (sisInfo == null) {
                Logger.m1434ww(TAG, "Invalid sis response, failed to parse JSON.");
                return null;
            }
            JsonElement root = new JsonParser().parse(sisResponse);
            Logger.m1428v(TAG, "the json - " + root.toString());
            if (root.isJsonObject()) {
                JsonObject o = root.getAsJsonObject();
                if (o.has("ips")) {
                    JsonElement ips = o.get("ips");
                    if (ips.isJsonArray()) {
                        JsonArray arr = ips.getAsJsonArray();
                        Logger.m1428v(TAG, "ips arr.len - " + arr.size());
                        if (arr.size() >= 3) {
                            Logger.m1416d(TAG, "default http:" + arr.get(2).getAsString());
                        }
                    }
                } else {
                    Logger.m1420e(TAG, "No ips key");
                }
            }
            sisInfo.parseAndSet(sisResponse);
            if (!sisInfo.isInvalidSis()) {
                return sisInfo;
            }
            return null;
        } catch (Exception e) {
            Logger.m1434ww(TAG, "parseSisinfo crash::" + e);
        }
    }

    private static byte[] prepareSisSendBuffer(Context context, String appKey, long uid) {
        int opNum;
        String networkType = AndroidUtil.getNetworkType(context);
        String operatorNumber = "";
        try {
            operatorNumber = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String getServerReq = "UF" + networkType;
        try {
            opNum = Integer.valueOf(operatorNumber).intValue();
        } catch (Exception e2) {
            opNum = 0;
        }
        byte[] sendBuf = new byte[128];
        byte[] lenBuf = {0, ISOFileInfo.DATA_BYTES1};
        System.arraycopy(lenBuf, 0, sendBuf, 0, lenBuf.length);
        ProtocolUtil.fillStringData(sendBuf, getServerReq, 2);
        ProtocolUtil.fillIntData(sendBuf, opNum, 34);
        ProtocolUtil.fillIntData(sendBuf, Integer.parseInt(((int) (2147483647L & uid)) + ""), 38);
        if (appKey.length() > 50) {
            appKey = appKey.substring(0, 49);
        }
        ProtocolUtil.fillStringData(sendBuf, appKey, 42);
        ProtocolUtil.fillStringData(sendBuf, JPushConstants.SDK_VERSION, 92);
        ProtocolUtil.fillIntData(sendBuf, 0, 102);
        if (sendBuf.length > 256) {
        }
        return sendBuf;
    }

    private static InetAddress resolveDns(String host) {
        boolean z = false;
        CheckDnsThread checkDnsThread = new CheckDnsThread(host);
        try {
            checkDnsThread.start();
            checkDnsThread.join(3000);
            return checkDnsThread.getAddr();
        } catch (InterruptedException e) {
            Logger.m1420e(TAG, "DNS checking thread has been interrupted !");
            return z;
        } catch (Exception e2) {
            e2.printStackTrace();
            return z;
        }
    }

    private static void handleRegisterError(Context context, int respCode, String msg) {
        Logger.m1422ee(TAG, "Register Failed with server error - code:" + respCode + ", message:" + msg);
        String desc = StatusCode.getErrorDesc(respCode);
        if (desc != null) {
            Logger.m1434ww(TAG, "Local error description: " + desc);
        }
        if (1006 == respCode) {
            Configs.setHeartbeatIntervalOneDay();
        } else if (1007 == respCode) {
            Logger.m1424i(TAG, "IMEI is duplicated reported by server. Give up now. ");
        } else if (1005 == respCode) {
            String info = "包名: " + JPush.PKG_NAME + " 与 AppKey:" + JPush.APP_KEY + "不匹配";
            AndroidUtil.showPermanentNotification(context, info, info);
            Configs.setHeartbeatIntervalOneDay();
        } else if (1009 == respCode) {
            Configs.setHeartbeatIntervalOneDay();
        } else {
            Logger.m1424i(TAG, "Unhandled server response error code - " + respCode);
        }
    }

    private void setRtcToDayTimesWhenRegisterFailed() {
        Logger.m1416d(TAG, "Action: setRtcToDayTimesWhenRegisterFailed");
        Configs.setHeartbeatIntervalOneDay();
    }

    private boolean isServiceStopedByRegister() {
        return Configs.isHeartbeatIntervalOneDay();
    }

    private void restoreRtcWhenRegisterSucceed() {
        if (isServiceStopedByRegister()) {
            Logger.m1416d(TAG, "Action: restoreRtcWhenRegisterSucceed");
            Configs.setHeartbeatIntervalDefault();
        }
    }

    public static void sendConnectionToHandler(Message msg, long connection) {
        Bundle bundle = new Bundle();
        bundle.putLong(NetworkingClient.EXTRA_CONNECTION, connection);
        msg.setData(bundle);
        msg.sendToTarget();
    }

    static {
        SIS_INFO_LIST.add("s.jpush.cn");
        SIS_INFO_LIST.add("sis.jpush.io");
        SIS_INFO_LIST.add("easytomessage.com");
        SIS_INFO_LIST.add("113.31.17.108");
    }
}
