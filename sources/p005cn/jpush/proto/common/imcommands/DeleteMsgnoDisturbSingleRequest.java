package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbSingle;

/* renamed from: cn.jpush.proto.common.imcommands.DeleteMsgnoDisturbSingleRequest */
public class DeleteMsgnoDisturbSingleRequest extends ImBaseRequest {
    @Expose
    long targetUid;
    @Expose
    long version;

    public DeleteMsgnoDisturbSingleRequest(long targetUid2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
    }

    public static DeleteMsgnoDisturbSingleRequest fromJson(String json) {
        return (DeleteMsgnoDisturbSingleRequest) _gson.fromJson(json, DeleteMsgnoDisturbSingleRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(32, 1, imUid, appKey, DeleteMsgnoDisturbSingle.newBuilder().setTargetUid(this.targetUid).build());
    }

    public long getTargetUid() {
        return this.targetUid;
    }

    public long getVersion() {
        return this.version;
    }
}
