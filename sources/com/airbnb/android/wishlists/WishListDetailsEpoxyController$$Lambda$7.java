package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishlistedListing;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$7 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;
    private final WishlistedListing arg$2;

    private WishListDetailsEpoxyController$$Lambda$7(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishlistedListing wishlistedListing) {
        this.arg$1 = wishListDetailsEpoxyController;
        this.arg$2 = wishlistedListing;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishlistedListing wishlistedListing) {
        return new WishListDetailsEpoxyController$$Lambda$7(wishListDetailsEpoxyController, wishlistedListing);
    }

    public void onClick(View view) {
        this.arg$1.onListingClicked(view, this.arg$2);
    }
}
