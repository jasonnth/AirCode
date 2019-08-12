package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.utils.ProtocolUtil;
import p005cn.jpush.proto.common.utils.SimpleLog;

/* renamed from: cn.jpush.proto.common.commands.RegisterResponse */
public class RegisterResponse extends JResponse {
    String deviceId;
    String invalidImei;
    long juid;
    String password;
    String regId;

    public RegisterResponse(long rid, long juid2, int code, String error, String password2, String regId2, String deviceId2) {
        super(7, 0, rid, -1, code, error);
        this.juid = juid2;
        this.password = password2;
        this.regId = regId2;
        this.deviceId = deviceId2;
    }

    /* access modifiers changed from: protected */
    public void writeBody() {
        super.writeBody();
        writeLong8(this.juid);
        writeTlv2(this.password);
        writeTlv2(this.regId);
        writeTlv2(this.deviceId);
    }

    public void setHeadVersion(int version) {
        this.head.version = version;
    }

    public RegisterResponse(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    /* access modifiers changed from: protected */
    public void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            SimpleLog.error("Response error - code:" + this.code + ", message:" + this.error);
            return;
        }
        ByteBuffer buffer = this.body;
        if (this.code == 0) {
            this.juid = ByteBufferUtils.getLong(buffer, this);
            this.password = ProtocolUtil.getTlv2(buffer, this);
            this.regId = ProtocolUtil.getTlv2(buffer, this);
            this.deviceId = ProtocolUtil.getTlv2(buffer, this);
        } else if (this.code == 1007) {
            this.invalidImei = ProtocolUtil.getTlv2(buffer, this);
        }
    }

    public String getName() {
        return "RegisterResponse";
    }

    public long getJuid() {
        return this.juid;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRegId() {
        return this.regId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getInvalidImei() {
        return this.invalidImei;
    }

    public String toString() {
        return "[RegisterResponse] - juid:" + this.juid + ", password:" + this.password + ", regId:" + this.regId + ", deviceId:" + this.deviceId + " - " + super.toString();
    }
}
