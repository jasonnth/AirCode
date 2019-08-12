package p005cn.jpush.proto.common.commands;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.ProtocolUtil;
import p005cn.jpush.proto.common.utils.SimpleLog;
import p005cn.jpush.proto.common.utils.StringUtils;

/* renamed from: cn.jpush.proto.common.commands.JPushProtocol */
public abstract class JPushProtocol {
    public static final int DEFAULT_RESP_CODE = 0;
    public static final int DEFAULT_RID = 0;
    public static final int DEFAULT_SID = 0;
    public static final int NO_JUID = -1;
    public static final int NO_RESP_CODE = -1;
    public static final int NO_SID = -1;
    private static final int PACKET_SIZE = 7168;
    protected ByteBuffer body;
    protected JHead head;
    private boolean isRequest;

    public abstract String getName();

    /* access modifiers changed from: protected */
    public abstract void parseBody();

    /* access modifiers changed from: protected */
    public abstract void writeBody();

    public JPushProtocol(boolean isRequest2, JHead head2, ByteBuffer bodyBuffer) {
        this.isRequest = isRequest2;
        this.head = head2;
        if (bodyBuffer != null) {
            this.body = bodyBuffer;
            parseBody();
            return;
        }
        SimpleLog.warn("No body to parse.");
    }

    public JPushProtocol(boolean isRequest2, int version, int command, long rid) {
        this.isRequest = isRequest2;
        this.head = new JHead(isRequest2, version, command, rid);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public JPushProtocol(boolean isRequest2, int version, int command, long rid, int sid, long juid) {
        this.isRequest = isRequest2;
        this.head = new JHead(isRequest2, 0, version, command, rid, sid, juid);
        this.body = ByteBuffer.allocate(PACKET_SIZE);
    }

    public int getCommand() {
        return this.head.command;
    }

    public JHead getHead() {
        return this.head;
    }

    /* access modifiers changed from: protected */
    public void writeBytes(byte[] data) {
        this.body.put(data);
    }

    /* access modifiers changed from: protected */
    public void writeInt1(int num) {
        this.body.put((byte) num);
    }

    /* access modifiers changed from: protected */
    public void writeInt2(int num) {
        this.body.putShort((short) num);
    }

    /* access modifiers changed from: protected */
    public void writeInt4(int num) {
        this.body.putInt(num);
    }

    /* access modifiers changed from: protected */
    public void writeLong8(long num) {
        this.body.putLong(num);
    }

    /* access modifiers changed from: protected */
    public void writeTlv2(String str) {
        this.body.put(ProtocolUtil.tlv2ToByteArray(str));
    }

    public final byte[] writeBodyAndToBytes() {
        this.body.clear();
        writeBody();
        this.body.flip();
        return toBytes();
    }

    private final byte[] toBytes() {
        ByteArrayOutputStream all = new ByteArrayOutputStream();
        byte[] bodyBytes = ProtocolUtil.getBytes(this.body);
        this.head.len = (this.isRequest ? 24 : 20) + bodyBytes.length;
        try {
            all.write(this.head.toBytes());
            all.write(bodyBytes);
        } catch (Exception e) {
        }
        byte[] allBytes = all.toByteArray();
        SimpleLog.debug("Final - len:" + allBytes.length + ", bytes: " + StringUtils.toHexLog(allBytes));
        return allBytes;
    }

    public String toString() {
        return (this.isRequest ? "[Request]" : "[Response]") + " - " + this.head.toString();
    }
}
