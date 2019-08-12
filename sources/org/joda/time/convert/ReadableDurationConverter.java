package org.joda.time.convert;

import org.joda.time.ReadableDuration;

class ReadableDurationConverter extends AbstractConverter implements DurationConverter, PeriodConverter {
    static final ReadableDurationConverter INSTANCE = new ReadableDurationConverter();

    protected ReadableDurationConverter() {
    }

    public Class<?> getSupportedType() {
        return ReadableDuration.class;
    }
}
