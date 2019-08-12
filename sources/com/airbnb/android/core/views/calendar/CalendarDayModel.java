package com.airbnb.android.core.views.calendar;

import com.airbnb.android.core.calendar.AvailabilityController.UnavailabilityType;

public class CalendarDayModel {
    public final int day;
    public boolean isToday;
    public Type type;
    public UnavailabilityType unavailabilityType;

    public enum Type {
        Past,
        CheckIn,
        CheckOut,
        SelectedCheckIn,
        SelectedMiddleDayAvailable,
        SelectedMiddleDayUnavailable,
        SelectedCheckOut,
        Unavailable,
        SelectedUnavailable
    }

    public CalendarDayModel(int day2) {
        this.day = day2;
    }

    public CalendarDayModel(int day2, Type type2) {
        this(day2);
        this.type = type2;
    }

    public void reset() {
        this.type = null;
        this.unavailabilityType = null;
    }

    public boolean isSelectedCheckIn() {
        return this.type == Type.SelectedCheckIn;
    }

    public boolean isSelectedMiddle() {
        return this.type == Type.SelectedMiddleDayAvailable || this.type == Type.SelectedMiddleDayUnavailable;
    }

    public boolean isSelectedCheckOut() {
        return this.type == Type.SelectedCheckOut;
    }

    public boolean isSelected() {
        return isSelectedCheckIn() || isSelectedMiddle() || isSelectedCheckOut();
    }

    public boolean isSelectedCheckInOrMiddle() {
        return isSelectedCheckIn() || isSelectedMiddle();
    }

    public boolean isSelectedMiddleOrCheckOut() {
        return isSelectedMiddle() || isSelectedCheckOut();
    }

    public boolean isInPast() {
        return this.type == Type.Past;
    }

    public boolean isUnavailable() {
        return this.type == Type.Unavailable;
    }
}
