package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGroup;

/* renamed from: cn.jpush.proto.common.imcommands.DeleteMsgnoDisturbGroupRequest */
public class DeleteMsgnoDisturbGroupRequest extends ImBaseRequest {
    @Expose
    long groupId;
    @Expose
    long version;

    public DeleteMsgnoDisturbGroupRequest(long groupId2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
    }

    public static DeleteMsgnoDisturbGroupRequest fromJson(String json) {
        return (DeleteMsgnoDisturbGroupRequest) _gson.fromJson(json, DeleteMsgnoDisturbGroupRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(34, 1, imUid, appKey, DeleteMsgnoDisturbGroup.newBuilder().setGid(this.groupId).build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public long getVersion() {
        return this.version;
    }
}
