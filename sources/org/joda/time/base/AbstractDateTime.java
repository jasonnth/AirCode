package org.joda.time.base;

import java.util.GregorianCalendar;
import org.joda.convert.ToString;
import org.joda.time.ReadableDateTime;

public abstract class AbstractDateTime extends AbstractInstant implements ReadableDateTime {
    protected AbstractDateTime() {
    }

    public int getYear() {
        return getChronology().year().get(getMillis());
    }

    public int getWeekyear() {
        return getChronology().weekyear().get(getMillis());
    }

    public int getMonthOfYear() {
        return getChronology().monthOfYear().get(getMillis());
    }

    public int getDayOfMonth() {
        return getChronology().dayOfMonth().get(getMillis());
    }

    public GregorianCalendar toGregorianCalendar() {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(getZone().toTimeZone());
        gregorianCalendar.setTime(toDate());
        return gregorianCalendar;
    }

    @ToString
    public String toString() {
        return super.toString();
    }
}
