package org.joda.time;

import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import java.io.Serializable;
import org.jmrtd.PassportService;

public abstract class DurationFieldType implements Serializable {
    static final DurationFieldType CENTURIES_TYPE = new StandardDurationFieldType("centuries", 2);
    static final DurationFieldType DAYS_TYPE = new StandardDurationFieldType(CalendarRulesRequestConstants.DAYS, 7);
    static final DurationFieldType ERAS_TYPE = new StandardDurationFieldType("eras", 1);
    static final DurationFieldType HALFDAYS_TYPE = new StandardDurationFieldType("halfdays", 8);
    static final DurationFieldType HOURS_TYPE = new StandardDurationFieldType("hours", 9);
    static final DurationFieldType MILLIS_TYPE = new StandardDurationFieldType("millis", PassportService.SF_DG12);
    static final DurationFieldType MINUTES_TYPE = new StandardDurationFieldType("minutes", 10);
    static final DurationFieldType MONTHS_TYPE = new StandardDurationFieldType("months", 5);
    static final DurationFieldType SECONDS_TYPE = new StandardDurationFieldType("seconds", PassportService.SF_DG11);
    static final DurationFieldType WEEKS_TYPE = new StandardDurationFieldType("weeks", 6);
    static final DurationFieldType WEEKYEARS_TYPE = new StandardDurationFieldType("weekyears", 3);
    static final DurationFieldType YEARS_TYPE = new StandardDurationFieldType("years", 4);
    private final String iName;

    private static class StandardDurationFieldType extends DurationFieldType {
        private final byte iOrdinal;

        StandardDurationFieldType(String str, byte b) {
            super(str);
            this.iOrdinal = b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StandardDurationFieldType)) {
                return false;
            }
            if (this.iOrdinal != ((StandardDurationFieldType) obj).iOrdinal) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return 1 << this.iOrdinal;
        }

        public DurationField getField(Chronology chronology) {
            Chronology chronology2 = DateTimeUtils.getChronology(chronology);
            switch (this.iOrdinal) {
                case 1:
                    return chronology2.eras();
                case 2:
                    return chronology2.centuries();
                case 3:
                    return chronology2.weekyears();
                case 4:
                    return chronology2.years();
                case 5:
                    return chronology2.months();
                case 6:
                    return chronology2.weeks();
                case 7:
                    return chronology2.days();
                case 8:
                    return chronology2.halfdays();
                case 9:
                    return chronology2.hours();
                case 10:
                    return chronology2.minutes();
                case 11:
                    return chronology2.seconds();
                case 12:
                    return chronology2.millis();
                default:
                    throw new InternalError();
            }
        }

        private Object readResolve() {
            switch (this.iOrdinal) {
                case 1:
                    return ERAS_TYPE;
                case 2:
                    return CENTURIES_TYPE;
                case 3:
                    return WEEKYEARS_TYPE;
                case 4:
                    return YEARS_TYPE;
                case 5:
                    return MONTHS_TYPE;
                case 6:
                    return WEEKS_TYPE;
                case 7:
                    return DAYS_TYPE;
                case 8:
                    return HALFDAYS_TYPE;
                case 9:
                    return HOURS_TYPE;
                case 10:
                    return MINUTES_TYPE;
                case 11:
                    return SECONDS_TYPE;
                case 12:
                    return MILLIS_TYPE;
                default:
                    return this;
            }
        }
    }

    public abstract DurationField getField(Chronology chronology);

    protected DurationFieldType(String str) {
        this.iName = str;
    }

    public static DurationFieldType millis() {
        return MILLIS_TYPE;
    }

    public static DurationFieldType seconds() {
        return SECONDS_TYPE;
    }

    public static DurationFieldType minutes() {
        return MINUTES_TYPE;
    }

    public static DurationFieldType hours() {
        return HOURS_TYPE;
    }

    public static DurationFieldType halfdays() {
        return HALFDAYS_TYPE;
    }

    public static DurationFieldType days() {
        return DAYS_TYPE;
    }

    public static DurationFieldType weeks() {
        return WEEKS_TYPE;
    }

    public static DurationFieldType weekyears() {
        return WEEKYEARS_TYPE;
    }

    public static DurationFieldType months() {
        return MONTHS_TYPE;
    }

    public static DurationFieldType years() {
        return YEARS_TYPE;
    }

    public static DurationFieldType centuries() {
        return CENTURIES_TYPE;
    }

    public static DurationFieldType eras() {
        return ERAS_TYPE;
    }

    public String getName() {
        return this.iName;
    }

    public String toString() {
        return getName();
    }
}
