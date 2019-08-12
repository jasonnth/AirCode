package com.airbnb.android.listing;

import com.google.common.base.Predicate;

final /* synthetic */ class LYSStep$$Lambda$11 implements Predicate {
    private final LYSCollection arg$1;

    private LYSStep$$Lambda$11(LYSCollection lYSCollection) {
        this.arg$1 = lYSCollection;
    }

    public static Predicate lambdaFactory$(LYSCollection lYSCollection) {
        return new LYSStep$$Lambda$11(lYSCollection);
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getStepsForCollection$10(this.arg$1, (LYSStep) obj);
    }
}
