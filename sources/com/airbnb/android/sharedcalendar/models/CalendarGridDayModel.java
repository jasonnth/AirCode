package com.airbnb.android.sharedcalendar.models;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDay.Type;
import com.airbnb.android.utils.ListUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CalendarGridDayModel {
    private static final AirDate today = AirDate.today();
    private final CalendarDay calendarDay;
    private final int drawnPosition;
    private boolean hasPendingRequest = false;
    private boolean selected = false;

    public static List<CalendarGridDayModel> getDayModels(int positionStart, int positionEnd, CalendarDays calendarDays, AirDate positionStartDate, Set<AirDate> daysSelected) {
        List<CalendarGridDayModel> dayModels = new ArrayList<>();
        for (int i = 0; i < positionEnd - positionStart; i++) {
            CalendarDay calendarDay2 = calendarDays.get(positionStartDate.plusDays(i));
            if (calendarDay2 != null) {
                CalendarGridDayModel model = new CalendarGridDayModel(calendarDay2, i + positionStart);
                model.setSelected(daysSelected.contains(calendarDay2.getDate()));
                dayModels.add(model);
            }
        }
        return dayModels;
    }

    public CalendarGridDayModel(CalendarDay calendarDay2, int drawnPosition2) {
        this.calendarDay = calendarDay2;
        this.drawnPosition = drawnPosition2;
    }

    public CalendarDay getCalendarDay() {
        return this.calendarDay;
    }

    public int getDayOfMonth() {
        return this.calendarDay.getDate().getDayOfMonth();
    }

    public boolean isBlocked() {
        return !this.calendarDay.isAvailable();
    }

    private boolean isBlockedByBookingWindow() {
        return this.calendarDay.getCalendarDayType() == Type.MaxDaysNoticeBusy;
    }

    public boolean showWithUnavailableSlash() {
        return ((isBlocked() && !isBlockedByBookingWindow()) || isNestedBusy()) && !hasReservation() && !isUnEditable();
    }

    public boolean showWithLightSlash() {
        if (!FeatureToggles.showNestedListings() || !isInPast() || !isNestedBusy()) {
            return false;
        }
        return true;
    }

    public boolean showWithGrayBackground() {
        return isBlocked() && !hasReservation() && !isUnEditable() && !isNestedBusy();
    }

    public boolean drawWithLightTextColor() {
        return isUnEditable() || isBlockedByBookingWindow();
    }

    public int getDrawnPosition() {
        return this.drawnPosition;
    }

    public boolean hasReservation() {
        return this.calendarDay.getReservation() != null && !this.calendarDay.getReservation().isExpired();
    }

    public boolean isNestedBusy() {
        return FeatureToggles.showNestedListings() && !ListUtils.isEmpty((Collection<?>) this.calendarDay.getNestedBusyDetails());
    }

    public boolean isFirstDayOfReservation() {
        return this.calendarDay.getReservation().getStartDate().isSameDay(this.calendarDay.getDate());
    }

    public boolean hasReservationInProgress() {
        return this.calendarDay.getReservation().isCurrent() && !this.calendarDay.getReservation().isExpired();
    }

    public String getReservationCode() {
        return this.calendarDay.getReservation().getConfirmationCode();
    }

    public boolean isToday() {
        return today.equals(this.calendarDay.getDate());
    }

    public boolean isInPast() {
        return today.isAfter(this.calendarDay.getDate());
    }

    public boolean isUnEditable() {
        return isInPast() || this.calendarDay.isBlockedForExceedingMaxDayCap();
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean selected2) {
        this.selected = selected2;
    }

    public boolean hasPendingRequest() {
        return this.hasPendingRequest;
    }

    public void setHasPendingRequest(boolean hasPendingRequest2) {
        this.hasPendingRequest = hasPendingRequest2;
    }
}
