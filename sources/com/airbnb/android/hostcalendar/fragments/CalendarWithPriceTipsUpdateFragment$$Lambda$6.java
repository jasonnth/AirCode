package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.jitney.event.logging.PriceTipDaysType.p206v1.C2571PriceTipDaysType;
import java.util.List;

final /* synthetic */ class CalendarWithPriceTipsUpdateFragment$$Lambda$6 implements OnClickListener {
    private final CalendarWithPriceTipsUpdateFragment arg$1;
    private final List arg$2;

    private CalendarWithPriceTipsUpdateFragment$$Lambda$6(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment, List list) {
        this.arg$1 = calendarWithPriceTipsUpdateFragment;
        this.arg$2 = list;
    }

    public static OnClickListener lambdaFactory$(CalendarWithPriceTipsUpdateFragment calendarWithPriceTipsUpdateFragment, List list) {
        return new CalendarWithPriceTipsUpdateFragment$$Lambda$6(calendarWithPriceTipsUpdateFragment, list);
    }

    public void onClick(View view) {
        this.arg$1.pricingJitneyLogger.adoptCalendarPriceTip(this.arg$1.listingId, this.arg$2, C2571PriceTipDaysType.SingleDay);
    }
}
