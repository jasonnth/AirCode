package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.android.util.ByteBufferUtils;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.MessagePush */
public class MessagePush extends JResponse {
    String msgContent;
    long msgId;
    int msgType;

    public MessagePush(long rid, long juid, int msgType2, long msgId2, String msgContent2) {
        super(1, 3, rid, juid, -1, null);
        this.msgType = msgType2;
        this.msgId = msgId2;
        this.msgContent = msgContent2;
    }

    public void writeBody() {
        super.writeBody();
        writeInt1(this.msgType);
        writeLong8(this.msgId);
        writeTlv2(this.msgContent);
    }

    public MessagePush(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        super.parseBody();
        ByteBuffer buffer = this.body;
        this.msgType = ByteBufferUtils.get(buffer, this).byteValue();
        this.msgId = ByteBufferUtils.getLong(buffer, this);
        this.msgContent = ProtocolUtil.getTlv2(buffer, this);
    }

    public String getName() {
        return "MessagePush";
    }

    public int getMsgType() {
        return this.msgType;
    }

    public long getMsgId() {
        return this.msgId;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public String toString() {
        return "[MessagePush] - msgType:" + this.msgType + ", msgId:" + this.msgId + ", msgContent:" + this.msgContent + " - " + super.toString();
    }
}
