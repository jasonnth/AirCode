package com.airbnb.android.listing;

import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$3 implements Predicate {
    private final boolean arg$1;
    private final LYSStep arg$2;

    private LYSStep$$Lambda$3(boolean z, LYSStep lYSStep) {
        this.arg$1 = z;
        this.arg$2 = lYSStep;
    }

    public static Predicate lambdaFactory$(boolean z, LYSStep lYSStep) {
        return new LYSStep$$Lambda$3(z, lYSStep);
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getFirstNonskippableStep$2(this.arg$1, this.arg$2, (LYSStep) obj);
    }
}
