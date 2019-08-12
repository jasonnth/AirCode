package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.Friend.DelFriend;

/* renamed from: cn.jpush.proto.common.imcommands.DelFriendRequest */
public class DelFriendRequest extends ImBaseRequest {
    @Expose
    long targetUid;

    public DelFriendRequest(long targetUid2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
    }

    public static DelFriendRequest fromJson(String json) {
        return (DelFriendRequest) _gson.fromJson(json, DelFriendRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(6, 1, imUid, appKey, DelFriend.newBuilder().setTargetUid(this.targetUid).build());
    }

    public long getTargetUid() {
        return this.targetUid;
    }
}
