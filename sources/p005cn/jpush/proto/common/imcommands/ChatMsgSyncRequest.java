package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsg;
import p005cn.jpush.p034im.proto.C1623Message.ChatMsgSync;

/* renamed from: cn.jpush.proto.common.imcommands.ChatMsgSyncRequest */
public class ChatMsgSyncRequest extends ImBaseRequest {
    @Expose
    long msgId;

    public ChatMsgSyncRequest(long msgId2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.msgId = msgId2;
    }

    public static ChatMsgSyncRequest fromJson(String json) {
        return (ChatMsgSyncRequest) _gson.fromJson(json, ChatMsgSyncRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(4, 1, imUid, appKey, ChatMsgSync.newBuilder().addChatMsg(ChatMsg.newBuilder().setMsgid(this.msgId).build()).build());
    }

    public long getMsgId() {
        return this.msgId;
    }
}
