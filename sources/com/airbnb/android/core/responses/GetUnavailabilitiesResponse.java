package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class GetUnavailabilitiesResponse extends BaseResponse {
    @JsonProperty("calendar_days")
    public List<CalendarDay> days;

    public static class CalendarDay {
        @JsonProperty("available")
        public boolean available;
        @JsonProperty("date")
        public String date;
    }
}
