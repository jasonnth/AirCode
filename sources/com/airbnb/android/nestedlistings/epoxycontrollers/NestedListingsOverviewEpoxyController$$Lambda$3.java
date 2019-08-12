package com.airbnb.android.nestedlistings.epoxycontrollers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsOverviewEpoxyController.NestedListingsOverviewListener;

final /* synthetic */ class NestedListingsOverviewEpoxyController$$Lambda$3 implements OnClickListener {
    private final NestedListingsOverviewListener arg$1;
    private final NestedListing arg$2;

    private NestedListingsOverviewEpoxyController$$Lambda$3(NestedListingsOverviewListener nestedListingsOverviewListener, NestedListing nestedListing) {
        this.arg$1 = nestedListingsOverviewListener;
        this.arg$2 = nestedListing;
    }

    public static OnClickListener lambdaFactory$(NestedListingsOverviewListener nestedListingsOverviewListener, NestedListing nestedListing) {
        return new NestedListingsOverviewEpoxyController$$Lambda$3(nestedListingsOverviewListener, nestedListing);
    }

    public void onClick(View view) {
        this.arg$1.onEditExistingParent(this.arg$2);
    }
}
