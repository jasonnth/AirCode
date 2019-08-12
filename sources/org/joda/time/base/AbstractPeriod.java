package org.joda.time.base;

import org.joda.convert.ToString;
import org.joda.time.DurationFieldType;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.ISOPeriodFormat;

public abstract class AbstractPeriod implements ReadablePeriod {
    protected AbstractPeriod() {
    }

    public int size() {
        return getPeriodType().size();
    }

    public DurationFieldType getFieldType(int i) {
        return getPeriodType().getFieldType(i);
    }

    public int get(DurationFieldType durationFieldType) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf == -1) {
            return 0;
        }
        return getValue(indexOf);
    }

    public int indexOf(DurationFieldType durationFieldType) {
        return getPeriodType().indexOf(durationFieldType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadablePeriod)) {
            return false;
        }
        ReadablePeriod readablePeriod = (ReadablePeriod) obj;
        if (size() != readablePeriod.size()) {
            return false;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (getValue(i) != readablePeriod.getValue(i) || getFieldType(i) != readablePeriod.getFieldType(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (((i * 27) + getValue(i2)) * 27) + getFieldType(i2).hashCode();
        }
        return i;
    }

    @ToString
    public String toString() {
        return ISOPeriodFormat.standard().print(this);
    }
}
