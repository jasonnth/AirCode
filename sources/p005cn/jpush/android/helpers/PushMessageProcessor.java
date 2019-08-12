package p005cn.jpush.android.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.jpush.JsonElement;
import com.google.gson.jpush.JsonParser;
import com.google.gson.jpush.JsonSyntaxException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONObject;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.JPush;
import p005cn.jpush.android.JPushConstants;
import p005cn.jpush.android.JPushConstants.PushService;
import p005cn.jpush.android.api.JPushInterface;
import p005cn.jpush.android.api.JPushInterface.ErrorCode;
import p005cn.jpush.android.data.BasicEntity;
import p005cn.jpush.android.data.Entity.EntityKey;
import p005cn.jpush.android.service.PushProtocol;
import p005cn.jpush.android.service.ServiceInterface;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.android.util.StringUtils;
import p005cn.jpush.android.util.TimeWatcher;
import p005cn.jpush.proto.common.commands.JResponse;
import p005cn.jpush.proto.common.commands.MessagePush;
import p005cn.jpush.proto.common.commands.TagaliasResponse;

/* renamed from: cn.jpush.android.helpers.PushMessageProcessor */
public class PushMessageProcessor {
    private static final int MESSAGE_TYPE_BOTH = 3;
    private static final int MESSAGE_TYPE_MESSAGE = 2;
    private static final int MESSAGE_TYPE_NOTIFICATION = 1;
    public static final byte MSG_TYPE_CRASH_ERROR = 16;
    private static final byte MSG_TYPE_DUPLICATED_DEVICE = 3;
    private static final byte MSG_TYPE_JSON = 2;
    private static final byte MSG_TYPE_MESSAGE = 0;
    public static final byte MSG_TYPE_RESTOREPUSH = 14;
    private static final byte MSG_TYPE_SERVER_CONFIG = 6;
    public static final byte MSG_TYPE_SETALIASANDTAGS = 15;
    private static final int MSG_TYPE_START_LOG = 21;
    private static final int MSG_TYPE_START_RECORDER = 22;
    public static final byte MSG_TYPE_STOPPUSH = 13;
    private static final int MSG_TYPE_TAGALIAS_RESPONSE = 20;
    private static final String TAG = "PushMessageProcessor";
    private static JsonParser _jsonParser = new JsonParser();
    private static Queue<EntityKey> lastMsgQueue = new ConcurrentLinkedQueue();

