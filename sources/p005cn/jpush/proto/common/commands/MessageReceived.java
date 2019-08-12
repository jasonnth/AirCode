package p005cn.jpush.proto.common.commands;

/* renamed from: cn.jpush.proto.common.commands.MessageReceived */
public class MessageReceived extends JRequest {
    int code;
    long msgId;
    int msgType;

    public String getName() {
        return "MessageReceived";
    }

    public MessageReceived(long rid, int code2, int msgType2, long msgId2) {
        super(1, 4, rid);
        this.code = code2;
        this.msgType = msgType2;
        this.msgId = msgId2;
    }

    public void parseBody() {
    }

    public void writeBody() {
    }

    public String toString() {
        return "[MessageReceived] - code:" + this.code + ", msgType:" + this.msgType + ", msgId:" + this.msgId + " - " + super.toString();
    }
}
