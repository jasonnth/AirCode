package com.airbnb.android.sharedcalendar.viewmodels;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirMonth;
import com.airbnb.android.airdate.DayOfWeek;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.sharedcalendar.C1576R;
import com.airbnb.android.sharedcalendar.listeners.CalendarGridTapListener;
import com.airbnb.android.sharedcalendar.listeners.OnboardingOverlayListener;
import com.airbnb.android.sharedcalendar.views.CalendarGridMonth;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;
import java.util.Set;

public class CalendarGridMonthEpoxyModel extends AirEpoxyModel<CalendarGridMonth> {
    private CalendarDays calendarDays;
    private String[] dayOfWeekInitials;
    private DayOfWeek firstDayOfWeek;
    private CalendarGridTapListener listener;
    private AirMonth month;
    private OnboardingOverlayListener onboardingOverlayListener;
    private Set<AirDate> selectedDates;

    public int getDefaultLayout() {
        return C1576R.layout.view_holder_calendar_grid_month;
    }

    public void bind(CalendarGridMonth view) {
        super.bind(view);
        view.bindMonth(this.firstDayOfWeek, this.dayOfWeekInitials, this.calendarDays, this.month, this.selectedDates, this.listener, this.onboardingOverlayListener);
    }

    public CalendarGridMonthEpoxyModel firstDayOfWeek(DayOfWeek firstDayOfWeek2) {
        this.firstDayOfWeek = firstDayOfWeek2;
        return this;
    }

    public CalendarGridMonthEpoxyModel dayOfWeekInitials(String[] dayOfWeekInitials2) {
        this.dayOfWeekInitials = dayOfWeekInitials2;
        return this;
    }

    public CalendarGridMonthEpoxyModel calendarDays(CalendarDays calendarDays2) {
        this.calendarDays = calendarDays2;
        return this;
    }

    public CalendarGridMonthEpoxyModel month(AirMonth month2) {
        this.month = month2;
        return this;
    }

    public CalendarGridMonthEpoxyModel selectedDates(Set<AirDate> selectedDates2) {
        this.selectedDates = selectedDates2;
        return this;
    }

    public CalendarGridMonthEpoxyModel clickListener(CalendarGridTapListener listener2) {
        this.listener = listener2;
        return this;
    }

    public CalendarGridMonthEpoxyModel onboardingOverlayListener(OnboardingOverlayListener onboardingOverlayListener2) {
        this.onboardingOverlayListener = onboardingOverlayListener2;
        return this;
    }

    public boolean canReuseUpdatedView(List<Object> list) {
        return false;
    }
}
