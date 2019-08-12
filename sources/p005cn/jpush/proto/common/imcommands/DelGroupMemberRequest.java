package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.p034im.proto.Group.DelGroupMember;

/* renamed from: cn.jpush.proto.common.imcommands.DelGroupMemberRequest */
public class DelGroupMemberRequest extends ImBaseRequest {
    @Expose
    long groupId;
    @Expose
    List<Long> uidList;

    public DelGroupMemberRequest(long groupId2, List<Long> uidList2, long rid, long uid) {
        this.cmd = 11;
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
        this.uidList = uidList2;
    }

    public DelGroupMemberRequest(long groupId2, long uid) {
        this.cmd = 11;
        this.groupId = groupId2;
        this.uidList = new ArrayList();
        this.uidList.add(Long.valueOf(uid));
    }

    public static DelGroupMemberRequest fromJson(String json) {
        return (DelGroupMemberRequest) _gson.fromJson(json, DelGroupMemberRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(11, 1, imUid, appKey, DelGroupMember.newBuilder().setGid(this.groupId).setMemberCount(this.uidList.size()).addAllMemberUidlist(this.uidList).build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public List<Long> getUidList() {
        return this.uidList;
    }
}
