package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.JHead */
public class JHead {
    public static final int REQUEST_HEAD_LEN = 24;
    public static final int RESPONSE_HEAD_LEN = 20;
    int command;
    private boolean isRequest;
    long juid;
    int len;
    Long rid;
    int sid;
    int version;

    public JHead(boolean isRequest2) {
        this.isRequest = false;
        this.isRequest = isRequest2;
    }

    public JHead(boolean isRequest2, int len2, int version2, int command2, long rid2, int sid2, long juid2) {
        this.isRequest = false;
        this.isRequest = isRequest2;
        this.len = len2;
        this.version = version2;
        this.command = command2;
        this.rid = Long.valueOf(rid2);
        this.sid = sid2;
        this.juid = juid2;
    }

    public JHead(boolean isRequest2, int version2, int command2, long rid2) {
        this(isRequest2, 0, version2, command2, rid2, 0, 0);
    }

    public JHead(boolean isRequest2, byte[] headBytes) {
        this.isRequest = false;
        this.isRequest = isRequest2;
        ByteBuffer buffer = ByteBuffer.wrap(headBytes);
        this.len = buffer.getShort();
        this.version = buffer.get();
        this.command = buffer.get();
        this.rid = Long.valueOf(buffer.getLong());
        if (isRequest2) {
            this.sid = buffer.getInt();
        }
        this.juid = buffer.getLong();
    }

    public int getCommand() {
        return this.command;
    }

    public Long getRid() {
        return this.rid;
    }

    public long getJuid() {
        return this.juid;
    }

    public int getSid() {
        return this.sid;
    }

    public byte[] toBytes() {
        if (this.len == 0) {
            throw new IllegalStateException("The head is not initialized yet.");
        }
        ByteBuffer buffer = ByteBuffer.allocate(24);
        buffer.putShort((short) this.len);
        buffer.put((byte) this.version);
        buffer.put((byte) this.command);
        buffer.putLong(this.rid.longValue());
        if (this.isRequest) {
            buffer.putInt(this.sid);
        }
        buffer.putLong(this.juid);
        buffer.flip();
        return ProtocolUtil.getBytesConsumed(buffer);
    }

    public String toString() {
        return "[JHead] - len:" + this.len + ", version:" + this.version + ", command:" + this.command + ", rid:" + this.rid + (this.isRequest ? ", sid:" + this.sid : "") + ", juid:" + this.juid;
    }
}
