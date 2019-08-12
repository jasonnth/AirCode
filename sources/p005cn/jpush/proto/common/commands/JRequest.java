package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;

/* renamed from: cn.jpush.proto.common.commands.JRequest */
public abstract class JRequest extends JPushProtocol {
    public JRequest(int version, int command, long rid) {
        super(true, version, command, rid);
    }

    public JRequest(JHead head, ByteBuffer bodyBuffer) {
        super(true, head, bodyBuffer);
    }

    public void setSid(int sid) {
        this.head.sid = sid;
    }

    public void setJuid(long juid) {
        this.head.juid = juid;
    }
}
