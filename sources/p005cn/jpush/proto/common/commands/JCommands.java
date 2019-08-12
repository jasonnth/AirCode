package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.SimpleLog;
import p005cn.jpush.proto.common.utils.StringUtils;

/* renamed from: cn.jpush.proto.common.commands.JCommands */
public class JCommands {

    /* renamed from: cn.jpush.proto.common.commands.JCommands$Ack */
    public interface Ack {
        public static final int CMD = 19;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$GetMessage */
    public interface GetMessage {
        public static final int CMD = 18;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$Heartbeat */
    public interface Heartbeat {
        public static final int CMD = 2;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$IM */
    public interface C1626IM {
        public static final int CMD = 100;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$Login */
    public interface Login {
        public static final int CMD = 1;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$PushMessage */
    public interface PushMessage {
        public static final int CMD = 3;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$PushReceived */
    public interface PushReceived {
        public static final int CMD = 4;
        public static final int VERSION = 1;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$Register */
    public interface Register {
        public static final int CMD = 0;
        public static final int VERSION = 7;
    }

    /* renamed from: cn.jpush.proto.common.commands.JCommands$TagAlias */
    public interface TagAlias {
        public static final int CMD = 10;
        public static final int VERSION = 2;
    }

    public static JRequest parseRequestInbound(JHead head, ByteBuffer body) {
        switch (head.command) {
            case 0:
                return new RegisterRequest(head, body);
            case 1:
                return new LoginRequest(head, body);
            case 2:
                return new HeartbeatRequest(head, body);
            case 10:
                return new TagaliasRequest(head, body);
            case 100:
                return new IMRequest(head, body);
            default:
                SimpleLog.info("Unknown cmd yet - " + head.toString());
                return null;
        }
    }

    public static JResponse parseResponseInbound(JHead head, ByteBuffer body) {
        switch (head.command) {
            case 0:
                return new RegisterResponse(head, body);
            case 1:
                return new LoginResponse(head, body);
            case 3:
                return new MessagePush(head, body);
            case 10:
                return new TagaliasResponse(head, body);
            case 19:
                return new AckNormal(head, body);
            case 100:
                return new IMResponse(head, body);
            default:
                SimpleLog.warn("Unknown command for parsing inbound.");
                return null;
        }
    }

    public static JResponse parseResponseInbound(byte[] totalBytes) {
        SimpleLog.debug("total bytes - " + StringUtils.toHexLog(totalBytes));
        int bodySize = totalBytes.length - 20;
        byte[] headBytes = new byte[20];
        System.arraycopy(totalBytes, 0, headBytes, 0, 20);
        byte[] bodyByes = new byte[bodySize];
        System.arraycopy(totalBytes, 20, bodyByes, 0, bodySize);
        ByteBuffer body = ByteBuffer.wrap(bodyByes);
        JHead head = new JHead(false, headBytes);
        SimpleLog.debug("parsed head - " + head.toString());
        return parseResponseInbound(head, body);
    }
}
