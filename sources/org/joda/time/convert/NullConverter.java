package org.joda.time.convert;

class NullConverter extends AbstractConverter implements DurationConverter, InstantConverter, IntervalConverter, PartialConverter, PeriodConverter {
    static final NullConverter INSTANCE = new NullConverter();

    protected NullConverter() {
    }

    public Class<?> getSupportedType() {
        return null;
    }
}
