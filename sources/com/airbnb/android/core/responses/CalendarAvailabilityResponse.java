package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CalendarMonth;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CalendarAvailabilityResponse extends BaseResponse {
    @JsonProperty("calendar_months")
    public List<CalendarMonth> months;
}
