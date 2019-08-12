package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CalendarPricingSettings;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarPricingSettingsResponse extends BaseResponse {
    @JsonProperty("calendar_pricing_setting")
    public CalendarPricingSettings calendarPriceSettings;
}
