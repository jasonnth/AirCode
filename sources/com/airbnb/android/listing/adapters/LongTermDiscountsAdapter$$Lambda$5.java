package com.airbnb.android.listing.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LongTermDiscountsAdapter$$Lambda$5 implements OnClickListener {
    private final LongTermDiscountsAdapter arg$1;

    private LongTermDiscountsAdapter$$Lambda$5(LongTermDiscountsAdapter longTermDiscountsAdapter) {
        this.arg$1 = longTermDiscountsAdapter;
    }

    public static OnClickListener lambdaFactory$(LongTermDiscountsAdapter longTermDiscountsAdapter) {
        return new LongTermDiscountsAdapter$$Lambda$5(longTermDiscountsAdapter);
    }

    public void onClick(View view) {
        this.arg$1.listener.showLengthOfStayDiscountLearnMore();
    }
}
