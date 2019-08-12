package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.p027n2.interfaces.ThreeWayToggle.ToggleState;
import com.airbnb.p027n2.primitives.TriStateSwitch;
import com.airbnb.p027n2.primitives.TriStateSwitch.OnCheckedChangeListener;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$$Lambda$4 implements OnCheckedChangeListener {
    private final CalendarWithPriceTipsUpdateFragment arg$1;

    private CalendarWithPriceTipsUpdateFragment$$Lambda$4(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        this.arg$1 = calendarWithPriceTipsUpdateFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment) {
        return new CalendarWithPriceTipsUpdateFragment$$Lambda$4(calendarWithPriceTipsUpdateFragment);
    }

    public void onCheckedChanged(TriStateSwitch triStateSwitch, ToggleState toggleState) {
        CalendarWithPriceTipsUpdateFragment.lambda$initSmartPricing$3(this.arg$1, triStateSwitch, toggleState);
    }
}
