package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;

/* renamed from: cn.jpush.proto.common.commands.HeartbeatRequest */
public class HeartbeatRequest extends JRequest {
    public HeartbeatRequest(long rid) {
        super(1, 2, rid);
    }

    public String getName() {
        return "Heartbeat";
    }

    public HeartbeatRequest(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
    }

    public void writeBody() {
    }

    public String toString() {
        return "[HeartbeatRequest] - " + super.toString();
    }
}
