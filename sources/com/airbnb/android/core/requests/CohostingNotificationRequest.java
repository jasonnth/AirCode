package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.CohostingNotificationResponse;
import java.lang.reflect.Type;

public class CohostingNotificationRequest extends BaseRequestV2<CohostingNotificationResponse> {
    private final long listingId;

    public CohostingNotificationRequest(long listingId2) {
        this.listingId = listingId2;
    }

    public String getPath() {
        return "cohosting_notifications/" + this.listingId;
    }

    public Type successResponseType() {
        return CohostingNotificationResponse.class;
    }
}
