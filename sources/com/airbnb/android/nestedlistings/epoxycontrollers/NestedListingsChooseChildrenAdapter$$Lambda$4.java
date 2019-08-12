package com.airbnb.android.nestedlistings.epoxycontrollers;

import com.airbnb.android.core.models.NestedListing;
import com.google.common.base.Function;

final /* synthetic */ class NestedListingsChooseChildrenAdapter$$Lambda$4 implements Function {
    private static final NestedListingsChooseChildrenAdapter$$Lambda$4 instance = new NestedListingsChooseChildrenAdapter$$Lambda$4();

    private NestedListingsChooseChildrenAdapter$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((NestedListing) obj).getId());
    }
}
