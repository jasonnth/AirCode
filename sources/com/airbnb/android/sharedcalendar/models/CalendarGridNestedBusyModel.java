package com.airbnb.android.sharedcalendar.models;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.NestedBusyDetail;
import com.airbnb.android.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalendarGridNestedBusyModel {
    private final int daysUntilNestedBusyEnds;
    private final int daysUntilNestedBusyStarts;

    public static List<CalendarGridNestedBusyModel> getNestedBusyModels(int positionStart, int positionEnd, CalendarDays calendarDays, AirDate positionStartDate) {
        List<CalendarGridNestedBusyModel> nestedBusyModels = new ArrayList<>();
        int i = -1;
        while (i < positionEnd - positionStart) {
            CalendarDay calendarDayStart = calendarDays.get(positionStartDate.plusDays(i));
            List<NestedBusyDetail> nestedBusyDetailsStart = calendarDayStart != null ? calendarDayStart.getNestedBusyDetails() : null;
            if (!ListUtils.isEmpty((Collection<?>) nestedBusyDetailsStart)) {
                int daysUntilStart = i;
                int daysUntilEnd = i;
                while (true) {
                    if (i > positionEnd - positionStart) {
                        break;
                    }
                    CalendarDay calendarDayEnd = calendarDays.get(positionStartDate.plusDays(i));
                    List<NestedBusyDetail> nestedBusyDetailsEnd = calendarDayEnd != null ? calendarDayEnd.getNestedBusyDetails() : null;
                    if (ListUtils.isEmpty((Collection<?>) nestedBusyDetailsEnd) || !NestedBusyDetail.isSameNestedBusyEvent((NestedBusyDetail) nestedBusyDetailsStart.get(0), (NestedBusyDetail) nestedBusyDetailsEnd.get(0))) {
                        daysUntilEnd = i;
                    } else {
                        daysUntilEnd = i;
                        i++;
                    }
                }
                daysUntilEnd = i;
                nestedBusyModels.add(new CalendarGridNestedBusyModel(daysUntilStart, daysUntilEnd));
            } else {
                i++;
            }
        }
        return nestedBusyModels;
    }

    public CalendarGridNestedBusyModel(int daysUntilStart, int daysUntilEnd) {
        this.daysUntilNestedBusyStarts = daysUntilStart;
        this.daysUntilNestedBusyEnds = daysUntilEnd;
    }

    public int getDaysUntilNestedBusyStarts() {
        return this.daysUntilNestedBusyStarts;
    }

    public int getDaysUntilNestedBusyEnds() {
        return this.daysUntilNestedBusyEnds;
    }
}
