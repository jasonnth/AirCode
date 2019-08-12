package com.airbnb.android.hostcalendar.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class MultiDayPriceTipsEpoxyController$$Lambda$1 implements OnClickListener {
    private final MultiDayPriceTipsEpoxyController arg$1;

    private MultiDayPriceTipsEpoxyController$$Lambda$1(MultiDayPriceTipsEpoxyController multiDayPriceTipsEpoxyController) {
        this.arg$1 = multiDayPriceTipsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(MultiDayPriceTipsEpoxyController multiDayPriceTipsEpoxyController) {
        return new MultiDayPriceTipsEpoxyController$$Lambda$1(multiDayPriceTipsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.clickListener.onDisclaimerClicked();
    }
}
