package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MultiDayPriceTipsFragment$$Lambda$2 implements OnClickListener {
    private final MultiDayPriceTipsFragment arg$1;

    private MultiDayPriceTipsFragment$$Lambda$2(MultiDayPriceTipsFragment multiDayPriceTipsFragment) {
        this.arg$1 = multiDayPriceTipsFragment;
    }

    public static OnClickListener lambdaFactory$(MultiDayPriceTipsFragment multiDayPriceTipsFragment) {
        return new MultiDayPriceTipsFragment$$Lambda$2(multiDayPriceTipsFragment);
    }

    public void onClick(View view) {
        this.arg$1.onApplyButtonClicked(view);
    }
}
