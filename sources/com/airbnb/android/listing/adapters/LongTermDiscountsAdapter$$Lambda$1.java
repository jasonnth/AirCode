package com.airbnb.android.listing.adapters;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

final /* synthetic */ class LongTermDiscountsAdapter$$Lambda$1 implements Listener {
    private final LongTermDiscountsAdapter arg$1;

    private LongTermDiscountsAdapter$$Lambda$1(LongTermDiscountsAdapter longTermDiscountsAdapter) {
        this.arg$1 = longTermDiscountsAdapter;
    }

    public static Listener lambdaFactory$(LongTermDiscountsAdapter longTermDiscountsAdapter) {
        return new LongTermDiscountsAdapter$$Lambda$1(longTermDiscountsAdapter);
    }

    public void amountChanged(Integer num) {
        this.arg$1.setWeeklyDiscountValue();
    }
}
