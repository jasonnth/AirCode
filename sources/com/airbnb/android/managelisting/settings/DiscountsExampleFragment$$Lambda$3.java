package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DiscountsExampleFragment$$Lambda$3 implements OnClickListener {
    private final DiscountsExampleFragment arg$1;

    private DiscountsExampleFragment$$Lambda$3(DiscountsExampleFragment discountsExampleFragment) {
        this.arg$1 = discountsExampleFragment;
    }

    public static OnClickListener lambdaFactory$(DiscountsExampleFragment discountsExampleFragment) {
        return new DiscountsExampleFragment$$Lambda$3(discountsExampleFragment);
    }

    public void onClick(View view) {
        this.arg$1.fetchExample();
    }
}
