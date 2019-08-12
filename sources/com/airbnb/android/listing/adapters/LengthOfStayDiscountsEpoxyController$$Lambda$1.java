package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LengthOfStayDiscountsEpoxyController$$Lambda$1 implements OnClickListener {
    private final LengthOfStayDiscountsEpoxyController arg$1;

    private LengthOfStayDiscountsEpoxyController$$Lambda$1(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController) {
        this.arg$1 = lengthOfStayDiscountsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController) {
        return new LengthOfStayDiscountsEpoxyController$$Lambda$1(lengthOfStayDiscountsEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.showLengthOfStayDiscountLearnMore();
    }
}
