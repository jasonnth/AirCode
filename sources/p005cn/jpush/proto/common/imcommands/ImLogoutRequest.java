package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.User.Logout;
import p005cn.jpush.p034im.proto.User.Logout.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.ImLogoutRequest */
public class ImLogoutRequest extends ImBaseRequest {
    @Expose
    String username;

    public ImLogoutRequest(String username2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.username = username2;
    }

    public static ImLogoutRequest fromJson(String json) {
        return (ImLogoutRequest) _gson.fromJson(json, ImLogoutRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = Logout.newBuilder();
        if (this.username != null) {
            builder.setUsername(ByteString.copyFromUtf8(this.username));
        }
        return new IMProtocol(2, 1, imUid, appKey, builder.build());
    }

    public String getUsername() {
        return this.username;
    }
}
