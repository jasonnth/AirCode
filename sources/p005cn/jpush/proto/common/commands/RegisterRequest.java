package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.RegisterRequest */
public class RegisterRequest extends JRequest {
    String apkVersion;
    int apsType;
    int buildType;
    String clientInfo;
    String deviceToken;
    String key;
    String keyExt;
    int platform;

    public RegisterRequest(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public RegisterRequest(long rid, String key2, String apkVersion2, String clientInfo2, int platform2, String keyExt2) {
        super(7, 0, rid);
        this.key = key2;
        this.apkVersion = apkVersion2;
        this.clientInfo = clientInfo2;
        this.platform = platform2;
        this.keyExt = keyExt2;
    }

    public void writeBody() {
        writeTlv2(this.key);
        writeTlv2(this.apkVersion);
        writeTlv2(this.clientInfo);
        writeInt1(this.platform);
        writeTlv2(this.keyExt);
    }

    public void parseBody() {
        ByteBuffer buffer = this.body;
        boolean isIOS = false;
        if (this.head.version == 8 || this.head.version == 4) {
            isIOS = true;
        }
        this.key = ProtocolUtil.getTlv2(buffer);
        this.apkVersion = ProtocolUtil.getTlv2(buffer);
        this.clientInfo = ProtocolUtil.getTlv2(buffer);
        if (isIOS) {
            this.deviceToken = ProtocolUtil.getTlv2(buffer);
            this.buildType = buffer.get();
            this.apsType = buffer.get();
        }
        this.platform = buffer.get();
        if (isIOS && this.platform == 1) {
            System.out.println("WARN: iOS register platform exception - platform:" + this.platform);
        }
        if (!isIOS) {
            this.keyExt = ProtocolUtil.getTlv2(buffer);
        }
    }

    public String getName() {
        return "RegisterRequest";
    }

    public String getKey() {
        return this.key;
    }

    public String getKeyExt() {
        return this.keyExt;
    }

    public String toString() {
        return " - [RegisterRequest] - key:" + this.key + ", apkVersion:" + this.apkVersion + ", clientInfo:" + this.clientInfo + ", platform:" + this.platform + ", keyExt:" + this.keyExt + " - " + super.toString();
    }
}
