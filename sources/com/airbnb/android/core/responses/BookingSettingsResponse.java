package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.BookingSettings;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingSettingsResponse extends BaseResponse {
    @JsonProperty("booking_setting")
    public BookingSettings bookingSettings;
}
