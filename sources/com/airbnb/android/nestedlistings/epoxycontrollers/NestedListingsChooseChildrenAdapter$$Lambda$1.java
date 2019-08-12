package com.airbnb.android.nestedlistings.epoxycontrollers;

import com.airbnb.android.core.models.NestedListing;
import com.google.common.base.Predicate;

final /* synthetic */ class NestedListingsChooseChildrenAdapter$$Lambda$1 implements Predicate {
    private final NestedListing arg$1;

    private NestedListingsChooseChildrenAdapter$$Lambda$1(NestedListing nestedListing) {
        this.arg$1 = nestedListing;
    }

    public static Predicate lambdaFactory$(NestedListing nestedListing) {
        return new NestedListingsChooseChildrenAdapter$$Lambda$1(nestedListing);
    }

    public boolean apply(Object obj) {
        return ((NestedListing) obj).hasThisParent(this.arg$1.getId());
    }
}
