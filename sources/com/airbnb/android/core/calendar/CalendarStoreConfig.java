package com.airbnb.android.core.calendar;

import com.airbnb.android.airdate.AirDate;

public class CalendarStoreConfig {
    private static final int DEFAULT_CACHE_TTL_MINUTES = 5;
    private static final int DEFAULT_MAX_MONTHS_FROM_TODAY = 36;
    private static final int DEFAULT_MIN_MONTHS_AGO = 12;
    private int cacheTTLMinutes;
    private AirDate maxDate;
    private AirDate minDate;

    public CalendarStoreConfig() {
        init(5, 12, 36);
    }

    public void init(int cacheTTLMinutes2, int minDateMinusMonths, int maxDatePlusMonths) {
        this.cacheTTLMinutes = cacheTTLMinutes2;
        this.minDate = AirDate.today().plusMonths(-minDateMinusMonths);
        this.maxDate = AirDate.today().plusMonths(maxDatePlusMonths);
    }

    public AirDate getMinDate() {
        return this.minDate;
    }

    public AirDate getMaxDate() {
        return this.maxDate;
    }

    public int getCacheTTLMinutes() {
        return this.cacheTTLMinutes;
    }
}
