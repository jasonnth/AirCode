package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.responses.UpdateCohostingNotificationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class UpdateCohostingNotificationRequest extends BaseRequestV2<UpdateCohostingNotificationResponse> {
    private final long listingId;
    private final RequestBody requestBody;

    private static class RequestBody {
        @JsonProperty("mute_type")
        final int muteType;

        RequestBody(MuteType muteType2) {
            this.muteType = muteType2.ordinal();
        }
    }

    public UpdateCohostingNotificationRequest(long listingId2, MuteType muteType) {
        this.listingId = listingId2;
        this.requestBody = new RequestBody(muteType);
    }

    public String getPath() {
        return "cohosting_notifications/" + this.listingId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return UpdateCohostingNotificationResponse.class;
    }
}
