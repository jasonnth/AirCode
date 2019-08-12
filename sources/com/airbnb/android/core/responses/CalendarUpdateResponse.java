package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDay;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CalendarUpdateResponse extends BaseResponse {
    public CalendarDaysWrapper wrapper;

    public class CalendarDaysWrapper {
        @JsonProperty("days")
        public List<CalendarDay> days;

        public CalendarDaysWrapper() {
        }
    }

    @JsonProperty("calendar")
    public void setCalendarWrapper(CalendarDaysWrapper wrapper2) {
        this.wrapper = wrapper2;
    }

    @JsonProperty("calendar_operation")
    public void setCalendarOperationWrapper(CalendarDaysWrapper wrapper2) {
        this.wrapper = wrapper2;
    }

    public List<CalendarDay> getUpdatedDays() {
        return this.wrapper.days;
    }

    public void updateServerSyncTime() {
        AirDateTime now = AirDateTime.now();
        for (CalendarDay calendarDay : getUpdatedDays()) {
            calendarDay.setServerSyncedAt(now);
        }
    }
}
