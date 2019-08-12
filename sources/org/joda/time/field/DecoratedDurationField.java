package org.joda.time.field;

import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;

public class DecoratedDurationField extends BaseDurationField {
    private final DurationField iField;

    public DecoratedDurationField(DurationField durationField, DurationFieldType durationFieldType) {
        super(durationFieldType);
        if (durationField == null) {
            throw new IllegalArgumentException("The field must not be null");
        } else if (!durationField.isSupported()) {
            throw new IllegalArgumentException("The field must be supported");
        } else {
            this.iField = durationField;
        }
    }

    public final DurationField getWrappedField() {
        return this.iField;
    }

    public boolean isPrecise() {
        return this.iField.isPrecise();
    }

    public long add(long j, int i) {
        return this.iField.add(j, i);
    }

    public long add(long j, long j2) {
        return this.iField.add(j, j2);
    }

    public long getDifferenceAsLong(long j, long j2) {
        return this.iField.getDifferenceAsLong(j, j2);
    }

    public long getUnitMillis() {
        return this.iField.getUnitMillis();
    }
}
