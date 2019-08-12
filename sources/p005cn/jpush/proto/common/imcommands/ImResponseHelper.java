package p005cn.jpush.proto.common.imcommands;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import p005cn.jpush.android.Configs;
import p005cn.jpush.android.helpers.ServiceHelper;
import p005cn.jpush.android.util.AndroidUtil;
import p005cn.jpush.android.util.Logger;
import p005cn.jpush.p034im.proto.C1621Im.Response;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsg;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync.Builder;
import p005cn.jpush.p034im.proto.C1623Message.EventNotification;
import p005cn.jpush.proto.common.commands.IMRequest;
import p005cn.jpush.proto.common.commands.IMResponse;
import p005cn.jpush.proto.common.commands.JResponse;

/* renamed from: cn.jpush.proto.common.imcommands.ImResponseHelper */
public class ImResponseHelper {
    public static final String ACTION_IM_RESPONSE = "cn.jpush.im.android.action.IM_RESPONSE";
    public static final String EXTRA_IM_CMD = "im_cmd";
    public static final String EXTRA_IM_RESPONSE = "im_response";
    public static final String EXTRA_IM_TIMEOUT = "im_timeout";
    public static final String EXTRA_LOGIN_LOCAL_TIME = "push_login_local_time";
    public static final String EXTRA_LOGIN_SERVER_TIME = "push_login_server_time";
    public static final String EXTRA_NETWORK_CONNECTED = "push_network_connected";
    public static final String EXTRA_PUSH2IM_DATA = "push_to_im_data";
    public static final String EXTRA_RID = "rid";
    public static final int MSG_CHAT_MSG_SYNC_BACK = 7502;
    public static final int MSG_EVENT_NOTIFICATION_BACK = 7501;
    private static final String TAG = "ImResponseHelper";

    public static void handleImResponse(Context context, Handler mainHandler, JResponse response, byte[] buf) {
        IMResponse imResponse = (IMResponse) response;
        IMProtocol imProtocol = imResponse.getIMProtocol();
        if (imProtocol == null) {
            Logger.m1420e(TAG, "imProtocol is null, maybe caused by error IM cmd in IMProtocol(byte[] data)");
            return;
        }
        int imCMD = imProtocol.getCommand();
        long rid = imResponse.getHead().getRid().longValue();
        Logger.m1418dd(TAG, "Action - handleImResponse - imCmd:" + imCMD + ", rid:" + rid);
        switch (imCMD) {
            case 1:
                Response resp = imProtocol.getResponse();
                if (resp != null && resp.getCode() == 0) {
                    Logger.m1416d(TAG, "IM login success!");
                    Configs.setImLoggedIn(true);
                    break;
                } else {
                    Logger.m1416d(TAG, "IM login failed!");
                    break;
                }
                break;
            case 2:
                Logger.m1416d(TAG, "IM logout success");
                Configs.setImLoggedIn(false);
                ServiceHelper.resetPushStatus(context);
                break;
            case 13:
                sendEventBack(mainHandler, rid, imProtocol);
                break;
            case 14:
                sendChatMsgSyncBack(mainHandler, rid, imProtocol);
                break;
        }
        AndroidUtil.sendBroadcast(context, ACTION_IM_RESPONSE, EXTRA_IM_RESPONSE, buf);
    }

    private static void sendEventBack(Handler mainHandler, long rid, IMProtocol imProtocol) {
        EventNotification event = (EventNotification) imProtocol.getEntity();
        Logger.m1416d(TAG, "Action - sendEventBack - rid:" + rid + ", eventId:" + event.getEventId());
        imProtocol.setEntity(EventNotification.newBuilder().setEventId(event.getEventId()).setEventType(event.getEventType()).setFromUid(event.getFromUid()).setGid(event.getGid()).build());
        Message.obtain(mainHandler, MSG_EVENT_NOTIFICATION_BACK, new IMRequest(rid, imProtocol)).sendToTarget();
    }

    private static void sendChatMsgSyncBack(Handler mainHandler, long rid, IMProtocol imProtocol) {
        Logger.m1416d(TAG, "Action - sendChatMsgSyncBack - rid:" + rid);
        ChatMsgSync chatMsgSync = (ChatMsgSync) imProtocol.getEntity();
        Builder respChatMsgSyncBuilder = ChatMsgSync.newBuilder();
        for (ChatMsg msg : chatMsgSync.getChatMsgList()) {
            Logger.m1428v(TAG, "ChatMsg Received - msgId:" + msg.getMsgid());
            respChatMsgSyncBuilder.addChatMsg(ChatMsg.newBuilder().setMsgid(msg.getMsgid()).setMsgType(msg.getMsgType()).setFromUid(msg.getFromUid()).setFromGid(msg.getFromGid()).build());
        }
        imProtocol.setEntity(respChatMsgSyncBuilder.build());
        Message.obtain(mainHandler, MSG_CHAT_MSG_SYNC_BACK, new IMRequest(rid, imProtocol)).sendToTarget();
    }
}
