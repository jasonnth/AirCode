package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.LoginRequest */
public class LoginRequest extends JRequest {
    String appKey;
    int clientVersion;
    String fromResource;
    long juid;
    String passwordMd5;
    int platform;
    int sid;

    public String getName() {
        return "LoginRequest";
    }

    public LoginRequest(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        ByteBuffer buffer = this.body;
        this.fromResource = ProtocolUtil.getString(buffer, 4);
        this.passwordMd5 = ProtocolUtil.getTlv2(buffer);
        this.clientVersion = buffer.getInt();
        this.appKey = ProtocolUtil.getTlv2(buffer);
        this.platform = buffer.get();
    }

    public LoginRequest(long rid, String passwordMd52, int clientVersion2, String appKey2, int platform2) {
        super(1, 1, rid);
        this.passwordMd5 = passwordMd52;
        this.clientVersion = clientVersion2;
        this.appKey = appKey2;
        this.platform = platform2;
    }

    public void writeBody() {
        writeBytes(ProtocolUtil.fixedStringToBytes(this.fromResource, 4));
        writeTlv2(this.passwordMd5);
        writeInt4(this.clientVersion);
        writeTlv2(this.appKey);
        writeInt1(this.platform);
    }

    public String toString() {
        return "[LoginRequest] - fromResource:" + this.fromResource + ", passwordMd5:" + this.passwordMd5 + ", clientVersion:" + this.clientVersion + ", appKey:" + this.appKey + ", platform:" + this.platform + " - " + super.toString();
    }
}
