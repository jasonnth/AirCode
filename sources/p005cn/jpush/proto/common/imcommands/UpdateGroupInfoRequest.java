package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import com.google.protobuf.jpush.ByteString;
import p005cn.jpush.p034im.proto.Group.UpdateGroupInfo;
import p005cn.jpush.p034im.proto.Group.UpdateGroupInfo.Builder;

/* renamed from: cn.jpush.proto.common.imcommands.UpdateGroupInfoRequest */
public class UpdateGroupInfoRequest extends ImBaseRequest {
    @Expose
    String desc;
    @Expose
    long groupId;
    @Expose
    String name;

    public UpdateGroupInfoRequest(long groupId2, String name2, String desc2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.groupId = groupId2;
        this.name = name2;
        this.desc = desc2;
    }

    public static UpdateGroupInfoRequest fromJson(String json) {
        return (UpdateGroupInfoRequest) _gson.fromJson(json, UpdateGroupInfoRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        Builder builder = UpdateGroupInfo.newBuilder().setGid(this.groupId);
        if (this.name != null) {
            builder.setName(ByteString.copyFromUtf8(this.name));
        }
        if (this.desc != null) {
            builder.setInfo(ByteString.copyFromUtf8(this.desc));
        }
        return new IMProtocol(12, 1, imUid, appKey, builder.build());
    }

    public long getGroupId() {
        return this.groupId;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }
}
