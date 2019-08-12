package p005cn.jpush.android;

import android.os.Environment;
import java.io.File;

/* renamed from: cn.jpush.android.JPushConstants */
public interface JPushConstants {
    public static final String ACTION_TYPE_WEBURL = "weburl";
    public static final String APP_ID = "appId";
    public static final String AUTO_DOWN_NET = "WIFI";
    public static final String A_MIME = "application/vnd.android.package-archive";
    public static final String COL_TYPE = "colType";
    public static final String DEFAULT_CONN_HOST = "im64.jpush.cn";
    public static final int DEFAULT_CONN_PORT = 3000;
    public static final int DEFAULT_SIS_PORT = 19000;
    public static final int DURATION_APPLIST_REPORT = 86400000;
    public static final int DURATION_DEVICE_INFO_REPORT = 86400000;
    public static final int DURATION_HEARTBEAT_AFTER_LOGGEDIN = 15000;
    public static final int DURATION_INDEX_REPORT = 86400000;
    public static final int DURATION_SIS_REQUEST = 180000;
    public static final int DURATION_UPDATE_CONFIG = 86400000;
    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String END_HOUR = "endHour";
    public static final String END_MINS = "endtMins";
    public static final String FIXED_MESSAGE_ICON_NAME = "jpush_notification_icon";
    public static final String HTTP_PRE = "http://";
    public static final String ICON_NAME = "view_ic";
    public static final String IMAG_NAME = "view_ig";
    public static final String INTERNAL_SENDER = "7fef6a7d76c782b1f0eda446b2c6c40a";
    public static final String JPUSH_USER_AGENT = "JPUSH-SDK";
    public static final String LOC_REPORT_TIME = "lctime";
    public static final String LOC_TYPE = "mLocationType";
    public static final String LOG_FILE_NAME_PRE = (LOG_FILE_PATH + LOG_FILE_PRE);
    public static final String LOG_FILE_PATH = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + LOG_FILE_PRE + File.separator);
    public static final String LOG_FILE_PRE = ".jpush";
    public static final int MAX_CACHED_MSG = 200;
    public static final String MESSAGE_JSON = "message";
    public static final String NOTIFICATION_CONTENT_A_INSTALL = "安装包已下载完毕，请点击安装。";
    public static final String NOTIFICATION_CONTENT_UPDATE_INSTALL = "更新版本已下载完毕，请点击安装。";
    public static final int ONE_DAY = 86400000;
    public static final int ONE_HOUR = 3600000;
    public static final int ONE_MINUTE = 60000;
    public static final String PARAM_BODY = "body";
    public static final String PARAM_JS_MODULE = "JPushWeb";
    public static final String PARAM_MESSAGEID = "msg_id";
    public static final String PUSH_MESSAGE_PERMISSION_POSTFIX = ".permission.JPUSH_MESSAGE";
    public static final String SDK_VERSION = "2.1.0";
    public static final String SENDER_ID = "senderId";
    public static final long SIZE_M = 1048576;
    public static final String START_HOUR = "startHour";
    public static final String START_MINS = "startMins";
    public static final int STOPED_RTC_RESTART = 86400;
    public static final String USER_FILE_NAME = "PrefsFile";

    /* renamed from: cn.jpush.android.JPushConstants$DaemonService */
    public interface DaemonService {
        public static final String DAEMON_ACTION = "cn.jpush.android.intent.DaemonService";
    }

    /* renamed from: cn.jpush.android.JPushConstants$JPushReportInterface */
    public interface JPushReportInterface {
        public static final String ANDROID_ID = "android_id";
        public static final String APP_ID = "appid";
        public static final String CONN_ADDR = "conn_addr";
        public static final String DATA = "data";
        public static final String DEFAULT_CONNECT = "default_conn_ip";
        public static final String DURATION = "duration";
        public static final String EVENT = "event";
        public static final String EVENT_DATA = "event_data";
        public static final String EVENT_DURATION = "event_duration";
        public static final String EVENT_ID = "event_id";
        public static final String EVENT_MAP = "event_map";
        public static final String EXCEPTION = "exception";
        public static final String HTTP_REPORT = "default_stat_ip";
        public static final String IMEI = "imei";
        public static final String ITIME = "itime";
        public static final String LAST_DEVICE_INFO_REPORT_TIME = "dev_info_rep_time";
        public static final String LAST_TIME = "last_time";
        public static final String LOCAL_DNS = "local_dns";
        public static final String MAC_ADDRESS = "mac_address";
        public static final String MSG_REPORT = "ad_report";

        /* renamed from: MY */
        public static final String f962MY = "my";
        public static final String NAME = "name";
        public static final String NETWORK_TYPE = "network_type";
        public static final String OPEN_ACTIVITY = "open_activity";
        public static final String PAGE = "page";
        public static final String PKG = "pkg";
        public static final String SDK_VER = "sdk_ver";
        public static final String SENDER_ID = "senderid";
        public static final String TITLE = "title";
        public static final String TOTAL = "total";
        public static final String TYPE = "type";
        public static final String TYPE_APP_ADD_RMV = "app_add_rmv";
        public static final String TYPE_APP_LIST = "app_list";
        public static final String TYPE_LOC = "loc_info";
        public static final String TYPE_MSG = "msg_status";
        public static final String TYPE_PERMISSION = "android_permissions";
        public static final String UID = "uid";
        public static final String URL = "url";
        public static final String VER_CODE = "ver_code";
        public static final String VER_NAME = "ver_name";
        public static final String ZIP_REPORT = "zip_report";
        public static final String ZIP_REPORT_SUBTYPE = "sub_type";
    }

    /* renamed from: cn.jpush.android.JPushConstants$PROTOCOL_COMMAND */
    public enum PROTOCOL_COMMAND {
        KKPUSHCMD_REG,
        KKPUSHCMD_LOGIN,
        KKPUSHCMD_HARTBEAT,
        KKPUSHCMD_MESSAGE,
        KKPUSHCMD_MESSAGEED,
        KKPUSHCMD_LOGOUT,
        KKPUSHCMD_GETCHANNELID,
        KKPUSHCMD_DECCHANNELID,
        KKPUSHCMD_MAX,
        KKPUSHCMD_PUSH_NEW,
        KKPUSHCMD_TAG_ALAIS,
        KKPUSHCMD_ENABLECHNNELID,
        KKPUSHCMD_PUSH_TIME,
        KKPUSH_DEVICETOKEN_REPORT,
        KKPUSHCMD_UNREGCHANNEID,
        KKPUSHCMD_CLIENTSENDMSG,
        KKPUSHCMD_GETCHANNELID2,
        KKPUSHCMD_DECCHANNELID2
    }

    /* renamed from: cn.jpush.android.JPushConstants$PushActivity */
    public interface PushActivity {
        public static final String ACTION_JPUSH = "cn.jpush.android.ui.PushActivity";
        public static final String CATEGORY_1 = "android.intent.category.DEFAULT";
        public static final String IS_UPDATE_VERSION = "isUpdateVersion";
    }

    /* renamed from: cn.jpush.android.JPushConstants$PushReceiver */
    public interface PushReceiver {
        public static final String EXTRA_NOTIFICATION_ACTION = "cn.jpush.android.NOTIFICATION_ACTION";
        public static final String EXTRA_NOTIFICATION_CATEGORY = "cn.jpush.android.NOTIFICATION_CATEGORY";
        public static final String EXTRA_NOTIFICATION_PENDINTENT_TYPE = "cn.jpush.android.NOTIFICATION_PENDINTENT_TYPE";
        public static final String INTENT_NOTIFICATION_INSTALL_CLICKED = "cn.jpush.android.intent.NOTIFICATION_INSTALL_CLICKED";
        public static final String INTENT_NOTIFICATION_OPENED_PROXY = "cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY";
        public static final String SHOW_FLOAT_VIEW_ACTION = "cn.jpush.android.intent.SHOW_FLOAT_VIEW_ACTION";
        public static final String SYSTEM_FULLSCREEN_ACTION = "cn.jpush.android.intent.SYSTEM_FULLSCREEN_ACTION";
        public static final String SYSTEM_FULLSCREEN_CATEGORY = "cn.jpush.android.intent.SYSTEM_FULLSCREEN_CATEGORY";
    }

    /* renamed from: cn.jpush.android.JPushConstants$PushService */
    public interface PushService {
        public static final String ACTION_ALIAS_TAGS = "cn.jpush.android.intent.ALIAS_TAGS";
        public static final String ACTION_CONNECTIVITY_CHANGE = "cn.jpush.android.intent.CONNECTIVITY_CHANGE";
        public static final String ACTION_INIT = "cn.jpush.android.intent.INIT";
        public static final String ACTION_MULTI_PROCESS = "cn.jpush.android.intent.MULTI_PROCESS";
        public static final String ACTION_NOTIFICATION_ENABLED = "cn.jpush.android.intent.PUSH_TIME";
        public static final String ACTION_PUSHSERVICE = "cn.jpush.android.intent.PushService";
        public static final String ACTION_REGISTER = "cn.jpush.android.intent.REGISTER";
        public static final String ACTION_REPORT = "cn.jpush.android.intent.REPORT";
        public static final String ACTION_RESTART_EPUSH = "cn.jpush.android.intent.RESTOREPUSH";
        public static final String ACTION_RTC = "cn.jpush.android.intent.RTC";
        public static final String ACTION_STOP_PUSH = "cn.jpush.android.intent.STOPPUSH";
        public static final String ACTION_UNREGISTER = "cn.jpush.android.intent.UNREGISTER";
        public static final String ACTION_USER_GROUND = "cn.jpush.android.intent.USER_GROUND";
        public static final String CONN_IP = "jpush_conn_mip";
        public static final String CONN_PORT = "jpush_conn_mport";
        public static final String DELAY_TIME = "rtc_delay";
        public static final String IMEI = "imei";
        public static final String PARAM_ALIAS = "alias";
        public static final String PARAM_APP = "app";
        public static final String PARAM_CONNECTIONSTATE = "connection-state";
        public static final String PARAM_LOCAL_NOTIFICATION = "local_notification";
        public static final String PARAM_LOCAL_NOTIFICATION_ID = "local_notification_id";
        public static final String PARAM_MULTI_TYPE = "multi_type";
        public static final String PARAM_NOTI_BUILDER = "notification_buidler";
        public static final String PARAM_NOTI_BUILDER_id = "notification_buidler_id";
        public static final String PARAM_NOTI_MAXNUM = "notification_maxnum";
        public static final String PARAM_PLATFORM = "platform";
        public static final String PARAM_PUSH_STOPPED = "push_stopped";
        public static final String PARAM_PUSH_TIME = "enable_push_time";
        public static final String PARAM_REPORT = "report";
        public static final String PARAM_REPORT_LOC = "loction";
        public static final String PARAM_RESULT = "result";
        public static final String PARAM_SEQUENCE = "sequence";
        public static final String PARAM_SEQ_ID = "seq_id";
        public static final String PARAM_SILENCE_PUSH_TIME = "silence_push_time";
        public static final String PARAM_TAGS = "tags";
        public static final String PARAM_USER_GROUND = "user_ground";
        public static final String REGISTRATION_ID = "registration_id";
        public static final String RESTART_RTC = "restart_rtc";
        public static final String SIS_NETTYPE = "jpush_sis_nettype";
        public static final String SIS_RECEIVER = "jpush_sis_receiver_string";
        public static final String STOP_THREAD = "stop_thread";
        public static final String TAGS_ALIAS = "tags_alias";
        public static final int USER_STATE_BACKGROUND = 0;
        public static final int USER_STATE_FOREGROUND = 1;

        /* renamed from: cn.jpush.android.JPushConstants$PushService$MultiType */
        public interface MultiType {
            public static final int LOCAL_NOTIFICATION_ADD = 6;
            public static final int LOCAL_NOTIFICATION_CLEAR = 8;
            public static final int LOCAL_NOTIFICATION_REMOVE = 7;
            public static final int NOTIFICATION_BUILDER = 1;
            public static final int NOTIFICATION_MAXNUM = 2;
            public static final int PUSH_STOP_EXECUTED = 5;
            public static final int PUSH_TIME = 3;
            public static final int SILENCE_PUSH_TIME = 4;
        }
    }
}
