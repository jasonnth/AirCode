package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishListedPlace;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$10 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;
    private final WishListedPlace arg$2;

    private WishListDetailsEpoxyController$$Lambda$10(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedPlace wishListedPlace) {
        this.arg$1 = wishListDetailsEpoxyController;
        this.arg$2 = wishListedPlace;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedPlace wishListedPlace) {
        return new WishListDetailsEpoxyController$$Lambda$10(wishListDetailsEpoxyController, wishListedPlace);
    }

    public void onClick(View view) {
        this.arg$1.onPlaceClicked(this.arg$2);
    }
}
