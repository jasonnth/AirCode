package p005cn.jpush.proto.common.commands;

import com.google.protobuf.jpush.InvalidProtocolBufferException;
import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.imcommands.IMProtocol;
import p005cn.jpush.proto.common.utils.ProtocolUtil;
import p005cn.jpush.proto.common.utils.SimpleLog;
import p005cn.jpush.proto.common.utils.StringUtils;

/* renamed from: cn.jpush.proto.common.commands.IMRequest */
public class IMRequest extends JRequest {
    int imBodyLength;
    IMProtocol protocol;

    public String getName() {
        return "IMRequest";
    }

    public IMProtocol getIMProtocol() {
        return this.protocol;
    }

    public void setIMProtocol(IMProtocol protocol2) {
        this.protocol = protocol2;
    }

    public IMRequest(long rid, IMProtocol imProtocol) {
        super(1, 100, rid);
        this.protocol = imProtocol;
    }

    public void writeBody() {
        if (this.protocol != null) {
            byte[] imProtocolByets = this.protocol.toProtocolBuffer().toByteArray();
            this.imBodyLength = imProtocolByets.length;
            writeInt2(this.imBodyLength);
            writeBytes(imProtocolByets);
        }
    }

    public IMRequest(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        SimpleLog.debug("The head - " + StringUtils.toHexLog(this.head.toBytes()));
        this.imBodyLength = this.body.getShort();
        SimpleLog.debug("IM Body Length - " + this.imBodyLength);
        try {
            this.protocol = new IMProtocol(ProtocolUtil.getBytesConsumed(this.body));
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String iMProtocol;
        StringBuilder append = new StringBuilder().append("[IMRequest] - protocol:");
        if (this.protocol == null) {
            iMProtocol = "";
        } else {
            iMProtocol = this.protocol.toString();
        }
        return append.append(iMProtocol).append(" - ").append(super.toString()).toString();
    }
}
