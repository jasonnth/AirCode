package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishlistedListing;

final /* synthetic */ class WishListDetailsMapFragment$$Lambda$1 implements OnClickListener {
    private final WishListDetailsMapFragment arg$1;
    private final WishlistedListing arg$2;

    private WishListDetailsMapFragment$$Lambda$1(WishListDetailsMapFragment wishListDetailsMapFragment, WishlistedListing wishlistedListing) {
        this.arg$1 = wishListDetailsMapFragment;
        this.arg$2 = wishlistedListing;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsMapFragment wishListDetailsMapFragment, WishlistedListing wishlistedListing) {
        return new WishListDetailsMapFragment$$Lambda$1(wishListDetailsMapFragment, wishlistedListing);
    }

    public void onClick(View view) {
        WishListDetailsMapFragment.lambda$buildListingModel$0(this.arg$1, this.arg$2, view);
    }
}
