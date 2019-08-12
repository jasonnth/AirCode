package com.airbnb.android.listing;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Function;

final /* synthetic */ class LYSStep$$Lambda$8 implements Function {
    private static final LYSStep$$Lambda$8 instance = new LYSStep$$Lambda$8();

    private LYSStep$$Lambda$8() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((AccountVerification) obj).getType();
    }
}
