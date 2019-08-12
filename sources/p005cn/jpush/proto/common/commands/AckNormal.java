package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;

/* renamed from: cn.jpush.proto.common.commands.AckNormal */
public class AckNormal extends JResponse {
    int requestCommand;
    int status;
    int step;
    long stime;

    public String getName() {
        return "ACK Response";
    }

    public int getRequestCommand() {
        return this.requestCommand;
    }

    public AckNormal(long rid, long juid, int requestCommand2, int step2, int status2, long stime2) {
        super(1, 19, rid, juid, -1, null);
        this.requestCommand = requestCommand2;
        this.step = step2;
        this.status = status2;
        this.stime = stime2;
    }

    public void writeBody() {
        super.writeBody();
        writeInt1(this.requestCommand);
        writeInt1(this.step);
        writeInt1(this.status);
        writeLong8(this.stime);
    }

    public AckNormal(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        super.parseBody();
        ByteBuffer buffer = this.body;
        this.requestCommand = ByteBufferUtils.get(buffer, this).byteValue();
        this.step = ByteBufferUtils.get(buffer, this).byteValue();
        this.status = ByteBufferUtils.get(buffer, this).byteValue();
        this.stime = ByteBufferUtils.getLong(buffer, this);
    }

    public String toString() {
        return "[AckNormal] - requestCommand:" + this.requestCommand + ", step:" + this.step + ", status:" + this.status + ", stime:" + this.stime + " - " + super.toString();
    }
}
