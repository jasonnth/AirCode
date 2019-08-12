package com.airbnb.android.core.requests;

import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.enums.FlagContent;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserFlagRequest extends UserFlagRequest {
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("flaggable_id")
        final long flaggableId;
        @JsonProperty("flaggable_type")
        final String flaggableType;
        @JsonProperty("flagging_user_id")
        final long flaggingUserId;
        @JsonProperty("name")
        final String name;

        RequestBody(FlagContent content, long flaggableId2, long flaggingUserId2, String name2) {
            this.flaggableType = content.getServerKey();
            this.flaggableId = flaggableId2;
            this.flaggingUserId = flaggingUserId2;
            this.name = name2;
        }
    }

    public CreateUserFlagRequest(FlagContent content, long flaggableId, long flaggingUserId, String name) {
        super(content, flaggableId, flaggingUserId);
        this.requestBody = new RequestBody(content, flaggableId, flaggingUserId, name);
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getPath() {
        return "user_flags";
    }

    public Object getBody() {
        return this.requestBody;
    }
}
