package com.airbnb.android.core.views.calendar;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.utils.Check;
import org.joda.time.DateTime;
import org.joda.time.IllegalInstantException;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.ReadableInstant;

public class DateRangeModel {
    private AirDate checkInDate;
    private DateTime checkInDateTime;
    private AirDate checkOutDate;
    private DateTime checkOutDateTime;
    private Interval daysInBetween;

    public void setCheckInDate(AirDate checkInDate2) {
        if (!(checkInDate2 == null || getCheckOutDate() == null)) {
            Check.argument(checkInDate2.isBefore(getCheckOutDate()), "Start date must be before end date.");
        }
        this.checkInDate = checkInDate2;
        this.checkInDateTime = checkInDate2 != null ? getDateTimeForDay(checkInDate2) : null;
        computeInterval();
    }

    public void setCheckOutDate(AirDate checkOutDate2) {
        if (checkOutDate2 != null) {
            Check.notNull(getCheckInDate(), "You must set a start date before an end date.");
        }
        this.checkOutDate = checkOutDate2;
        this.checkOutDateTime = checkOutDate2 != null ? getDateTimeForDay(checkOutDate2) : null;
        computeInterval();
    }

    public void computeInterval() {
        if (hasSetStartAndEnd()) {
            DateTime offsettedCheckIn = this.checkInDateTime.plusDays(1);
            if (offsettedCheckIn.isBefore((ReadableInstant) this.checkOutDateTime)) {
                this.daysInBetween = new Interval(offsettedCheckIn, this.checkOutDateTime);
            } else {
                this.daysInBetween = null;
            }
        } else {
            this.daysInBetween = null;
        }
    }

    private DateTime getDateTimeForDay(AirDate airDate) {
        try {
            return new DateTime(airDate.getYear(), airDate.getMonthOfYear(), airDate.getDayOfMonth(), 0, 0, 0, 1);
        } catch (IllegalInstantException e) {
            return new LocalDate(airDate.getYear(), airDate.getMonthOfYear(), airDate.getDayOfMonth()).toDateTimeAtStartOfDay().plusMillis(1);
        }
    }

    public boolean hasSetStartAndEnd() {
        return (this.checkInDateTime == null || this.checkOutDateTime == null) ? false : true;
    }

    public boolean hasSetOnlyStart() {
        return this.checkInDateTime != null && this.checkOutDateTime == null;
    }

    public boolean hasntSetStartOrEnd() {
        return this.checkInDateTime == null && this.checkOutDateTime == null;
    }

    public void reset() {
        setCheckInDate(null);
        setCheckOutDate(null);
    }

    public AirDate getCheckInDate() {
        return this.checkInDate;
    }

    public AirDate getCheckOutDate() {
        return this.checkOutDate;
    }

    public DateTime getCheckInDateTime() {
        return this.checkInDateTime;
    }

    public DateTime getCheckOutDateTime() {
        return this.checkOutDateTime;
    }

    public Interval getDaysInBetween() {
        return this.daysInBetween;
    }
}
