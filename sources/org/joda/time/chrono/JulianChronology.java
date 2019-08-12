package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.chrono.AssembledChronology.Fields;
import org.joda.time.field.SkipDateTimeField;

public final class JulianChronology extends BasicGJChronology {
    private static final JulianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final ConcurrentHashMap<DateTimeZone, JulianChronology[]> cCache = new ConcurrentHashMap<>();

    static int adjustYearForSet(int i) {
        if (i > 0) {
            return i;
        }
        if (i != 0) {
            return i + 1;
        }
        throw new IllegalFieldValueException(DateTimeFieldType.year(), Integer.valueOf(i), null, null);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static JulianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        JulianChronology[] julianChronologyArr;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        JulianChronology[] julianChronologyArr2 = (JulianChronology[]) cCache.get(dateTimeZone);
        if (julianChronologyArr2 == null) {
            julianChronologyArr = new JulianChronology[7];
            JulianChronology[] julianChronologyArr3 = (JulianChronology[]) cCache.putIfAbsent(dateTimeZone, julianChronologyArr);
            if (julianChronologyArr3 != null) {
                julianChronologyArr = julianChronologyArr3;
            }
        } else {
            julianChronologyArr = julianChronologyArr2;
        }
        try {
            JulianChronology julianChronology = julianChronologyArr[i - 1];
            if (julianChronology == null) {
                synchronized (julianChronologyArr) {
                    julianChronology = julianChronologyArr[i - 1];
                    if (julianChronology == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            julianChronology = new JulianChronology(null, null, i);
                        } else {
                            julianChronology = new JulianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        julianChronologyArr[i - 1] = julianChronology;
                    }
                }
            }
            return julianChronology;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    JulianChronology(Chronology chronology, Object obj, int i) {
        super(chronology, obj, i);
    }

    private Object readResolve() {
        Chronology base = getBase();
        int minimumDaysInFirstWeek = getMinimumDaysInFirstWeek();
        if (minimumDaysInFirstWeek == 0) {
            minimumDaysInFirstWeek = 4;
        }
        return base == null ? getInstance(DateTimeZone.UTC, minimumDaysInFirstWeek) : getInstance(base.getZone(), minimumDaysInFirstWeek);
    }

    public Chronology withUTC() {
        return INSTANCE_UTC;
    }

    public Chronology withZone(DateTimeZone dateTimeZone) {
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        return dateTimeZone == getZone() ? this : getInstance(dateTimeZone);
    }

    /* access modifiers changed from: 0000 */
    public long getDateMidnightMillis(int i, int i2, int i3) throws IllegalArgumentException {
        return super.getDateMidnightMillis(adjustYearForSet(i), i2, i3);
    }

    /* access modifiers changed from: 0000 */
    public boolean isLeapYear(int i) {
        return (i & 3) == 0;
    }

    /* access modifiers changed from: 0000 */
    public long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i - 1968;
        if (i3 <= 0) {
            i2 = (i3 + 3) >> 2;
        } else {
            i2 = i3 >> 2;
            if (!isLeapYear(i)) {
                i2++;
            }
        }
        return ((((long) i2) + (((long) i3) * 365)) * 86400000) - 62035200000L;
    }

    /* access modifiers changed from: 0000 */
    public int getMinYear() {
        return -292269054;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxYear() {
        return 292272992;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerYear() {
        return 31557600000L;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerYearDividedByTwo() {
        return 15778800000L;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerMonth() {
        return 2629800000L;
    }

    /* access modifiers changed from: 0000 */
    public long getApproxMillisAtEpochDividedByTwo() {
        return 31083663600000L;
    }

    /* access modifiers changed from: protected */
    public void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
            fields.year = new SkipDateTimeField(this, fields.year);
            fields.weekyear = new SkipDateTimeField(this, fields.weekyear);
        }
    }
}
