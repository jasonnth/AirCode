package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.p034im.proto.Friend.AddBlackList;

/* renamed from: cn.jpush.proto.common.imcommands.AddBlackListRequest */
public class AddBlackListRequest extends ImBaseRequest {
    @Expose
    List<Long> blackList;

    public AddBlackListRequest(List<Long> blackList2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.blackList = blackList2;
    }

    public AddBlackListRequest(long balckUid, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.blackList = new ArrayList();
        this.blackList.add(Long.valueOf(balckUid));
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(18, 1, imUid, appKey, AddBlackList.newBuilder().addAllTargetList(this.blackList).build());
    }

    public static AddBlackListRequest fromJson(String json) {
        return (AddBlackListRequest) _gson.fromJson(json, AddBlackListRequest.class);
    }

    public List<Long> getBlackList() {
        return this.blackList;
    }
}
