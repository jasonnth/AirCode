package org.joda.time.chrono;

import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.AssembledChronology.Fields;

public final class GregorianChronology extends BasicGJChronology {
    private static final GregorianChronology INSTANCE_UTC = getInstance(DateTimeZone.UTC);
    private static final ConcurrentHashMap<DateTimeZone, GregorianChronology[]> cCache = new ConcurrentHashMap<>();

    public static GregorianChronology getInstanceUTC() {
        return INSTANCE_UTC;
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone) {
        return getInstance(dateTimeZone, 4);
    }

    public static GregorianChronology getInstance(DateTimeZone dateTimeZone, int i) {
        GregorianChronology[] gregorianChronologyArr;
        if (dateTimeZone == null) {
            dateTimeZone = DateTimeZone.getDefault();
        }
        GregorianChronology[] gregorianChronologyArr2 = (GregorianChronology[]) cCache.get(dateTimeZone);
        if (gregorianChronologyArr2 == null) {
            gregorianChronologyArr = new GregorianChronology[7];
            GregorianChronology[] gregorianChronologyArr3 = (GregorianChronology[]) cCache.putIfAbsent(dateTimeZone, gregorianChronologyArr);
            if (gregorianChronologyArr3 != null) {
                gregorianChronologyArr = gregorianChronologyArr3;
            }
        } else {
            gregorianChronologyArr = gregorianChronologyArr2;
        }
        try {
            GregorianChronology gregorianChronology = gregorianChronologyArr[i - 1];
            if (gregorianChronology == null) {
                synchronized (gregorianChronologyArr) {
                    gregorianChronology = gregorianChronologyArr[i - 1];
                    if (gregorianChronology == null) {
                        if (dateTimeZone == DateTimeZone.UTC) {
                            gregorianChronology = new GregorianChronology(null, null, i);
                        } else {
                            gregorianChronology = new GregorianChronology(ZonedChronology.getInstance(getInstance(DateTimeZone.UTC, i), dateTimeZone), null, i);
                        }
                        gregorianChronologyArr[i - 1] = gregorianChronology;
                    }
                }
            }
            return gregorianChronology;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid min days in first week: " + i);
        }
    }

    private GregorianChronology(Chronology chronology, Object obj, int i) {
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

    /* access modifiers changed from: protected */
    public void assemble(Fields fields) {
        if (getBase() == null) {
            super.assemble(fields);
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isLeapYear(int i) {
        return (i & 3) == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    /* access modifiers changed from: 0000 */
    public long calculateFirstDayOfYearMillis(int i) {
        int i2;
        int i3 = i / 100;
        if (i < 0) {
            i2 = (((i3 + 3) >> 2) + (((i + 3) >> 2) - i3)) - 1;
        } else {
            i2 = (i3 >> 2) + ((i >> 2) - i3);
            if (isLeapYear(i)) {
                i2--;
            }
        }
        return (((long) (i2 - 719527)) + (((long) i) * 365)) * 86400000;
    }

    /* access modifiers changed from: 0000 */
    public int getMinYear() {
        return -292275054;
    }

    /* access modifiers changed from: 0000 */
    public int getMaxYear() {
        return 292278993;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerYear() {
        return 31556952000L;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerYearDividedByTwo() {
        return 15778476000L;
    }

    /* access modifiers changed from: 0000 */
    public long getAverageMillisPerMonth() {
        return 2629746000L;
    }

    /* access modifiers changed from: 0000 */
    public long getApproxMillisAtEpochDividedByTwo() {
        return 31083597720000L;
    }
}
