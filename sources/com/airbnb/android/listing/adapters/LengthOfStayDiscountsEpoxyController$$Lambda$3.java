package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LengthOfStayDiscountsEpoxyController$$Lambda$3 implements OnClickListener {
    private final LengthOfStayDiscountsEpoxyController arg$1;
    private final int arg$2;

    private LengthOfStayDiscountsEpoxyController$$Lambda$3(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController, int i) {
        this.arg$1 = lengthOfStayDiscountsEpoxyController;
        this.arg$2 = i;
    }

    public static OnClickListener lambdaFactory$(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController, int i) {
        return new LengthOfStayDiscountsEpoxyController$$Lambda$3(lengthOfStayDiscountsEpoxyController, i);
    }

    public void onClick(View view) {
        this.arg$1.onTipClicked(this.arg$2);
    }
}
