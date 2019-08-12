package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.LongTermPricingExampleResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DiscountsExampleFragment$$Lambda$1 implements Action1 {
    private final DiscountsExampleFragment arg$1;

    private DiscountsExampleFragment$$Lambda$1(DiscountsExampleFragment discountsExampleFragment) {
        this.arg$1 = discountsExampleFragment;
    }

    public static Action1 lambdaFactory$(DiscountsExampleFragment discountsExampleFragment) {
        return new DiscountsExampleFragment$$Lambda$1(discountsExampleFragment);
    }

    public void call(Object obj) {
        DiscountsExampleFragment.lambda$new$0(this.arg$1, (LongTermPricingExampleResponse) obj);
    }
}