    public static void parsePushMessage(Context context, Handler mMainHandler, long connection, JResponse response) {
        String msgContent;
        MessagePush push = (MessagePush) response;
        reportReceived(connection, push.getMsgType(), push.getMsgId(), push.getHead().getRid().longValue());
        long msgId = push.getMsgId();
        int msgType = push.getMsgType();
        String msgContent2 = push.getMsgContent();
        Logger.m1416d(TAG, "msgType = " + msgType + ", msgId = " + msgId);
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(msgContent2));
        try {
            String appId = lineNumberReader.readLine();
            if (appId == null) {
                Logger.m1420e(TAG, "NO appId");
                return;
            }
            String senderId = lineNumberReader.readLine();
            if (senderId == null) {
                Logger.m1420e(TAG, "NO senderId");
                return;
            }
            int priorSize = appId.length() + senderId.length() + 2;
            if (msgContent2.length() > priorSize + 1) {
                msgContent = msgContent2.substring(priorSize);
            } else {
                Logger.m1416d(TAG, "No msgContent");
                msgContent = "";
            }
            Logger.m1428v(TAG, "Message Fields - appId:" + appId + ", senderId:" + senderId + ", msgContent:" + msgContent);
            switch (msgType) {
                case 0:
                case 2:
                    try {
                        parseNormal(context, msgType, msgId, appId, senderId, msgContent);
                        return;
                    } catch (Exception e) {
                        Logger.m1421e(TAG, "Unknown error", e);
                        return;
                    }
                case 3:
                case 6:
                case 21:
                case 22:
                    return;
                case 20:
                    Message.obtain(mMainHandler, 1009, new TagaliasResponse(Long.valueOf(getRidFromTagaliasResponse(msgContent)).longValue(), push.getHead().getJuid(), 0, null, 0)).sendToTarget();
                    onRecvTagAliasCallBack(context, msgContent);
                    return;
                default:
                    Logger.m1416d(TAG, "Unexpected: unknown push msg type -" + msgType);
                    return;
            }
        } catch (IOException e2) {
            Logger.m1421e(TAG, "Parse msgContent failed", e2);
        }
    }

    public static long getRidFromTagaliasResponse(String json) {
        try {
            JsonElement root = _jsonParser.parse(json);
            if (!root.isJsonObject()) {
                Logger.m1420e(TAG, "Tagalias response is not a json object");
                return 0;
            }
            JsonElement seq = root.getAsJsonObject().get(PushService.PARAM_SEQUENCE);
            if (seq != null && seq.isJsonPrimitive()) {
                return seq.getAsLong();
            }
            Logger.m1420e(TAG, "Not found sequence in tagalias response.");
            return 0;
        } catch (JsonSyntaxException e) {
            Logger.m1420e(TAG, "Parse tagalias json error.");
            return 0;
        }
    }

    private static void onRecvTagAliasCallBack(Context context, String msgContent) {
        try {
            JSONObject callback = new JSONObject(msgContent);
            int code = callback.optInt("code", ErrorCode.UNKNOWN_ERROR);
            long rid = callback.optLong(PushService.PARAM_SEQUENCE);
            Intent tagAliasIntent = new Intent();
            tagAliasIntent.addCategory(JPush.PKG_NAME);
            tagAliasIntent.setAction(ServiceInterface.ACTION_TAG_ALIAS_CALLBACK);
            tagAliasIntent.putExtra(ServiceInterface.EXTRA_TAGALIAS_CALLBACKCODE, code);
            tagAliasIntent.putExtra(ServiceInterface.EXTRA_TAGALIAS_SEQID, rid);
            context.sendBroadcast(tagAliasIntent);
        } catch (Exception e) {
            Logger.m1432w(TAG, "tagalias msgContent:" + msgContent);
        }
    }

    private static void parseNormal(Context context, int msgType, long msgId, String appId, String senderId, String msgContent) throws Exception {
        Logger.m1416d(TAG, "parseNormal -  msgId:" + msgId);
        if (ServiceInterface.isServiceStoped(context)) {
            Logger.m1424i(TAG, "Service is stoped, give up all the message");
            return;
        }
        TimeWatcher tw = new TimeWatcher(TAG, "Time to process received msg.");
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(senderId)) {
            Logger.m1432w(TAG, "Empty senderid or appid. Give up parser.");
        } else if (!TextUtils.isEmpty(msgContent)) {
            processMessage(context, appId, senderId, msgContent, msgId);
        } else {
            Logger.m1420e(TAG, "Empty msg. Give up parser.");
        }
        tw.show();
    }

    private static void processMessage(Context context, String app, String sender, String message, long msgId) {
        Logger.m1416d(TAG, "action:receivedPushMessage msgId = " + msgId);
        BasicEntity basicEntity = ProtocolHelper.preParseOriginalMsgMessage(context, message, app, sender, "" + msgId);
        if (basicEntity != null) {
            EntityKey entityKey = basicEntity.getEntityKey();
            if (lastMsgQueue.contains(entityKey)) {
                Logger.m1420e(TAG, "Duplicated msg. Give up processing - " + entityKey);
                return;
            }
            if (lastMsgQueue.size() >= 200) {
                lastMsgQueue.poll();
            }
            lastMsgQueue.offer(entityKey);
            int messageType = 0;
            if (sender.equalsIgnoreCase(JPushConstants.INTERNAL_SENDER)) {
                ProtocolHelper.parseMsgMessage(context, basicEntity);
            } else if (basicEntity.notificationOnly) {
                messageType = 1;
                if (basicEntity.messageVersion == 4) {
                    messageType = 3;
                }
            } else {
                messageType = 2;
            }
            processBasicEntity(context, basicEntity, sender, app, message, messageType, "" + msgId);
        }
    }

    private static void processBasicEntity(Context context, BasicEntity basicEntity, String sender, String app, String message, int messageType, String msgId) {
        Logger.m1416d(TAG, "processBasicEntity type:" + messageType);
        if ((messageType & 1) != 0) {
            Logger.m1416d(TAG, "processBasicEntity notification");
            Intent msgIntent = new Intent(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY);
            msgIntent.putExtra(JPushConstants.SENDER_ID, sender);
            msgIntent.putExtra(JPushConstants.APP_ID, app);
            msgIntent.putExtra("message", message);
            msgIntent.putExtra("msg_id", msgId);
            msgIntent.putExtra(JPushInterface.EXTRA_NOTI_TYPE, basicEntity.notificationType);
            msgIntent.addCategory(app);
            context.sendOrderedBroadcast(msgIntent, app + JPushConstants.PUSH_MESSAGE_PERMISSION_POSTFIX);
            return;
        }
        Logger.m1416d(TAG, "processBasicEntity user-defined message.");
        if (!StringUtils.isEmpty(basicEntity.message) || !StringUtils.isEmpty(basicEntity.extras)) {
            AndroidUtil.sendBroadcastToApp(context, basicEntity);
        }
    }

    private static void reportReceived(long connection, int msgType, long msgId, long rid) {
        if (PushProtocol.MsgResponse(connection, 0, Configs.getUid(), (byte) msgType, msgId, rid, Configs.getSid()) != 0) {
            Logger.m1416d(TAG, "Failed to report received.");
        } else {
            Logger.m1416d(TAG, "Succeed to report received - " + msgId);
        }
    }
}
