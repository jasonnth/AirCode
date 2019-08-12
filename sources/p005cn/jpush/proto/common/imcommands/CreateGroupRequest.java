package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.Group.CreateGroup;
import p005cn.jpush.p034im.proto.Group.CreateGroup.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.CreateGroupRequest */
public class CreateGroupRequest extends ImBaseRequest {
    @Expose
    String desc;
    @Expose
    int flag;
    @Expose
    int level;
    @Expose
    String name;

    public CreateGroupRequest(String name2, String desc2, int flag2, int level2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.name = name2;
        this.desc = desc2;
        this.flag = flag2;
        this.level = level2;
    }

    public static CreateGroupRequest fromJson(String json) {
        return (CreateGroupRequest) _gson.fromJson(json, CreateGroupRequest.class);
    }

    /* access modifiers changed from: protected */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = CreateGroup.newBuilder().setFlag(this.flag).setGroupLevel(this.level);
        if (this.name != null) {
            builder.setGroupName(ByteString.copyFromUtf8(this.name));
        }
        if (this.desc != null) {
            builder.setGroupDesc(ByteString.copyFromUtf8(this.desc));
        }
        return new IMProtocol(8, 1, imUid, appKey, builder.build());
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public int getFlag() {
        return this.flag;
    }

    public int getLevel() {
        return this.level;
    }
}
