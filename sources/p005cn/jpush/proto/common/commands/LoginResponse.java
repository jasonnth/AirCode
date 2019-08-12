package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.utils.ProtocolUtil;
import p005cn.jpush.proto.common.utils.SimpleLog;

/* renamed from: cn.jpush.proto.common.commands.LoginResponse */
public class LoginResponse extends JResponse {
    int serverTime;
    int serverVersion;
    String sessionKey;
    int sid;

    public LoginResponse(long rid, long juid, int code, String error, int sid2, int serverVersion2, String sessionKey2, int serverTime2) {
        super(1, 1, rid, juid, code, error);
        this.sid = sid2;
        this.serverVersion = serverVersion2;
        this.sessionKey = sessionKey2;
        this.serverTime = serverTime2;
    }

    public void writeBody() {
        super.writeBody();
        writeInt4(this.sid);
        writeInt2(this.serverVersion);
        writeTlv2(this.sessionKey);
        writeInt4(this.serverTime);
    }

    public LoginResponse(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            SimpleLog.error("Response error - code:" + this.code + ", message:" + this.error);
            return;
        }
        ByteBuffer buffer = this.body;
        this.sid = ByteBufferUtils.getInt(buffer, this);
        this.serverVersion = ByteBufferUtils.getShort(buffer, this);
        this.sessionKey = ProtocolUtil.getTlv2(buffer, this);
        this.serverTime = ByteBufferUtils.getInt(buffer, this);
    }

    public String getName() {
        return "LoginResponse";
    }

    public int getSid() {
        return this.sid;
    }

    public int getServerVersion() {
        return this.serverVersion;
    }

    public String getSessionKey() {
        return this.sessionKey;
    }

    public int getServerTime() {
        return this.serverTime;
    }

    public String toString() {
        return "[LoginResponse] - sid:" + this.sid + ", serverVersion:" + this.serverVersion + ", sessionKey:" + this.sessionKey + ", serverTime:" + this.serverTime + " - " + super.toString();
    }
}
