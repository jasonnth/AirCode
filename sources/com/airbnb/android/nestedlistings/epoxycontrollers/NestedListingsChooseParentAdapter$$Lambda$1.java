package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.NestedListing;

final /* synthetic */ class NestedListingsChooseParentAdapter$$Lambda$1 implements OnClickListener {
    private final NestedListingsChooseParentAdapter arg$1;
    private final NestedListing arg$2;

    private NestedListingsChooseParentAdapter$$Lambda$1(NestedListingsChooseParentAdapter nestedListingsChooseParentAdapter, NestedListing nestedListing) {
        this.arg$1 = nestedListingsChooseParentAdapter;
        this.arg$2 = nestedListing;
    }

    public static OnClickListener lambdaFactory$(NestedListingsChooseParentAdapter nestedListingsChooseParentAdapter, NestedListing nestedListing) {
        return new NestedListingsChooseParentAdapter$$Lambda$1(nestedListingsChooseParentAdapter, nestedListing);
    }

    public void onClick(View view) {
        this.arg$1.listener.onChooseParent(this.arg$2);
    }
}
