package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.convert.ToString;
import org.joda.time.base.BasePartial;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

public final class YearMonth extends BasePartial implements Serializable, ReadablePartial {
    private static final DateTimeFieldType[] FIELD_TYPES = {DateTimeFieldType.year(), DateTimeFieldType.monthOfYear()};

    public static YearMonth now() {
        return new YearMonth();
    }

    public YearMonth() {
    }

    public YearMonth(int i, int i2) {
        this(i, i2, null);
    }

    public YearMonth(int i, int i2, Chronology chronology) {
        super(new int[]{i, i2}, chronology);
    }

    YearMonth(YearMonth yearMonth, int[] iArr) {
        super((BasePartial) yearMonth, iArr);
    }

    YearMonth(YearMonth yearMonth, Chronology chronology) {
        super((BasePartial) yearMonth, chronology);
    }

    private Object readResolve() {
        if (!DateTimeZone.UTC.equals(getChronology().getZone())) {
            return new YearMonth(this, getChronology().withUTC());
        }
        return this;
    }

    public int size() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public DateTimeField getField(int i, Chronology chronology) {
        switch (i) {
            case 0:
                return chronology.year();
            case 1:
                return chronology.monthOfYear();
            default:
                throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
    }

    public DateTimeFieldType getFieldType(int i) {
        return FIELD_TYPES[i];
    }

    public YearMonth withFieldAdded(DurationFieldType durationFieldType, int i) {
        int indexOfSupported = indexOfSupported(durationFieldType);
        if (i == 0) {
            return this;
        }
        return new YearMonth(this, getField(indexOfSupported).add(this, indexOfSupported, getValues(), i));
    }

    public YearMonth plusMonths(int i) {
        return withFieldAdded(DurationFieldType.months(), i);
    }

    public LocalDate toLocalDate(int i) {
        return new LocalDate(getYear(), getMonthOfYear(), i, getChronology());
    }

    public int getYear() {
        return getValue(0);
    }

    public int getMonthOfYear() {
        return getValue(1);
    }

    @ToString
    public String toString() {
        return ISODateTimeFormat.yearMonth().print((ReadablePartial) this);
    }

    public String toString(String str) {
        if (str == null) {
            return toString();
        }
        return DateTimeFormat.forPattern(str).print((ReadablePartial) this);
    }

    public String toString(String str, Locale locale) throws IllegalArgumentException {
        if (str == null) {
            return toString();
        }
        return DateTimeFormat.forPattern(str).withLocale(locale).print((ReadablePartial) this);
    }
}
