package com.airbnb.android.lib.paidamenities.fragments.create;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.lib.paidamenities.fragments.create.SelectListingFragment.SelectListingAdapter;

final /* synthetic */ class SelectListingFragment$SelectListingAdapter$$Lambda$1 implements OnClickListener {
    private final SelectListingAdapter arg$1;
    private final Listing arg$2;

    private SelectListingFragment$SelectListingAdapter$$Lambda$1(SelectListingAdapter selectListingAdapter, Listing listing) {
        this.arg$1 = selectListingAdapter;
        this.arg$2 = listing;
    }

    public static OnClickListener lambdaFactory$(SelectListingAdapter selectListingAdapter, Listing listing) {
        return new SelectListingFragment$SelectListingAdapter$$Lambda$1(selectListingAdapter, listing);
    }

    public void onClick(View view) {
        SelectListingFragment.this.listener.onSelectListing(this.arg$2);
    }
}
