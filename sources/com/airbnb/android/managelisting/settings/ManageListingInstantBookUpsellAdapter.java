package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.managelisting.C7368R;

public class ManageListingInstantBookUpsellAdapter extends ManageListingAdapter {
    ManageListingInstantBookUpsellAdapter(ManageListingDataController controller) {
        super(controller);
        addModel(new DocumentMarqueeEpoxyModel_().titleRes(C7213R.string.manage_listing_turn_on_instant_book_title));
        addModel(new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_turn_on_instant_book_benefit).titleMaxLine(10));
    }

    public void dataUpdated() {
    }

    public void dataLoading(boolean loading) {
    }
}
