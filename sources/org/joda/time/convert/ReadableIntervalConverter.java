package org.joda.time.convert;

import org.joda.time.ReadableInterval;

class ReadableIntervalConverter extends AbstractConverter implements DurationConverter, IntervalConverter, PeriodConverter {
    static final ReadableIntervalConverter INSTANCE = new ReadableIntervalConverter();

    protected ReadableIntervalConverter() {
    }

    public Class<?> getSupportedType() {
        return ReadableInterval.class;
    }
}
