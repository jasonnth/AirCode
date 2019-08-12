package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.Friend.UpdateMemo;
import p005cn.jpush.p034im.proto.Friend.UpdateMemo.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.UpdateMemoRequest */
public class UpdateMemoRequest extends ImBaseRequest {
    @Expose
    String newMemoName;
    @Expose
    String newMemoOthers;
    @Expose
    long targetUid;

    public UpdateMemoRequest(long targetUid2, String newMemoName2, String newMemoOthers2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
        this.newMemoName = newMemoName2;
        this.newMemoOthers = newMemoOthers2;
    }

    public static UpdateMemoRequest fromJson(String json) {
        return (UpdateMemoRequest) _gson.fromJson(json, UpdateMemoRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = UpdateMemo.newBuilder().setTargetUid(this.targetUid);
        if (this.newMemoName != null) {
            builder.setNewMemoName(ByteString.copyFromUtf8(this.newMemoName));
        }
        if (this.newMemoOthers != null) {
            builder.setNewMemoOthers(ByteString.copyFromUtf8(this.newMemoOthers));
        }
        return new IMProtocol(7, 1, imUid, appKey, builder.build());
    }

    public long getTargetUid() {
        return this.targetUid;
    }

    public String getNewMemoName() {
        return this.newMemoName;
    }

    public String getNewMemoOthers() {
        return this.newMemoOthers;
    }
}
