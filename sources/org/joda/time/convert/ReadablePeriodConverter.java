package org.joda.time.convert;

import org.joda.time.ReadablePeriod;

class ReadablePeriodConverter extends AbstractConverter implements PeriodConverter {
    static final ReadablePeriodConverter INSTANCE = new ReadablePeriodConverter();

    protected ReadablePeriodConverter() {
    }

    public Class<?> getSupportedType() {
        return ReadablePeriod.class;
    }
}
