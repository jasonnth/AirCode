package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.PeriodType;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;

public abstract class BasePeriod extends AbstractPeriod implements Serializable, ReadablePeriod {
    private static final ReadablePeriod DUMMY_PERIOD = new AbstractPeriod() {
        public int getValue(int i) {
            return 0;
        }

        public PeriodType getPeriodType() {
            return PeriodType.time();
        }
    };
    private final PeriodType iType;
    private final int[] iValues;

    protected BasePeriod(ReadableInstant readableInstant, ReadableInstant readableInstant2, PeriodType periodType) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        if (readableInstant == null && readableInstant2 == null) {
            this.iType = checkPeriodType;
            this.iValues = new int[size()];
            return;
        }
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        long instantMillis2 = DateTimeUtils.getInstantMillis(readableInstant2);
        Chronology intervalChronology = DateTimeUtils.getIntervalChronology(readableInstant, readableInstant2);
        this.iType = checkPeriodType;
        this.iValues = intervalChronology.get(this, instantMillis, instantMillis2);
    }

    protected BasePeriod(ReadablePartial readablePartial, ReadablePartial readablePartial2, PeriodType periodType) {
        if (readablePartial == null || readablePartial2 == null) {
            throw new IllegalArgumentException("ReadablePartial objects must not be null");
        } else if ((readablePartial instanceof BaseLocal) && (readablePartial2 instanceof BaseLocal) && readablePartial.getClass() == readablePartial2.getClass()) {
            PeriodType checkPeriodType = checkPeriodType(periodType);
            long localMillis = ((BaseLocal) readablePartial).getLocalMillis();
            long localMillis2 = ((BaseLocal) readablePartial2).getLocalMillis();
            Chronology chronology = DateTimeUtils.getChronology(readablePartial.getChronology());
            this.iType = checkPeriodType;
            this.iValues = chronology.get(this, localMillis, localMillis2);
        } else if (readablePartial.size() != readablePartial2.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
        } else {
            int size = readablePartial.size();
            for (int i = 0; i < size; i++) {
                if (readablePartial.getFieldType(i) != readablePartial2.getFieldType(i)) {
                    throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
                }
            }
            if (!DateTimeUtils.isContiguous(readablePartial)) {
                throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
            }
            this.iType = checkPeriodType(periodType);
            Chronology withUTC = DateTimeUtils.getChronology(readablePartial.getChronology()).withUTC();
            this.iValues = withUTC.get(this, withUTC.set(readablePartial, 0), withUTC.set(readablePartial2, 0));
        }
    }

    protected BasePeriod(long j, PeriodType periodType, Chronology chronology) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iType = checkPeriodType;
        this.iValues = chronology2.get((ReadablePeriod) this, j);
    }

    /* access modifiers changed from: protected */
    public PeriodType checkPeriodType(PeriodType periodType) {
        return DateTimeUtils.getPeriodType(periodType);
    }

    public PeriodType getPeriodType() {
        return this.iType;
    }

    public int getValue(int i) {
        return this.iValues[i];
    }
}
