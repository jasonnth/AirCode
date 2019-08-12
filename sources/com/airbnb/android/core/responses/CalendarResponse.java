package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.ListingCalendar;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CalendarResponse extends BaseResponse {
    @JsonProperty("calendars")
    public List<ListingCalendar> calendars;

    public void updateServerSyncTime() {
        AirDateTime now = AirDateTime.now();
        for (ListingCalendar calendar : this.calendars) {
            for (CalendarDay calendarDay : calendar.getCalendarDays()) {
                calendarDay.setServerSyncedAt(now);
            }
        }
    }
}
