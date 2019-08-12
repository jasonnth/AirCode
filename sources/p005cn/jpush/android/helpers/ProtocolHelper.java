package p005cn.jpush.android.helpers;

import android.content.Context;
import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.data.BasicEntity;
import p005cn.jpush.android.data.Entity;
import p005cn.jpush.android.data.Entity.EntityKey;
import p005cn.jpush.android.data.PkEntity;
import p005cn.jpush.android.data.ShowEntity;
import p005cn.jpush.android.data.UpdateEntity;
import p005cn.jpush.android.data.VideoEntity;
import p005cn.jpush.android.service.StatusCode;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.HttpHelper;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;

/* renamed from: cn.jpush.android.helpers.ProtocolHelper */
public class ProtocolHelper {
    private static final int MAX_CACHED_MSG = 200;
    private static final int MESSAGE_TYPE_BOTH = 3;
    private static final int MESSAGE_TYPE_MESSAGE = 2;
    private static final int MESSAGE_TYPE_NOTIFICATION = 1;
    private static final String TAG = "ProtocolHelper";
    private static Queue<EntityKey> lastMsgQueue = new LinkedList();

    public static BasicEntity preParseOriginalMsgMessage(Context context, String originalJson, String app, String sender, String msgid) {
        boolean notificationOnly = true;
        Logger.m1416d(TAG, "action:preParseOriginalMsgMessage - originalJson:\n" + originalJson);
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(originalJson)) {
            Logger.m1420e(TAG, "Empty original json txt");
            ReportHelper.reportMsgResult("NO MSGID", 996, context);
            return null;
        } else {
            JSONObject topJsonObject = getJsonObject(context, "NO_MSGID", originalJson);
            if (topJsonObject == null) {
                Logger.m1416d(TAG, "topJsonObject == null");
                return null;
            }
            String msgId = topJsonObject.optString("msg_id", "");
            if (StringUtils.isEmpty(msgId)) {
                msgId = msgid;
            }
            if (StringUtils.isEmpty(msgId)) {
                msgId = topJsonObject.optString(Entity.KEY_MESSAGE_ID, "");
            }
            Logger.m1416d(TAG, "preParseOriginalMsgMessage msgId = " + msgId);
            if (topJsonObject.optInt(Entity.KEY_NOTIFICATION_ONLY, 0) != 1) {
                notificationOnly = false;
            }
            int notificationOnlyId = 0;
            if (notificationOnly) {
                notificationOnlyId = topJsonObject.optInt(Entity.KEY_NOTIFICATION_ONLY_ID, 0);
            }
            BasicEntity msgEntity = new BasicEntity();
            msgEntity.messageId = msgId;
            msgEntity.topJsonObject = topJsonObject;
            msgEntity.messageVersion = topJsonObject.optInt(Entity.KEY_PROTOCOL_VERSION, 3);
            msgEntity.notificationOnly = notificationOnly;
            msgEntity.notificationBuilderId = notificationOnlyId;
            msgEntity.notificationType = topJsonObject.optInt(Entity.KEY_NOTIFICATION_TYPE, 0);
            msgEntity.message = topJsonObject.optString("message", "");
            msgEntity.contentType = topJsonObject.optString(Entity.KEY_CONTENT_TYPE, "");
            msgEntity.title = topJsonObject.optString("title", "");
            msgEntity.extras = topJsonObject.optString("extras", "");
            msgEntity.appId = app;
            msgEntity.senderId = sender;
            msgEntity.overrideMessageId = topJsonObject.optString(Entity.KEY_OVERRIDE_MESSAGE_ID, "");
            return msgEntity;
        }
    }

    public static void parseMsgMessage(Context context, BasicEntity basicMsgEntity) {
        Logger.m1428v(TAG, "action:parseMsgMessage");
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        int msgProtocolVersion = basicMsgEntity.messageVersion;
        JSONObject topJsonObject = basicMsgEntity.topJsonObject;
        String msgId = basicMsgEntity.messageId;
        if (msgProtocolVersion == 3 || msgProtocolVersion == 4) {
            JSONObject secondJsonObject = getJsonObject(context, msgId, topJsonObject, Entity.KEY_MESSAGE_BODY_CONTENT);
            if (secondJsonObject == null) {
                Logger.m1432w(TAG, "The secondJsonObject is null!");
                return;
            }
            int msgType = secondJsonObject.optInt(Entity.KEY_MSG_TYPE, -1);
            if (msgType == 0) {
                Entity theMsgEntity = new ShowEntity();
                theMsgEntity.messageId = msgId;
                theMsgEntity.messageVersion = msgProtocolVersion;
                theMsgEntity.type = msgType;
                theMsgEntity.isDeveloperMessage = basicMsgEntity.isDeveloperMessage;
                theMsgEntity.notificationOnly = basicMsgEntity.notificationOnly;
                theMsgEntity.notificationBuilderId = basicMsgEntity.notificationBuilderId;
                theMsgEntity.appId = basicMsgEntity.appId;
                theMsgEntity.overrideMessageId = basicMsgEntity.overrideMessageId;
                theMsgEntity.notificationType = basicMsgEntity.notificationType;
                boolean isParseSucceed = theMsgEntity.parse(context, secondJsonObject);
                Logger.m1428v(TAG, "Entity.parse the second json object over.");
                if (isParseSucceed) {
                    theMsgEntity.process(context);
                    Logger.m1428v(TAG, "ShowEntity.process over.");
                    return;
                }
                Logger.m1432w(TAG, "Push message parsing failed. Give up processing.");
                return;
            }
            Logger.m1432w(TAG, "Unknow msg type ad_t = " + msgType);
            ReportHelper.reportMsgResult(msgId, 996, context);
            return;
        }
        Logger.m1416d(TAG, "Unknown MSG protocol version. Give up - " + msgProtocolVersion);
        ReportHelper.reportMsgResult(msgId, 996, context);
    }

    public static void parseOriginalMsgMessage(Context context, String originalJson) {
        Entity theMsgEntity;
        Logger.m1428v(TAG, "action:parseOriginalMsgMessage - originalJson:\n" + originalJson);
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        } else if (TextUtils.isEmpty(originalJson)) {
            Logger.m1420e(TAG, "Empty original json txt");
        } else {
            JSONObject topJsonObject = getJsonObject(context, "NO MSGID", originalJson);
            if (topJsonObject != null) {
                String msgId = topJsonObject.optString("msg_id", "");
                if (StringUtils.isEmpty(msgId)) {
                    msgId = topJsonObject.optString(Entity.KEY_MESSAGE_ID, "");
                }
                int msgProtocolVersion = topJsonObject.optInt(Entity.KEY_PROTOCOL_VERSION, -1);
                JSONObject secondJsonObject = null;
                if (msgProtocolVersion == 2) {
                    String url = topJsonObject.optString(Entity.KEY_MESSAGE_BODY_CONTENT, "").trim();
                    if (checkValidUrl(url)) {
                        loadMsgJsonFromUrl(context, url, msgId);
                        return;
                    }
                    Logger.m1416d(TAG, "Failed to get json from url becauseof invalid url - " + url);
                    ReportHelper.reportMsgResult(msgId, 996, context);
                    return;
                }
                if (msgProtocolVersion == 1) {
                    secondJsonObject = getJsonObject(context, msgId, topJsonObject, Entity.KEY_MESSAGE_BODY_CONTENT);
                }
                if (secondJsonObject != null) {
                    int msgType = secondJsonObject.optInt(Entity.KEY_MSG_TYPE, -1);
                    switch (msgType) {
                        case 0:
                            theMsgEntity = new ShowEntity();
                            break;
                        case 1:
                            theMsgEntity = new PkEntity();
                            break;
                        case 2:
                            theMsgEntity = new VideoEntity();
                            break;
                        case 3:
                            theMsgEntity = new UpdateEntity();
                            break;
                        default:
                            Logger.m1432w(TAG, "Unknow msg type - " + msgType);
                            ReportHelper.reportMsgResult(msgId, 996, context);
                            return;
                    }
                    boolean isParseSucceed = theMsgEntity.parse(context, secondJsonObject);
                    Logger.m1428v(TAG, "Parse end");
                    theMsgEntity.messageId = msgId;
                    theMsgEntity.messageVersion = msgProtocolVersion;
                    theMsgEntity.type = msgType;
                    if (isParseSucceed) {
                        theMsgEntity.process(context);
                        Logger.m1428v(TAG, "Process end");
                        return;
                    }
                    Logger.m1432w(TAG, "Push message parsing failed. Give up processing.");
                }
            }
        }
    }

    public static JSONObject getJsonObject(Context context, String msgId, String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            Logger.m1421e(TAG, "parse json error", e);
            ReportHelper.reportMsgResult(msgId, 996, context);
            return null;
        }
    }

    public static JSONObject getJsonObject(Context context, String msgId, JSONObject json, String name) {
        if (json == null) {
            Logger.m1432w(TAG, "NULL json object");
            ReportHelper.reportMsgResult(msgId, 996, context);
            return null;
        } else if (TextUtils.isEmpty(name)) {
            Logger.m1432w(TAG, "Empty json name to get");
            return null;
        } else {
            try {
                if (!json.isNull(name)) {
                    return json.getJSONObject(name);
                }
                return null;
            } catch (JSONException e) {
                Logger.m1421e(TAG, "get json object error", e);
                ReportHelper.reportMsgResult(msgId, 996, context);
                return null;
            }
        }
    }

    public static boolean checkValidUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return false;
        }
        String url2 = url.trim();
        boolean ret = url2.matches("^[http|https]+://.*");
        if (ret) {
            return ret;
        }
        Logger.m1432w(TAG, "Invalid url - " + url2);
        return ret;
    }

    private static void loadMsgJsonFromUrl(final Context context, final String urlstr, final String msgId) {
        Logger.m1428v(TAG, "action:loadMsgJsonFromUrl - " + urlstr);
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        new Thread() {
            public void run() {
                boolean success;
                String msg = "";
                int i = 0;
                while (true) {
                    success = false;
                    if (i >= 4) {
                        break;
                    }
                    i++;
                    msg = HttpHelper.httpSimpleGet(urlstr, 5, 8000);
                    if (!HttpHelper.checkHttpIsError(msg)) {
                        success = true;
                        break;
                    }
                }
                if (!success || TextUtils.isEmpty(msg)) {
                    ReportHelper.reportMsgActionResult(msgId, StatusCode.RESULT_TYPE_HTML_LOAD_FAIL, AndroidUtil.getDownloadFailedClientInfo(context, urlstr), context);
                    ReportHelper.reportMsgResult(msgId, 996, context);
                    Logger.m1416d(ProtocolHelper.TAG, "Failed to load json from url");
                    return;
                }
                ProtocolHelper.parseOriginalMsgMessage(context, msg);
            }
        }.start();
    }

    private static boolean isPkUrl(String url) {
        boolean z = false;
        if (TextUtils.isEmpty(url)) {
            return z;
        }
        try {
            return new URL(url).getPath().matches(".*(.(a|A)(p|P)(k|K))$");
        } catch (MalformedURLException e) {
            Logger.m1421e(TAG, "", e);
            return z;
        }
    }

    public static boolean isNeedSDownload(boolean needSilence, int networkType, Context context) {
        boolean isCurrentWifi = JPushConstants.AUTO_DOWN_NET.equalsIgnoreCase(AndroidUtil.getConnectedTypeName(context));
        if (needSilence && networkType == 0) {
            return true;
        }
        if (!needSilence || networkType != 1 || !isCurrentWifi) {
            return false;
        }
        return true;
    }
}
