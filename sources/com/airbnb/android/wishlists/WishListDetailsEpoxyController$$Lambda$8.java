package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishListedTrip;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$8 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;
    private final WishListedTrip arg$2;

    private WishListDetailsEpoxyController$$Lambda$8(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedTrip wishListedTrip) {
        this.arg$1 = wishListDetailsEpoxyController;
        this.arg$2 = wishListedTrip;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedTrip wishListedTrip) {
        return new WishListDetailsEpoxyController$$Lambda$8(wishListDetailsEpoxyController, wishListedTrip);
    }

    public void onClick(View view) {
        this.arg$1.onTripClicked(view, this.arg$2);
    }
}
