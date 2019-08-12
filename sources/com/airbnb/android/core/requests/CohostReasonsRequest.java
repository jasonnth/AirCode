package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.CohostReasonsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CohostReasonsRequest extends BaseRequestV2<CohostReasonsResponse> {
    private final ReasonsBody body;

    private static final class ReasonsBody {
        @JsonProperty("action")
        final String action;
        @JsonProperty("message")
        final String message;
        @JsonProperty("comment")
        final String privateFeedback;
        @JsonProperty("reason")
        final int reason;
        @JsonProperty("removed_cohost_id")
        final long removedCohostId;
        @JsonProperty("source_id")
        final long sourceId;
        @JsonProperty("source_type")
        final String sourceType;

        ReasonsBody(long sourceId2, String sourceType2, String action2, long removedCohostId2, int reason2, String privateFeedback2, String message2) {
            this.sourceId = sourceId2;
            this.sourceType = sourceType2;
            this.action = action2;
            this.removedCohostId = removedCohostId2;
            this.reason = reason2;
            this.privateFeedback = privateFeedback2;
            this.message = message2;
        }
    }

    public CohostReasonsRequest(long sourceId, String sourceType, String action, long removedCohostId, int reason, String privateFeedback, String message) {
        this.body = new ReasonsBody(sourceId, sourceType, action, removedCohostId, reason, privateFeedback, message);
    }

    public ReasonsBody getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return CohostReasonsResponse.class;
    }

    public String getPath() {
        return "cohosting_dropoff_reasons";
    }
}
