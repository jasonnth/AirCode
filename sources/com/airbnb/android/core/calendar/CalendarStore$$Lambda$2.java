package com.airbnb.android.core.calendar;

import com.airbnb.android.core.models.CalendarDay;
import java.util.Comparator;

final /* synthetic */ class CalendarStore$$Lambda$2 implements Comparator {
    private static final CalendarStore$$Lambda$2 instance = new CalendarStore$$Lambda$2();

    private CalendarStore$$Lambda$2() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((CalendarDay) obj).getDate().compareTo(((CalendarDay) obj2).getDate());
    }
}
