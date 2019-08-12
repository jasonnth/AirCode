package com.airbnb.android.hostcalendar.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.hostcalendar.adapters.NestedListingViewAdapter.NestedListingViewListener;

final /* synthetic */ class NestedListingViewAdapter$$Lambda$1 implements OnClickListener {
    private final NestedListingViewListener arg$1;
    private final NestedListing arg$2;

    private NestedListingViewAdapter$$Lambda$1(NestedListingViewListener nestedListingViewListener, NestedListing nestedListing) {
        this.arg$1 = nestedListingViewListener;
        this.arg$2 = nestedListing;
    }

    public static OnClickListener lambdaFactory$(NestedListingViewListener nestedListingViewListener, NestedListing nestedListing) {
        return new NestedListingViewAdapter$$Lambda$1(nestedListingViewListener, nestedListing);
    }

    public void onClick(View view) {
        this.arg$1.onClickListing(this.arg$2);
    }
}
