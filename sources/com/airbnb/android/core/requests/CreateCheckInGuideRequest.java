package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.checkin.CheckInGuideDataModel;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class CreateCheckInGuideRequest extends BaseRequestV2<CheckInGuideResponse> {
    private final CreateCheckInGuideRequestBody body;

    private static final class CreateCheckInGuideRequestBody {
        @JsonProperty("hosting_id")
        final long hostingId;
        @JsonProperty("language")
        final String language;

        CreateCheckInGuideRequestBody(long hostingId2, String language2) {
            this.hostingId = hostingId2;
            this.language = language2;
        }
    }

    public CreateCheckInGuideRequest(long listingId, String language) {
        this.body = new CreateCheckInGuideRequestBody(listingId, language);
    }

    public String getPath() {
        return CheckInGuideDataModel.TABLE_NAME;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return this.body;
    }

    public Type successResponseType() {
        return CheckInGuideResponse.class;
    }
}
