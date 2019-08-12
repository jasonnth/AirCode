package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.DeleteMsgnoDisturbGlobal;

/* renamed from: cn.jpush.proto.common.imcommands.DeleteMsgnoDisturbGlobalRequest */
public class DeleteMsgnoDisturbGlobalRequest extends ImBaseRequest {
    @Expose
    int none;
    @Expose
    long version;

    public DeleteMsgnoDisturbGlobalRequest(long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
    }

    public static DeleteMsgnoDisturbGlobalRequest fromJson(String json) {
        return (DeleteMsgnoDisturbGlobalRequest) _gson.fromJson(json, DeleteMsgnoDisturbGlobalRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(36, 1, imUid, appKey, DeleteMsgnoDisturbGlobal.newBuilder().build());
    }

    public int getNone() {
        return this.none;
    }

    public long getVersion() {
        return this.version;
    }
}
