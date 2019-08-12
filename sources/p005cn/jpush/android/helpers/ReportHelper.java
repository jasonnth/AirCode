package p005cn.jpush.android.helpers;

import android.content.Context;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.helpers.ReportHelper */
public class ReportHelper {
    private static final String TAG = "ReportHelper";

    public static void reportMsgResult(String msgId, int resultCode, Context context) {
        reportMsgActionResult(msgId, resultCode, null, context);
    }

    public static void reportMsgActionResult(String messageId, int resultCode, String jsonContent, Context context) {
        if (!Configs.isValidRegistered()) {
            Logger.m1428v(TAG, "JPush is inValidRegistered");
        } else if (context == null) {
            Logger.m1416d(TAG, "context did not init, return");
        } else {
            StringBuffer logMsg = new StringBuffer();
            logMsg.append("action:reportActionResult - messageId: " + messageId + ", code: " + resultCode + "-" + StatusCode.getReportDesc(resultCode));
            if (!StringUtils.isEmpty(jsonContent)) {
                logMsg.append(" report content: " + jsonContent);
            }
            Logger.m1416d(TAG, logMsg.toString());
        }
    }

    public static void reportOperation(Context context, JSONObject jsonObject) {
        if (Configs.isValidRegistered()) {
            if (context == null) {
                throw new IllegalArgumentException("NULL context");
            } else if (jsonObject != null && jsonObject.length() > 0) {
                Logger.m1416d(TAG, "action:reportOperation - content:" + jsonObject.toString());
            }
        }
    }
}
