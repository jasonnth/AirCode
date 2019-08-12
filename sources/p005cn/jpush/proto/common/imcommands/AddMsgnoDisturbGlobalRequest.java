package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.AddMsgnoDisturbGlobal;

/* renamed from: cn.jpush.proto.common.imcommands.AddMsgnoDisturbGlobalRequest */
public class AddMsgnoDisturbGlobalRequest extends ImBaseRequest {
    @Expose
    int none;
    @Expose
    long version;

    public AddMsgnoDisturbGlobalRequest(long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
    }

    public static AddMsgnoDisturbGlobalRequest fromJson(String json) {
        return (AddMsgnoDisturbGlobalRequest) _gson.fromJson(json, AddMsgnoDisturbGlobalRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(35, 1, imUid, appKey, AddMsgnoDisturbGlobal.newBuilder().build());
    }

    public int getNone() {
        return this.none;
    }

    public long getVersion() {
        return this.version;
    }
}
