package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class LengthOfStayDiscountsEpoxyController$$Lambda$2 implements Listener {
    private final LengthOfStayDiscountsEpoxyController arg$1;
    private final int arg$2;

    private LengthOfStayDiscountsEpoxyController$$Lambda$2(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController, int i) {
        this.arg$1 = lengthOfStayDiscountsEpoxyController;
        this.arg$2 = i;
    }

    public static Listener lambdaFactory$(LengthOfStayDiscountsEpoxyController lengthOfStayDiscountsEpoxyController, int i) {
        return new LengthOfStayDiscountsEpoxyController$$Lambda$2(lengthOfStayDiscountsEpoxyController, i);
    }

    public void amountChanged(Integer num) {
        this.arg$1.onAmountChanged(this.arg$2, num);
    }
}
