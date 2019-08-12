package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGroup;

/* renamed from: cn.jpush.proto.common.imcommands.AddMsgnoDisturbGroupRequest */
public class AddMsgnoDisturbGroupRequest extends ImBaseRequest {
    @Expose
    long groupId;
    @Expose
    long version;

    public AddMsgnoDisturbGroupRequest(long groupId2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
    }

    public static AddMsgnoDisturbGroupRequest fromJson(String json) {
        return (AddMsgnoDisturbGroupRequest) _gson.fromJson(json, AddMsgnoDisturbGroupRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(33, 1, imUid, appKey, AddMsgnoDisturbGroup.newBuilder().setGid(this.groupId).build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public long getVersion() {
        return this.version;
    }
}
