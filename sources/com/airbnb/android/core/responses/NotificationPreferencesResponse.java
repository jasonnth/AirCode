package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.NotificationPreference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class NotificationPreferencesResponse extends BaseResponse {
    @JsonProperty("air_notification_settings")
    public List<NotificationPreference> notificationPreferences;
}
