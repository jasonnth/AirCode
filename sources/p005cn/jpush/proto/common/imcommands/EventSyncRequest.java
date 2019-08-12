package p005cn.jpush.proto.common.imcommands;

import com.google.gson.jpush.annotations.Expose;
import p005cn.jpush.p034im.proto.C1623Message.EventSync;

/* renamed from: cn.jpush.proto.common.imcommands.EventSyncRequest */
public class EventSyncRequest extends ImBaseRequest {
    @Expose
    long eventId;

    public EventSyncRequest(long eventId2, long rid, long uid) {
        this.rid = rid;
        this.uid = uid;
        this.eventId = eventId2;
    }

    public static EventSyncRequest fromJson(String json) {
        return (EventSyncRequest) _gson.fromJson(json, EventSyncRequest.class);
    }

    /* access modifiers changed from: 0000 */
    public IMProtocol toProtocolBuffer(long imUid, String appKey) {
        return new IMProtocol(4, 1, imUid, appKey, EventSync.newBuilder().setEventId(this.eventId).build());
    }

    public long getEventId() {
        return this.eventId;
    }
}
