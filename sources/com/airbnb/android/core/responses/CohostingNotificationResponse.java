package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CohostingNotification;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CohostingNotificationResponse extends BaseResponse {
    @JsonProperty("cohosting_notification")
    public CohostingNotification cohostingNotification;
}
