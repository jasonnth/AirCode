package com.airbnb.android.core.requests;

import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.FlagContent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateUserFlagRequest extends UserFlagRequest {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("redacted")
        final boolean redacted;

        RequestBody(boolean redacted2) {
            this.redacted = redacted2;
        }
    }

    public UpdateUserFlagRequest(FlagContent content, long flaggableId, long flaggingUserId, boolean redacted) {
        super(content, flaggableId, flaggingUserId);
        this.requestBody = new RequestBody(redacted);
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }
}
