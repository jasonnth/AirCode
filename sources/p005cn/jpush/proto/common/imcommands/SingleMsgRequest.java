package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.C1623Message.MessageContent;
import p005cn.jpush.p034im.proto.C1623Message.SingleMsg;
import p005cn.jpush.p034im.proto.C1623Message.SingleMsg.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.SingleMsgRequest */
public class SingleMsgRequest extends ImBaseRequest {
    public static final String SERVICE_KEY = SingleMsgRequest.class.getName();
    @Expose
    String msgContent;
    @Expose
    long targetUid;

    public SingleMsgRequest(long targetUid2, String msgContent2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.targetUid = targetUid2;
        this.msgContent = msgContent2;
    }

    public static SingleMsgRequest fromJson(String json) {
        return (SingleMsgRequest) _gson.fromJson(json, SingleMsgRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = SingleMsg.newBuilder().setTargetUid(this.targetUid);
        if (this.msgContent != null) {
            builder.setContent(MessageContent.newBuilder().setContent(ByteString.copyFromUtf8(this.msgContent)).build());
        }
        return new IMProtocol(3, 1, imUid, appKey, builder.build());
    }

    public static String getServiceKey() {
        return SERVICE_KEY;
    }

    public long getTargetUid() {
        return this.targetUid;
    }

    public String getMsgContent() {
        return this.msgContent;
    }
}
