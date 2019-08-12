package com.airbnb.android.nestedlistings.epoxycontrollers;

import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.viewcomponents.models.NestedListingToggleRowEpoxyModel_;
import com.airbnb.p027n2.components.NestedListingToggleRow;
import com.airbnb.p027n2.components.NestedListingToggleRow.OnCheckChangedListener;

final /* synthetic */ class NestedListingsChooseChildrenAdapter$$Lambda$5 implements OnCheckChangedListener {
    private final NestedListingsChooseChildrenAdapter arg$1;
    private final NestedListingToggleRowEpoxyModel_ arg$2;
    private final NestedListing arg$3;

    private NestedListingsChooseChildrenAdapter$$Lambda$5(NestedListingsChooseChildrenAdapter nestedListingsChooseChildrenAdapter, NestedListingToggleRowEpoxyModel_ nestedListingToggleRowEpoxyModel_, NestedListing nestedListing) {
        this.arg$1 = nestedListingsChooseChildrenAdapter;
        this.arg$2 = nestedListingToggleRowEpoxyModel_;
        this.arg$3 = nestedListing;
    }

    public static OnCheckChangedListener lambdaFactory$(NestedListingsChooseChildrenAdapter nestedListingsChooseChildrenAdapter, NestedListingToggleRowEpoxyModel_ nestedListingToggleRowEpoxyModel_, NestedListing nestedListing) {
        return new NestedListingsChooseChildrenAdapter$$Lambda$5(nestedListingsChooseChildrenAdapter, nestedListingToggleRowEpoxyModel_, nestedListing);
    }

    public void onCheckChanged(NestedListingToggleRow nestedListingToggleRow) {
        NestedListingsChooseChildrenAdapter.lambda$addRow$2(this.arg$1, this.arg$2, this.arg$3, nestedListingToggleRow);
    }
}
