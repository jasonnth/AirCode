package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.AirNotificationDevice;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AirNotificationDeviceResponse extends BaseResponse {
    @JsonProperty("air_notification_device")
    public AirNotificationDevice notificationDevice;
}
