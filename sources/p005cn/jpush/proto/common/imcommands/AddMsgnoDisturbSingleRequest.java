package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbSingle;

/* renamed from: cn.jpush.proto.common.imcommands.AddMsgnoDisturbSingleRequest */
public class AddMsgnoDisturbSingleRequest extends ImBaseRequest {
    @Expose
    long targetUid;
    @Expose
    long version;

    public AddMsgnoDisturbSingleRequest(long targetUid2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
    }

    public static AddMsgnoDisturbSingleRequest fromJson(String json) {
        return (AddMsgnoDisturbSingleRequest) _gson.fromJson(json, AddMsgnoDisturbSingleRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(31, 1, imUid, appKey, AddMsgnoDisturbSingle.newBuilder().setTargetUid(this.targetUid).build());
    }

    public long getTargetUid() {
        return this.targetUid;
    }

    public long getVersion() {
        return this.version;
    }
}
