package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.C1623Message.GroupMsg;
import p005cn.jpush.p034im.proto.C1623Message.GroupMsg.Builder;
import p005cn.jpush.p034im.proto.C1623Message.MessageContent;

/* renamed from: cn.jpush.proto.common.imcommands.GroupMsgRequest */
public class GroupMsgRequest extends ImBaseRequest {
    @Expose
    long groupId;
    @Expose
    String msgContent;

    public GroupMsgRequest(long groupId2, String msgContent2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
        this.msgContent = msgContent2;
    }

    public static GroupMsgRequest fromJson(String json) {
        return (GroupMsgRequest) _gson.fromJson(json, GroupMsgRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = GroupMsg.newBuilder().setTargetGid(this.groupId);
        if (this.msgContent != null) {
            builder.setContent(MessageContent.newBuilder().setContent(ByteString.copyFromUtf8(this.msgContent)).build());
        }
        return new IMProtocol(4, 1, imUid, appKey, builder.build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public String getMsgContent() {
        return this.msgContent;
    }
}
