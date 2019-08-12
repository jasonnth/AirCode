package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.Friend.AddFriend;
import p005cn.jpush.p034im.proto.Friend.AddFriend.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.AddFriendRequest */
public class AddFriendRequest extends ImBaseRequest {
    @Expose
    int fromType;
    @Expose
    String memoName;
    @Expose
    String memoOthers;
    @Expose
    long targetUid;
    @Expose
    String why;

    public AddFriendRequest(long targetUid2, String memoName2, String memoOthers2, int fromType2, String why2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
        this.memoName = memoName2;
        this.memoOthers = memoOthers2;
        this.fromType = fromType2;
        this.why = why2;
    }

    public static AddFriendRequest fromJson(String json) {
        return (AddFriendRequest) _gson.fromJson(json, AddFriendRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = AddFriend.newBuilder().setTargetUid(this.targetUid).setFromType(this.fromType);
        if (this.memoName != null) {
            builder.setMemoName(ByteString.copyFromUtf8(this.memoName));
        }
        if (this.memoOthers != null) {
            builder.setMemoOthers(ByteString.copyFromUtf8(this.memoOthers));
        }
        if (this.why != null) {
            builder.setWhy(ByteString.copyFromUtf8(this.why));
        }
        return new IMProtocol(5, 1, imUid, appKey, builder.build());
    }

    public long getTargetUid() {
        return this.targetUid;
    }

    public String getMemoName() {
        return this.memoName;
    }

    public String getMemoOthers() {
        return this.memoOthers;
    }

    public int getFromType() {
        return this.fromType;
    }

    public String getWhy() {
        return this.why;
    }
}
