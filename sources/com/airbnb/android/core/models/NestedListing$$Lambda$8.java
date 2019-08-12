package com.airbnb.android.core.models;

import com.google.common.base.Function;

final /* synthetic */ class NestedListing$$Lambda$8 implements Function {
    private static final NestedListing$$Lambda$8 instance = new NestedListing$$Lambda$8();

    private NestedListing$$Lambda$8() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((NestedListing) obj).getId());
    }
}
