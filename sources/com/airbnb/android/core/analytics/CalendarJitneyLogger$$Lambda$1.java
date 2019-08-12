package com.airbnb.android.core.analytics;

import com.airbnb.android.core.models.CalendarDay;
import com.google.common.base.Function;

final /* synthetic */ class CalendarJitneyLogger$$Lambda$1 implements Function {
    private static final CalendarJitneyLogger$$Lambda$1 instance = new CalendarJitneyLogger$$Lambda$1();

    private CalendarJitneyLogger$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CalendarDay) obj).getDate().formatDate(CalendarJitneyLogger.DATE_FORMAT);
    }
}
