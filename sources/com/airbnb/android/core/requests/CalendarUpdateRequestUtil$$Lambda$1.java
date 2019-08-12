package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.CalendarDay;
import com.google.common.base.Function;

final /* synthetic */ class CalendarUpdateRequestUtil$$Lambda$1 implements Function {
    private static final CalendarUpdateRequestUtil$$Lambda$1 instance = new CalendarUpdateRequestUtil$$Lambda$1();

    private CalendarUpdateRequestUtil$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CalendarDay) obj).getDate();
    }
}
