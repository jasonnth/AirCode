package p005cn.jpush.android;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Random;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.helpers.MultiSpHelper;
import p005cn.jpush.android.service.ConnectionState;
import p005cn.jpush.android.util.BasePreferenceManager;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.Configs */
public class Configs extends BasePreferenceManager {
    public static final int BUILD_ID = 69;
    public static final int BUILD_ID_BASE = 260;
    private static final String DEFAULT_DEFAULT_CONN_IP = "113.31.17.106";
    private static final int DEFAULT_DEFAULT_CONN_PORT = 7000;
    private static final String DEFAULT_DEVICEINFO = "";
    private static final int DEFAULT_HEARTBEAT_INTERVAL = 290;
    public static final boolean HAS_TEST_CONN = false;
    private static final int HEARTBEAT_INTERVAL_ONE_DAY = 86400;
    private static final String KEY_APP_KEY = "device_appkey";
    private static final String KEY_BACKUP_REPORT_ADDR = "backup_report_addr";
    private static final String KEY_BACKUP_USER_ADDR = "backup_user_addr";
    private static final String KEY_CHANNEL = "device_channel";
    private static final String KEY_CONNECTING_STATE = "connecting_state";
    private static final String KEY_DEFAULT_CONN_IP = "default_conn_ip";
    private static final String KEY_DEFAULT_CONN_PORT = "default_conn_port";
    public static final String KEY_DEVICE_ID = "devcie_id_generated";
    private static final String KEY_FIRST_LAUNCH = "first_launch";
    private static final String KEY_FIRST_LOGIN = "first_login";
    private static final String KEY_HEARTBEAT_INTERVAL = "heartbeat_interval";
    private static final String KEY_IMEI = "imei";
    private static final String KEY_IS_IM_LOGGED_IN = "is_im_logged_in";
    private static final String KEY_LAST_CONNECTION_TYPE = "last_connection_type";
    private static final String KEY_LAST_GOOD_CONN_IP = "last_good_conn_ip";
    private static final String KEY_LAST_GOOD_CONN_PORT = "last_good_conn_port";
    private static final String KEY_LAST_GOOD_SIS = "last_good_sis";
    private static final String KEY_LAST_REPORT_APPLIST = "last_report_applist";
    private static final String KEY_LAST_REPORT_DEVICE_INFO = "last_report_device_info";
    private static final String KEY_LAST_REPORT_INDEX = "last_report_index";
    private static final String KEY_LAST_SIS_REQUEST_TIME = "last_sis_request_time";
    private static final String KEY_LAST_UPDATE_CONFIG = "last_update_config";
    private static final String KEY_LOGIN_LOCAL_TIME = "login_local_time";
    private static final String KEY_LOGIN_SERVER_TIME = "login_server_time";
    private static final String KEY_MAIN_DEVICE_ANDROID_ID = "device_main_android_id";
    private static final String KEY_MAIN_DEVICE_IDS = "device_main_ids";
    private static final String KEY_MAIN_DEVICE_IMEI = "device_main_imei";
    private static final String KEY_MAIN_DEVICE_MAC = "device_main_mac";
    private static final String KEY_NEXT_RID = "next_rid";
    private static final String KEY_NOTIFICATION_ENABLED = "notification_enabled";
    private static final String KEY_NOTIFICATION_MAX_NUM = "notification_num";
    private static final String KEY_PASSWORD = "device_password";
    private static final String KEY_PUSH_TIME = "setting_push_time";
    private static final String KEY_PUSH_UDID = "push_udid";
    private static final String KEY_REGISTERED_APP_KEY = "device_registered_appkey";
    private static final String KEY_REGISTER_CODE = "jpush_register_code";
    private static final String KEY_REGISTRATION_ID = "device_registration_id";
    private static final String KEY_SAVED_CUSTOM_BUILDER = "jpush_save_custom_builder";
    private static final String KEY_SDK_VERSION = "sdk_version";
    private static final String KEY_SERVER_CONFIG_RETRYTIMES = "serverconfig_retrytimes";
    private static final String KEY_SERVICES_LAUNCHED_TIME = "services_launched_time";
    private static final String KEY_SERVICE_STOPED_FLAG = "service_stoped";
    private static final String KEY_SID = "session_id";
    private static final String KEY_SILENCE_PUSH_TIME = "setting_silence_push_time";
    private static final String KEY_STOP_EXECUTED = "stop_executed_on_imlogin";
    private static final String KEY_UID = "device_uid";
    private static final String KEY_UPDATE_CONFIG_ENABLED = "update_config_enabled";
    private static final String TAG = "Configs";
    public static final String TEST_CONN_IP = "183.232.42.208";
    public static final int TEST_CONN_PORT = 3000;

