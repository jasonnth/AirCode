package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$$Lambda$2 implements Listener {
    private final CalendarWithPriceTipsUpdateFragment arg$1;

    private CalendarWithPriceTipsUpdateFragment$$Lambda$2(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        this.arg$1 = calendarWithPriceTipsUpdateFragment;
    }

    public static Listener lambdaFactory$(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        return new CalendarWithPriceTipsUpdateFragment$$Lambda$2(calendarWithPriceTipsUpdateFragment);
    }

    public void amountChanged(Integer num) {
        CalendarWithPriceTipsUpdateFragment.lambda$new$1(this.arg$1, num);
    }
}
