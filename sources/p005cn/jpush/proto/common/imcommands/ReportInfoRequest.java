package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.User.ReportInformation;
import p005cn.jpush.p034im.proto.User.ReportInformation.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.ReportInfoRequest */
public class ReportInfoRequest extends ImBaseRequest {
    @Expose
    String sdkVersion;

    public ReportInfoRequest(String sdkVersion2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.sdkVersion = sdkVersion2;
    }

    public static ReportInfoRequest fromJson(String json) {
        return (ReportInfoRequest) _gson.fromJson(json, ReportInfoRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = ReportInformation.newBuilder();
        if (this.sdkVersion != null) {
            builder.setSdkVersion(ByteString.copyFromUtf8(this.sdkVersion));
        }
        return new IMProtocol(23, 1, imUid, appKey, builder.build());
    }

    public String getSdkVerion() {
        return this.sdkVersion;
    }
}
