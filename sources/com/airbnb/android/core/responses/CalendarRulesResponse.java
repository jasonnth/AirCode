package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.CalendarRule;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarRulesResponse extends BaseResponse {
    @JsonProperty("calendar_rule")
    public CalendarRule calendarRule;
}
