package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$$Lambda$5 implements OnCheckedChangeListener {
    private final CalendarWithPriceTipsUpdateFragment arg$1;

    private CalendarWithPriceTipsUpdateFragment$$Lambda$5(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        this.arg$1 = calendarWithPriceTipsUpdateFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        return new CalendarWithPriceTipsUpdateFragment$$Lambda$5(calendarWithPriceTipsUpdateFragment);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        CalendarWithPriceTipsUpdateFragment.lambda$initSmartPricing$4(this.arg$1, switchRowInterface, z);
    }
}
