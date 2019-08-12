package p005cn.jpush.android.service;

import android.text.TextUtils;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import com.facebook.react.modules.appstate.AppStateModule;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;
import p005cn.jpush.android.util.Logger;

/* renamed from: cn.jpush.android.service.StatusCode */
public class StatusCode {
    public static final int ERRCODE_BYTEBUFFER_PARSE = 10000;
    public static final int ERRCODE_CLOSE = -996;
    public static final int ERRCODE_CONN = -1000;
    public static final int ERRCODE_INVALIDSOCK = -993;
    public static final int ERRCODE_INVALID_APPKEY = 1008;
    public static final int ERRCODE_OK = 0;
    public static final int ERRCODE_RECV = -997;
    public static final int ERRCODE_REGISTER = 11;
    public static final int ERRCODE_SEND = -998;
    public static final int ERRCODE_SENDBUFF = -1001;
    public static final int ERRCODE_STOPED = -991;
    public static final int ERRCODE_TIMEOUT = -994;
    public static final int ERROCDE_APPKEY_PKG_UNMATCHED = 1005;
    public static final int ERROCDE_INVALID_IMEI = 1007;
    public static final int ERROCDE_PACKAGENAME_NOTEXIST = 1006;
    public static final int ERROCDE_USING_NON_ANDROID_APPKEY = 1009;
    public static final int REPORT_RECEIVED_SUCCEED = 1002;
    public static final int RESULT_TPPE_INVALID_PARAM = 1100;
    public static final int RESULT_TYPE_AUTO_DOWN_SUCCEED = 1003;
    public static final int RESULT_TYPE_A_FOUND_NOT_RECEIVED = 997;
    public static final int RESULT_TYPE_A_FOUND_RECEIVED = 998;
    public static final int RESULT_TYPE_A_LOAD_FAIL = 1022;
    public static final int RESULT_TYPE_BTN_CANCEL = 1006;
    public static final int RESULT_TYPE_BTN_OK = 1007;
    public static final int RESULT_TYPE_CLCIK_APPLIST = 1019;
    public static final int RESULT_TYPE_CLICK = 1000;
    public static final int RESULT_TYPE_CLICK_CALL = 1017;
    public static final int RESULT_TYPE_CLICK_CONTENT = 1016;
    public static final int RESULT_TYPE_DISCARD_TIME_CLOSED = 1030;
    public static final int RESULT_TYPE_DOWN_AGAIN = 1012;
    public static final int RESULT_TYPE_DOWN_EXIST = 1013;
    public static final int RESULT_TYPE_DOWN_FAIL = 1011;
    public static final int RESULT_TYPE_DOWN_SUCCEED = 1001;
    public static final int RESULT_TYPE_HTML_LOAD_FAIL = 1021;
    public static final int RESULT_TYPE_IMAGE_LOAD_FAIL = 1020;
    public static final int RESULT_TYPE_JSON_LOAD_FAIL = 996;
    public static final int RESULT_TYPE_JSON_LOAD_SUC = 995;
    public static final int RESULT_TYPE_NOTIFACTION_OPENED = 1028;
    public static final int RESULT_TYPE_NOTIFACTION_SHOW = 1018;
    public static final int RESULT_TYPE_NOTIFICATION_INSTALL_CLICKED = 1015;
    public static final int RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED = 1014;
    public static final int RESULT_TYPE_RESUME_PUSH = 1032;
    public static final int RESULT_TYPE_STOP_PUSH = 1031;
    public static final int RESULT_TYPE_VIDEO_CLICK = 1005;
    public static final int RESULT_TYPE_VIDEO_CLICK_CLOSE = 1008;
    public static final int RESULT_TYPE_VIDEO_DOWN_SUCEED = 1004;
    private static long _lastUserPresentTime = 0;
    private static final HashMap<Integer, String> errorMap = new HashMap<>();
    private static final HashMap<Integer, String> reportCodeMap = new HashMap<>();

