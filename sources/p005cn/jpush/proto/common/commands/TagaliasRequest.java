package p005cn.jpush.proto.common.commands;

import java.nio.ByteBuffer;
import p005cn.jpush.proto.common.utils.ProtocolUtil;

/* renamed from: cn.jpush.proto.common.commands.TagaliasRequest */
public class TagaliasRequest extends JRequest {
    String action;
    String appKey;

    public TagaliasRequest(JHead head, ByteBuffer bodyBuffer) {
        super(head, bodyBuffer);
    }

    public void parseBody() {
        ByteBuffer buffer = this.body;
        this.appKey = ProtocolUtil.getTlv2(buffer);
        this.action = ProtocolUtil.getTlv2(buffer);
    }

    public TagaliasRequest(long rid, String appKey2, String action2) {
        super(2, 10, rid);
        this.appKey = appKey2;
        this.action = action2;
    }

    public void writeBody() {
        writeTlv2(this.appKey);
        writeTlv2(this.action);
    }

    public String getName() {
        return "TagaliasRequest";
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getAction() {
        return this.action;
    }

    public String toString() {
        return "[TagaliasRequest] - appKey:" + this.appKey + ", action:" + this.action + " - " + super.toString();
    }
}
