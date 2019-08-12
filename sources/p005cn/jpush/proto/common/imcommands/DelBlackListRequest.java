package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import p005cn.jpush.p034im.proto.Friend.DelBlackList;

/* renamed from: cn.jpush.proto.common.imcommands.DelBlackListRequest */
public class DelBlackListRequest extends ImBaseRequest {
    @Expose
    List<Long> blackList;

    public DelBlackListRequest(List<Long> blackList2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.blackList = blackList2;
    }

    public DelBlackListRequest(long blackUid, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.blackList = new ArrayList();
        this.blackList.add(Long.valueOf(blackUid));
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(19, 1, imUid, appKey, DelBlackList.newBuilder().addAllTargetList(this.blackList).build());
    }

    public static DelBlackListRequest fromJson(String json) {
        return (DelBlackListRequest) _gson.fromJson(json, DelBlackListRequest.class);
    }

    public List<Long> getBlackList() {
        return this.blackList;
    }
}