    public static boolean isReportDeviceInfoNeeded() {
        long last = getLong(KEY_LAST_REPORT_DEVICE_INFO, 0);
        long now = System.currentTimeMillis();
        if (now - last <= 86400000) {
            return false;
        }
        commitLong(KEY_LAST_REPORT_DEVICE_INFO, now);
        return true;
    }

    public static synchronized boolean isReportIndexNeeded() {
        boolean z;
        synchronized (Configs.class) {
            long last = getLong(KEY_LAST_REPORT_INDEX, 0);
            long now = System.currentTimeMillis();
            if (now - last > 86400000) {
                commitLong(KEY_LAST_REPORT_INDEX, now);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static long getLastReportIndex() {
        return getLong(KEY_LAST_REPORT_INDEX, 0);
    }

    public static boolean isReportApplistNeeded() {
        long last = getLong(KEY_LAST_REPORT_APPLIST, 0);
        long now = System.currentTimeMillis();
        if (now - last <= 86400000) {
            return false;
        }
        commitLong(KEY_LAST_REPORT_APPLIST, now);
        return true;
    }

    public static boolean isUpdateConfigNeeded() {
        long last = getLong(KEY_LAST_UPDATE_CONFIG, 0);
        long now = System.currentTimeMillis();
        if (now - last <= 86400000) {
            return false;
        }
        commitLong(KEY_LAST_UPDATE_CONFIG, now);
        return true;
    }

    public static void resetLastUpdateConfigTime() {
        commitLong(KEY_LAST_UPDATE_CONFIG, 0);
    }

    public static long getLastUpdateConfigTime(Context context) {
        return getLong(context, KEY_LAST_UPDATE_CONFIG, 0);
    }

    public static String getLastGoodConnIp() {
        return getString(KEY_LAST_GOOD_CONN_IP, null);
    }

    public static void setLastGoodConnIp(String ip) {
        commitString(KEY_LAST_GOOD_CONN_IP, ip);
    }

    public static int getLastGoodConnPort() {
        return getInt(KEY_LAST_GOOD_CONN_PORT, 0);
    }

    public static void setLastGoodConnPort(int port) {
        commitInt(KEY_LAST_GOOD_CONN_PORT, port);
    }

    public static boolean isSisRequestNeeded() {
        if (System.currentTimeMillis() - getLong(KEY_LAST_SIS_REQUEST_TIME, 0) > 180000) {
            return true;
        }
        return false;
    }

    public static void setLastSisRequestNow() {
        commitLong(KEY_LAST_SIS_REQUEST_TIME, System.currentTimeMillis());
    }

    public static boolean isConnectionTypeChanged(String newType) {
        if (newType == null) {
            return true;
        }
        if (newType.equalsIgnoreCase(getString(KEY_LAST_CONNECTION_TYPE, null))) {
            return false;
        }
        commitString(KEY_LAST_CONNECTION_TYPE, newType);
        return true;
    }

    public static ConnectionState getConnectionState(Context context) {
        return ConnectionState.valueOf(MultiSpHelper.getString(context, KEY_CONNECTING_STATE, ConnectionState.disconnected.name()));
    }

    public static void setConnectionState(Context context, ConnectionState state) {
        MultiSpHelper.commitString(context, KEY_CONNECTING_STATE, state.name());
    }

    public static boolean isFirstLogin() {
        boolean isFirstLogin = getBoolean(KEY_FIRST_LOGIN, true).booleanValue();
        commitBoolean(KEY_FIRST_LOGIN, false);
        return isFirstLogin;
    }

    public static boolean isFirstLaunch() {
        boolean isFirstLaunch = getBoolean("first_launch", true).booleanValue();
        commitBoolean("first_launch", false);
        return isFirstLaunch;
    }

    public static int getSid() {
        return getInt(KEY_SID, 0);
    }

    public static void setSid(int sid) {
        commitInt(KEY_SID, sid);
    }

    public static long getReportTime() {
        long lastLocal = getLong(KEY_LOGIN_LOCAL_TIME, 0);
        return ((getLong(KEY_LOGIN_SERVER_TIME, 0) - lastLocal) + System.currentTimeMillis()) / 1000;
    }

    public static void setLoginServerTime(long serverTime) {
        commitLong(KEY_LOGIN_SERVER_TIME, serverTime);
        commitLong(KEY_LOGIN_LOCAL_TIME, System.currentTimeMillis());
    }

    public static synchronized long getNextRid() {
        long j;
        synchronized (Configs.class) {
            long oldRid = getLong(KEY_NEXT_RID, getStartRid()) % 32767;
            commitLong(KEY_NEXT_RID, oldRid + 2);
            j = oldRid + 2;
        }
        return j;
    }

    protected static long getStartRid() {
        long rid = (long) Math.abs(new Random().nextInt(32767));
        if (rid % 2 == 0) {
            return rid + 1;
        }
        return rid;
    }

    public static boolean isImLoggedIn() {
        return MultiSpHelper.getBoolean(JPush.mApplicationContext, KEY_IS_IM_LOGGED_IN, false);
    }

    public static void setImLoggedIn(boolean loggedIn) {
        MultiSpHelper.commitBoolean(JPush.mApplicationContext, KEY_IS_IM_LOGGED_IN, loggedIn);
    }

    public static int getHeartbeatInterval() {
        return getInt(KEY_HEARTBEAT_INTERVAL, DEFAULT_HEARTBEAT_INTERVAL);
    }

    public static void setHeartbeatInterval(int seconds) {
        commitInt(KEY_HEARTBEAT_INTERVAL, seconds);
    }

    public static void setHeartbeatIntervalDefault() {
        setHeartbeatInterval(DEFAULT_HEARTBEAT_INTERVAL);
    }

    public static void setHeartbeatIntervalOneDay() {
        setHeartbeatInterval(86400);
    }

    public static boolean isHeartbeatIntervalOneDay() {
        return getHeartbeatInterval() == 86400;
    }

    public static String getDefaultConnIp() {
        return getString("default_conn_ip", DEFAULT_DEFAULT_CONN_IP);
    }

    public static void setDefaultConnIp(String ip) {
        commitString("default_conn_ip", ip);
    }

    public static int getDefaultConnPort() {
        return getInt(KEY_DEFAULT_CONN_PORT, DEFAULT_DEFAULT_CONN_PORT);
    }

    public static void setDefaultConnPort(int port) {
        commitInt(KEY_DEFAULT_CONN_PORT, port);
    }

    public static String getBackupReportAddr() {
        return getString(KEY_BACKUP_REPORT_ADDR, null);
    }

    public static void setBackupReportAddr(String addr) {
        commitString(KEY_BACKUP_REPORT_ADDR, addr);
    }

    public static String getBackupUserAddr() {
        return getString(KEY_BACKUP_USER_ADDR, null);
    }

    public static void setBackupUserAddr(String addr) {
        commitString(KEY_BACKUP_USER_ADDR, addr);
    }

    public static String getLastGoodSis() {
        return getString(KEY_LAST_GOOD_SIS, null);
    }

    public static void setLastGoodSis(String sisInfo) {
        commitString(KEY_LAST_GOOD_SIS, sisInfo);
    }

    public static long getLaunchedTime() {
        return getLong(KEY_SERVICES_LAUNCHED_TIME, -1);
    }

    public static void setLaunchedTime(long time) {
        commitLong(KEY_SERVICES_LAUNCHED_TIME, time);
    }

    public static long getUid() {
        long uid = JPush.CURRENT_UID;
        if (uid != 0) {
            return uid;
        }
        long uid2 = MultiSpHelper.getLong(JPush.mApplicationContext, KEY_UID, 0);
        JPush.CURRENT_UID = uid2;
        return uid2;
    }

    public static void setUid(Long uid) {
        JPush.CURRENT_UID = uid.longValue();
        MultiSpHelper.commitLong(JPush.mApplicationContext, KEY_UID, uid.longValue());
    }

    public static void setRegisteredUserInfo(long uid, String pwd, String regId, String deviceId, String appKey) {
        setUid(Long.valueOf(uid));
        setPassword(pwd);
        setRegistrationId(regId);
        if (!StringUtils.isEmpty(deviceId)) {
            setDeviceId(deviceId);
        }
        commitString(KEY_REGISTERED_APP_KEY, appKey);
    }

    public static boolean isValidRegistered() {
        long uid = getUid();
        String regId = getRegistrationId();
        if (uid <= 0 || StringUtils.isEmpty(regId)) {
            return false;
        }
        return true;
    }

    public static void clearRegistered() {
        setUid(Long.valueOf(0));
        setPassword("");
        setRegistrationId("");
        setDeviceId("");
        removeKey(KEY_REGISTERED_APP_KEY);
    }

    public static String getPassword() {
        String password = JPush.CURRENT_PWD;
        if (!StringUtils.isEmpty(password)) {
            return password;
        }
        String password2 = MultiSpHelper.getString(JPush.mApplicationContext, KEY_PASSWORD, "");
        JPush.CURRENT_PWD = password2;
        return password2;
    }

    public static void setPassword(String value) {
        JPush.CURRENT_PWD = value;
        MultiSpHelper.commitString(JPush.mApplicationContext, KEY_PASSWORD, value);
    }

    public static String getRegistrationId() {
        return MultiSpHelper.getString(JPush.mApplicationContext, KEY_REGISTRATION_ID, "");
    }

    public static void setRegistrationId(String value) {
        MultiSpHelper.commitString(JPush.mApplicationContext, KEY_REGISTRATION_ID, value);
    }

    public static String getRegisteredAppKey() {
        return getString(KEY_REGISTERED_APP_KEY, null);
    }

    public static String getDeviceId() {
        return MultiSpHelper.getString(JPush.mApplicationContext, KEY_DEVICE_ID, "");
    }

    public static void setDeviceId(String deviceId) {
        MultiSpHelper.commitString(JPush.mApplicationContext, KEY_DEVICE_ID, deviceId);
    }

    public static String getAppKey() {
        return MultiSpHelper.getString(JPush.mApplicationContext, KEY_APP_KEY, "");
    }

    public static void setAppKey(String appKey) {
        MultiSpHelper.commitString(JPush.mApplicationContext, KEY_APP_KEY, appKey);
    }

    public static String getChannel() {
        return getString(KEY_CHANNEL, null);
    }

    public static void setChannel(String channel) {
        commitString(KEY_CHANNEL, channel);
    }

    public static boolean isFirstInstallNewVersion() {
        String old_imei = getMainImei();
        String old_androidId = getMainAndroidId();
        String old_Mac = getMainMac();
        if (!old_imei.isEmpty() || !old_androidId.isEmpty() || !old_Mac.isEmpty()) {
            return false;
        }
        Logger.m1424i(TAG, "Update from a version earlier than 180 ,or fist time install JPushSDK.");
        return true;
    }

    public static boolean isSameDeviceJudgeOne(String imei, String androidId) {
        String old_imei = getMainImei();
        String old_androidId = getMainAndroidId();
        if (!imei.equals(old_imei) || !androidId.equals(old_androidId)) {
            Logger.m1424i(TAG, "NewDevice:IMEI or AndroidId had changed!");
            return false;
        }
        Logger.m1424i(TAG, "Same IMEI and androidId!");
        return true;
    }

    public static boolean isSameDeviceJudgeTwo(String androidId, String mac) {
        String old_androidId = getMainAndroidId();
        String old_mac = getMainMac();
        if (StringUtils.isEmpty(mac) || StringUtils.isEmpty(old_mac)) {
            return androidId.equals(old_androidId);
        }
        if (!androidId.equals(old_androidId) || !mac.equals(old_mac)) {
            Logger.m1424i(TAG, "NewDevice:AndroidId or Mac had changed!");
            return false;
        }
        Logger.m1424i(TAG, "Same androidId and macAddress!");
        return true;
    }

    public static String getMainImei() {
        return getString(KEY_MAIN_DEVICE_IMEI, "");
    }

    public static void setMainImei(String imei) {
        commitString(KEY_MAIN_DEVICE_IMEI, imei);
    }

    public static String getMainAndroidId() {
        return getString(KEY_MAIN_DEVICE_ANDROID_ID, "");
    }

    public static void setMainAndroidId(String androidId) {
        commitString(KEY_MAIN_DEVICE_ANDROID_ID, androidId);
    }

    public static String getMainMac() {
        return getString(KEY_MAIN_DEVICE_MAC, "");
    }

    public static void setMainMac(String mac) {
        commitString(KEY_MAIN_DEVICE_MAC, mac);
    }

    public static void setSdkVersion(String sdkVersion) {
        commitString(KEY_SDK_VERSION, sdkVersion);
    }

    public static String getSdkVersion() {
        return getString(KEY_SDK_VERSION, "");
    }

    public static void setUpdateConfigEnabled(Context context, int pushEnabled) {
        commitInt(context, KEY_UPDATE_CONFIG_ENABLED, pushEnabled);
    }

    public static int getUpdateConfigEnabled(Context context) {
        return getInt(context, KEY_UPDATE_CONFIG_ENABLED, -1);
    }

    public static void setServerConfigRetrytimes(Context context, int retryTimes) {
        commitInt(context, KEY_SERVER_CONFIG_RETRYTIMES, retryTimes);
    }

    public static int getServerConfigRetrytimes(Context context) {
        return getInt(context, KEY_SERVER_CONFIG_RETRYTIMES, 0);
    }

    public static void setNotificationMaxNum(Context context, int maxNum) {
        MultiSpHelper.commitInt(context, KEY_NOTIFICATION_MAX_NUM, maxNum);
    }

    public static int getNotificationMaxNum(Context context) {
        int result = MultiSpHelper.getInt(context, KEY_NOTIFICATION_MAX_NUM, 5);
        Logger.m1428v(TAG, "max notification:" + result);
        return result;
    }

    public static void setServiceStopedFlag(Context context, int stopFlag) {
        MultiSpHelper.commitInt(context, KEY_SERVICE_STOPED_FLAG, stopFlag);
    }

    public static int getServiceStoppedFlag(Context context) {
        return MultiSpHelper.getInt(context, KEY_SERVICE_STOPED_FLAG, 0);
    }

    public static void setStopExecuted(Context context, boolean stopFlag) {
        MultiSpHelper.commitBoolean(context, KEY_STOP_EXECUTED, stopFlag);
    }

    public static boolean isStopExecuted(Context context) {
        return MultiSpHelper.getBoolean(context, KEY_STOP_EXECUTED, false);
    }

    public static String getSilencePushTime(Context context) {
        return MultiSpHelper.getString(context, KEY_SILENCE_PUSH_TIME, "");
    }

    public static void setSilencePushTime(Context context, String sliencePushTime) {
        MultiSpHelper.commitString(context, KEY_SILENCE_PUSH_TIME, sliencePushTime);
    }

    public static String getPushTime(Context context) {
        return MultiSpHelper.getString(context, KEY_PUSH_TIME, "");
    }

    public static void setPushTime(Context context, String pushTime) {
        MultiSpHelper.commitString(context, KEY_PUSH_TIME, pushTime);
    }

    public static void setNotificationEnabled(Context context, boolean enableFlag) {
        MultiSpHelper.commitBoolean(context, KEY_NOTIFICATION_ENABLED, enableFlag);
    }

    public static boolean isNotificationEnabled(Context context) {
        return MultiSpHelper.getBoolean(context, KEY_NOTIFICATION_ENABLED, true);
    }

    public static String getPushUdid(Context context) {
        return getString(context, KEY_PUSH_UDID, "");
    }

    public static void setPushUdid(Context context, String udid) {
        commitString(context, KEY_PUSH_UDID, udid);
    }

    public static String getImei(Context context) {
        return getString(context, "imei", "");
    }

    public static void setImei(Context context, String imei) {
        commitString(context, "imei", imei);
    }

    public static void setCustomBuilder(Context context, String paramkey, String value) {
        MultiSpHelper.commitString(context, KEY_SAVED_CUSTOM_BUILDER + paramkey, value);
    }

    public static String getCustomBuilder(Context context, String paramKey) {
        return MultiSpHelper.getString(context, KEY_SAVED_CUSTOM_BUILDER + paramKey, "");
    }

    public static void setRegisterCode(Context context, int registerCode) {
        MultiSpHelper.commitInt(context, KEY_REGISTER_CODE, registerCode);
    }

    public static int getRegisterCode(Context context) {
        return MultiSpHelper.getInt(context, KEY_REGISTER_CODE, -1);
    }

    public static void copyDataOnUpgradeFromVersionOne(Context context) {
        init(context);
        SharedPreferences lowPreference = context.getSharedPreferences("cn.jpush.serverconfig", 0);
        commitString(context, KEY_CONNECTING_STATE, lowPreference.getInt("service_connecet", 0) == 1 ? ConnectionState.connected.name() : ConnectionState.disconnected.name());
        commitString(context, KEY_MAIN_DEVICE_IDS, lowPreference.getString("register_device_info", ""));
        commitString(context, KEY_MAIN_DEVICE_IMEI, lowPreference.getString("register_device_imei", ""));
        commitString(context, KEY_MAIN_DEVICE_ANDROID_ID, lowPreference.getString("register_device_android_id", ""));
        commitString(context, KEY_MAIN_DEVICE_MAC, lowPreference.getString("register_device_mac", ""));
        commitString(context, KEY_LAST_CONNECTION_TYPE, lowPreference.getString(PushService.SIS_NETTYPE, ""));
        commitString(context, KEY_LAST_GOOD_SIS, lowPreference.getString(PushService.SIS_RECEIVER, ""));
        commitString(context, "default_conn_ip", lowPreference.getString("mIP", ""));
        commitString(context, KEY_BACKUP_REPORT_ADDR, lowPreference.getString("http_report_sis_url", ""));
        commitString(context, KEY_LAST_GOOD_CONN_IP, lowPreference.getString(PushService.CONN_IP, ""));
        commitString(context, KEY_PUSH_UDID, lowPreference.getString(KEY_PUSH_UDID, ""));
        commitString(context, "imei", lowPreference.getString("imei", ""));
        commitInt(context, KEY_DEFAULT_CONN_PORT, lowPreference.getInt("mPort", 0));
        commitInt(context, KEY_LAST_GOOD_CONN_PORT, lowPreference.getInt(PushService.CONN_PORT, 0));
        commitLong(context, KEY_LOGIN_LOCAL_TIME, lowPreference.getLong(JPushConstants.LOC_REPORT_TIME, 0));
        commitLong(context, KEY_LAST_REPORT_DEVICE_INFO, lowPreference.getLong(JPushReportInterface.LAST_DEVICE_INFO_REPORT_TIME, 0));
        commitLong(context, KEY_LOGIN_SERVER_TIME, lowPreference.getLong(KEY_LOGIN_SERVER_TIME, 0));
        setRegistrationId(lowPreference.getString("registration_id", ""));
        setAppKey(lowPreference.getString(JPushInterface.EXTRA_APP_KEY, ""));
        setDeviceId(lowPreference.getString("JPush_DeviceId", ""));
        setSilencePushTime(context, lowPreference.getString("silencePushTime", ""));
        setPushTime(context, lowPreference.getString("pushtime", ""));
        setNotificationMaxNum(context, lowPreference.getInt("notifaction_num", 5));
        setServiceStopedFlag(context, lowPreference.getInt(KEY_SERVICE_STOPED_FLAG, 0));
    }
}
