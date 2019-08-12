package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.p034im.proto.Group.AddGroupMember;

/* renamed from: cn.jpush.proto.common.imcommands.AddGroupMemberRequest */
public class AddGroupMemberRequest extends ImBaseRequest {
    @Expose
    long groupId;
    @Expose
    List<Long> uidList;

    public AddGroupMemberRequest(long groupId2, List<Long> uidList2, long rid, long uid) {
        this.cmd = 10;
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
        this.uidList = uidList2;
    }

    public AddGroupMemberRequest(long groupId2, long uid) {
        this.cmd = 10;
        this.groupId = groupId2;
        this.uidList = new ArrayList();
        this.uidList.add(Long.valueOf(uid));
    }

    public static AddGroupMemberRequest fromJson(String json) {
        return (AddGroupMemberRequest) _gson.fromJson(json, AddGroupMemberRequest.class);
    }

    /* access modifiers changed from: protected */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(10, 1, imUid, appKey, AddGroupMember.newBuilder().setGid(this.groupId).addAllMemberUidlist(this.uidList).setMemberCount(this.uidList.size()).build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public List<Long> getUidList() {
        return this.uidList;
    }
}
