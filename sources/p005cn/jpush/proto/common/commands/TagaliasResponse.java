package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.utils.SimpleLog;

/* renamed from: cn.jpush.proto.common.commands.TagaliasResponse */
public class TagaliasResponse extends JResponse {
    int sequence;

    public TagaliasResponse(long rid, long juid, int code, String error, int sequence2) {
        super(2, 10, rid, juid, code, error);
        this.sequence = sequence2;
    }

    public void writeBody() {
        super.writeBody();
        writeInt4(this.sequence);
    }

    public TagaliasResponse(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        super.parseBody();
        if (this.code > 0) {
            SimpleLog.error("Response error - code:" + this.code + ", message:" + this.error);
        } else {
            this.sequence = ByteBufferUtils.getShort(this.body, this);
        }
    }

    public String getName() {
        return "TagaliasResponse";
    }

    public int getSequence() {
        return this.sequence;
    }

    public String toString() {
        return "[TagaliasResponse] - sequence:" + this.sequence + " - " + super.toString();
    }
}
