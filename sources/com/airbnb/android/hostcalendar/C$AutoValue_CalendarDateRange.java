package com.airbnb.android.hostcalendar;

import com.airbnb.android.airdate.AirDate;

/* renamed from: com.airbnb.android.hostcalendar.$AutoValue_CalendarDateRange reason: invalid class name */
abstract class C$AutoValue_CalendarDateRange extends CalendarDateRange {
    private final AirDate endDate;
    private final AirDate scrollTargetDate;
    private final AirDate startDate;

    C$AutoValue_CalendarDateRange(AirDate startDate2, AirDate endDate2, AirDate scrollTargetDate2) {
        if (startDate2 == null) {
            throw new NullPointerException("Null startDate");
        }
        this.startDate = startDate2;
        if (endDate2 == null) {
            throw new NullPointerException("Null endDate");
        }
        this.endDate = endDate2;
        if (scrollTargetDate2 == null) {
            throw new NullPointerException("Null scrollTargetDate");
        }
        this.scrollTargetDate = scrollTargetDate2;
    }

    public AirDate getStartDate() {
        return this.startDate;
    }

    public AirDate getEndDate() {
        return this.endDate;
    }

    public AirDate getScrollTargetDate() {
        return this.scrollTargetDate;
    }

    public String toString() {
        return "CalendarDateRange{startDate=" + this.startDate + ", endDate=" + this.endDate + ", scrollTargetDate=" + this.scrollTargetDate + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CalendarDateRange)) {
            return false;
        }
        CalendarDateRange that = (CalendarDateRange) o;
        if (!this.startDate.equals(that.getStartDate()) || !this.endDate.equals(that.getEndDate()) || !this.scrollTargetDate.equals(that.getScrollTargetDate())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.startDate.hashCode()) * 1000003) ^ this.endDate.hashCode()) * 1000003) ^ this.scrollTargetDate.hashCode();
    }
}
