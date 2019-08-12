package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;

public abstract class ListingInsightsEpoxyModel extends AirEpoxyModel<ListingInsightsView> {
    String description;
    String dismissText;
    OnClickListener goToCardsClickListener;
    String listingImageUrl;
    String title;

    public void bind(ListingInsightsView view) {
        super.bind(view);
        view.setTitle(this.title);
        view.setDescription(this.description);
        view.setListingImage(this.listingImageUrl);
        view.setOnClickListener(this.goToCardsClickListener);
    }
}
