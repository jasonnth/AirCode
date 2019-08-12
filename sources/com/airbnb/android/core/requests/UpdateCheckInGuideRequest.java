package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.CheckInGuideStatus;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.List;

public class UpdateCheckInGuideRequest extends BaseRequestV2<CheckInGuideResponse> {
    private static final String JSON_KEY_PUBLISHED_STATUS = "pub_status";
    private static final String JSON_KEY_STEPS_FOR_REORDER = "step_ids_for_reorder";
    private final Object body;
    private final long listingId;

    private static class ReorderStepsBody {
        @JsonProperty("step_ids_for_reorder")
        final List<Long> orderedStepIds;

        ReorderStepsBody(List<Long> orderedStepIds2) {
            this.orderedStepIds = orderedStepIds2;
        }
    }

    public static UpdateCheckInGuideRequest forPublish(long listingId2) {
        return new UpdateCheckInGuideRequest(listingId2, Strap.make().mo11637kv(JSON_KEY_PUBLISHED_STATUS, CheckInGuideStatus.Published.serverKey.intValue()));
    }

    public static UpdateCheckInGuideRequest forUnpublish(long listingId2) {
        return new UpdateCheckInGuideRequest(listingId2, Strap.make().mo11637kv(JSON_KEY_PUBLISHED_STATUS, CheckInGuideStatus.NotPublished.serverKey.intValue()));
    }

    public static UpdateCheckInGuideRequest forReorder(long listingId2, List<Long> orderedStepIds) {
        return new UpdateCheckInGuideRequest(listingId2, new ReorderStepsBody(orderedStepIds));
    }

    private UpdateCheckInGuideRequest(long listingId2, Object body2) {
        this.listingId = listingId2;
        this.body = body2;
    }

    public String getPath() {
        return "check_in_guides/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.body;
    }

    public Type successResponseType() {
        return CheckInGuideResponse.class;
    }
}