    static {
        errorMap.put(Integer.valueOf(0), OfficialIdStatusResponse.f1090OK);
        errorMap.put(Integer.valueOf(ERRCODE_SENDBUFF), "Exceed buffer size. Please contact support.");
        errorMap.put(Integer.valueOf(ERRCODE_CONN), "Connection failed. Please check your connection and retry later!");
        errorMap.put(Integer.valueOf(ERRCODE_SEND), "Sending failed or timeout. Please Retry later!");
        errorMap.put(Integer.valueOf(ERRCODE_RECV), "Receiving failed or timeout. Please Retry later!");
        errorMap.put(Integer.valueOf(ERRCODE_CLOSE), "Connection is closed. Please Retry later!");
        errorMap.put(Integer.valueOf(ERRCODE_TIMEOUT), "Response timeout. Please Retry later!");
        errorMap.put(Integer.valueOf(ERRCODE_INVALIDSOCK), "Invalid socket. Please Retry later!");
        errorMap.put(Integer.valueOf(11), "Failed to register!");
        errorMap.put(Integer.valueOf(1005), "Your appKey and android package name are not matched. Please double check them according to Application you created on Portal.");
        errorMap.put(Integer.valueOf(1006), "You android package name is not exist, Please register your pacakge name in Portal.");
        errorMap.put(Integer.valueOf(1007), "Invalid Imei, Register again.");
        errorMap.put(Integer.valueOf(1009), "Your appKey is related to a non-Android App.Please use your Android App's appKey, or add an Android app for this appKey.");
        errorMap.put(Integer.valueOf(10000), "Receiver data parse error");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_JSON_LOAD_SUC), "Message JSON parsing succeed");
        reportCodeMap.put(Integer.valueOf(996), "Message JSON parsing failed");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_A_FOUND_NOT_RECEIVED), "Message already received, give up");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_A_FOUND_RECEIVED), "Message already received, still process");
        reportCodeMap.put(Integer.valueOf(1000), "User clicked and opened the Message");
        reportCodeMap.put(Integer.valueOf(1001), "Message download succeed");
        reportCodeMap.put(Integer.valueOf(1002), "Message received succeed");
        reportCodeMap.put(Integer.valueOf(1003), "Message silence download succeed");
        reportCodeMap.put(Integer.valueOf(1004), "Video silence downlaod succeed");
        reportCodeMap.put(Integer.valueOf(1005), "User clicked video and jumped to url Message (browser)");
        reportCodeMap.put(Integer.valueOf(1008), "Video is force closed by user");
        reportCodeMap.put(Integer.valueOf(1007), "User clicked 'OK'");
        reportCodeMap.put(Integer.valueOf(1006), "User clicked 'Cancel'");
        reportCodeMap.put(Integer.valueOf(1011), "Download failed");
        reportCodeMap.put(Integer.valueOf(1012), "User clicked to download again");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_DOWN_EXIST), "The file already exist and same size. Don't download again.");
        reportCodeMap.put(Integer.valueOf(RESULT_TPPE_INVALID_PARAM), "Invalid param or unexpected result.");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_RESOURCE_REQUIRED_PRELOAD_FAILED), "Failed to preload required resource");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_NOTIFICATION_INSTALL_CLICKED), "User clicked install alert on status bar after downloading finished.");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_CLICK_CONTENT), "User clicked the webview's url");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_CLICK_CALL), "User clicked call action");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_NOTIFACTION_SHOW), "The Message show in the status bar");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_CLCIK_APPLIST), "Click applist and show the Message");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_IMAGE_LOAD_FAIL), "Down image failed");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_HTML_LOAD_FAIL), "Down html failed");
        reportCodeMap.put(Integer.valueOf(1022), "Down Message failed");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_DISCARD_TIME_CLOSED), "Discard the message because it is not in the push time");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_STOP_PUSH), "Stop push service");
        reportCodeMap.put(Integer.valueOf(RESULT_TYPE_RESUME_PUSH), "Resume push service");
    }

    public static String getErrorDesc(int code) {
        if (errorMap.containsKey(Integer.valueOf(code))) {
            return (String) errorMap.get(Integer.valueOf(code));
        }
        Logger.m1416d("StatusCode", "Unknown error code - " + code);
        return null;
    }

    public static String getReportDesc(int code) {
        if (reportCodeMap.containsKey(Integer.valueOf(code))) {
            return (String) reportCodeMap.get(Integer.valueOf(code));
        }
        Logger.m1416d("StatusCode", "Unknown report code - " + code);
        return "";
    }

    public static JSONObject buildPackageAddedReportContent(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        try {
            JSONObject o = new JSONObject();
            o.put("action", "add");
            o.put(JPushReportInterface.APP_ID, packageName);
            o.put("type", JPushReportInterface.TYPE_APP_ADD_RMV);
            return o;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject buildPackageRemovedReportContent(String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }
        try {
            JSONObject o = new JSONObject();
            o.put("action", "rmv");
            o.put(JPushReportInterface.APP_ID, packageName);
            o.put("type", JPushReportInterface.TYPE_APP_ADD_RMV);
            return o;
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONObject buildUserActiveReportContent(boolean isActive) {
        int useTime = 0;
        if (isActive) {
            _lastUserPresentTime = System.currentTimeMillis();
        } else if (_lastUserPresentTime != 0) {
            useTime = (int) ((System.currentTimeMillis() - _lastUserPresentTime) / 1000);
        }
        JSONObject userActive = new JSONObject();
        try {
            JSONObject o = new JSONObject();
            o.put(AppStateModule.APP_STATE_ACTIVE, isActive ? 1 : 0);
            o.put(ErfExperimentsModel.TIMESTAMP, System.currentTimeMillis());
            if (!isActive) {
                o.put("use_time", useTime);
            }
            userActive.put("user_active", o);
        } catch (JSONException e) {
        }
        return userActive;
    }

    public static String buildUnexpectedErrorContent(String packageName, String errorDescription) {
        return buildUnexpectedErrorContent(packageName, errorDescription, null, null);
    }

    public static String buildUnexpectedErrorContent(String packageName, String errorDescription, String msgId, String senderId) {
        if (TextUtils.isEmpty(packageName)) {
            return "";
        }
        JSONObject o = new JSONObject();
        try {
            o.put("error", errorDescription);
            o.put(JPushReportInterface.APP_ID, packageName);
            if (msgId != null) {
                o.put("message_id", msgId);
            }
            if (senderId != null) {
                o.put("sender", senderId);
            }
        } catch (JSONException e) {
        }
        return o.toString();
    }
}
