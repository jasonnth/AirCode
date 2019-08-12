package p005cn.jpush.proto.common.commands;

import com.google.protobuf.jpush.InvalidProtocolBufferException;
import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.imcommands.IMProtocol;
import p005cn.jpush.proto.common.utils.ProtocolUtil;
import p005cn.jpush.proto.common.utils.SimpleLog;

/* renamed from: cn.jpush.proto.common.commands.IMResponse */
public class IMResponse extends JResponse {
    int imBodyLength;
    IMProtocol protocol;

    public String getName() {
        return "IMResponse";
    }

    public IMProtocol getIMProtocol() {
        return this.protocol;
    }

    public IMResponse(long rid, long juid, IMProtocol protocol2) {
        super(1, 100, rid, juid, -1, null);
        this.protocol = protocol2;
    }

    public void writeBody() {
        super.writeBody();
        if (this.protocol != null) {
            byte[] protocolBytes = this.protocol.toProtocolBuffer().toByteArray();
            this.imBodyLength = protocolBytes.length;
            SimpleLog.debug("IM Body Length - " + this.imBodyLength);
            writeInt2(this.imBodyLength);
            writeBytes(protocolBytes);
        }
    }

    public IMResponse(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        super.parseBody();
        this.imBodyLength = this.body.getShort();
        SimpleLog.debug("IM Body Length - " + this.imBodyLength);
        try {
            this.protocol = new IMProtocol(ProtocolUtil.getBytesConsumed(this.body));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "[IMResponse] - protocol:" + (this.protocol == null ? "NULL Object" : this.protocol.toString()) + " - " + super.toString();
    }
}
