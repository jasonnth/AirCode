package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.Group.ExitGroup;

/* renamed from: cn.jpush.proto.common.imcommands.ExitGroupRequest */
public class ExitGroupRequest extends ImBaseRequest {
    @Expose
    long groupId;

    public ExitGroupRequest(long groupId2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
    }

    public static ExitGroupRequest fromJson(String json) {
        return (ExitGroupRequest) _gson.fromJson(json, ExitGroupRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(9, 1, imUid, appKey, ExitGroup.newBuilder().setGid(this.groupId).build());
    }

    public long getGroupId() {
        return this.groupId;
    }
}
