package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LastMinuteDiscountsEpoxyController$$Lambda$1 implements OnClickListener {
    private final LastMinuteDiscountsEpoxyController arg$1;

    private LastMinuteDiscountsEpoxyController$$Lambda$1(LastMinuteDiscountsEpoxyController lastMinuteDiscountsEpoxyController) {
        this.arg$1 = lastMinuteDiscountsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(LastMinuteDiscountsEpoxyController lastMinuteDiscountsEpoxyController) {
        return new LastMinuteDiscountsEpoxyController$$Lambda$1(lastMinuteDiscountsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.showLastMinuteDiscountLearnMore();
    }
}
