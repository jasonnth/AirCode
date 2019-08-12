package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.JResponse */
public abstract class JResponse extends JPushProtocol {
    public int code;
    public String error;

    public JResponse(int version, int command, long rid, long juid, int code2, String error2) {
        super(false, version, command, rid, -1, juid);
        this.code = code2;
        this.error = error2;
    }

    /* access modifiers changed from: protected */
    public void writeBody() {
        if (this.code >= 0) {
            writeInt2(this.code);
            if (this.code > 0) {
                writeTlv2(this.error);
            }
        }
    }

    public JResponse(JHead head, ByteBuffer bodyBuffer) {
        super(false, head, bodyBuffer);
    }

    /* access modifiers changed from: protected */
    public void parseBody() {
        int command = getCommand();
        if (command != 19 && command != 3 && command != 100) {
            this.code = ByteBufferUtils.getShort(this.body, this);
            if (this.code > 0) {
                this.error = ProtocolUtil.getTlv2(this.body, this);
            }
        }
    }

    public String toString() {
        return "[JResponse] - code:" + this.code + (this.error == null ? "" : ", error:" + this.error) + " - " + super.toString();
    }
}
