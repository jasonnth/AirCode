package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EarlyBirdDiscountsEpoxyController$$Lambda$1 implements OnClickListener {
    private final EarlyBirdDiscountsEpoxyController arg$1;

    private EarlyBirdDiscountsEpoxyController$$Lambda$1(EarlyBirdDiscountsEpoxyController earlyBirdDiscountsEpoxyController) {
        this.arg$1 = earlyBirdDiscountsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(EarlyBirdDiscountsEpoxyController earlyBirdDiscountsEpoxyController) {
        return new EarlyBirdDiscountsEpoxyController$$Lambda$1(earlyBirdDiscountsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.showEarlyBirdDiscountLearnMore();
    }
}
