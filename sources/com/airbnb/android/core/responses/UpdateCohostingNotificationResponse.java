package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.CohostingNotification;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateCohostingNotificationResponse {
    @JsonProperty("cohosting_notification")
    public CohostingNotification cohostingNotification;
}
