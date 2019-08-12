package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.User.Login;
import p005cn.jpush.p034im.proto.User.Login.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.ImLoginRequest */
public class ImLoginRequest extends ImBaseRequest {
    @Expose
    String password;
    @Expose
    int platform;
    @Expose
    String sdkVersion;
    @Expose
    String username;

    public ImLoginRequest(String username2, String password2, int platform2, String sdkVersion2, long rid) {
        this.rid = rid;
        this.username = username2;
        this.password = password2;
        this.platform = platform2;
        this.sdkVersion = sdkVersion2;
    }

    public static ImLoginRequest fromJson(String json) {
        return (ImLoginRequest) _gson.fromJson(json, ImLoginRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = Login.newBuilder().setPlatform(this.platform);
        if (this.username != null) {
            builder.setUsername(ByteString.copyFromUtf8(this.username));
        }
        if (this.password != null) {
            builder.setPassword(ByteString.copyFromUtf8(this.password));
        }
        if (this.sdkVersion != null) {
            builder.setSdkVersion(ByteString.copyFromUtf8(this.sdkVersion));
        }
        return new IMProtocol(1, 1, imUid, appKey, builder.build());
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }
}
