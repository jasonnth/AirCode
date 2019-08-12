package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishListedPlaceActivity;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$11 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;
    private final WishListedPlaceActivity arg$2;

    private WishListDetailsEpoxyController$$Lambda$11(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedPlaceActivity wishListedPlaceActivity) {
        this.arg$1 = wishListDetailsEpoxyController;
        this.arg$2 = wishListedPlaceActivity;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController, WishListedPlaceActivity wishListedPlaceActivity) {
        return new WishListDetailsEpoxyController$$Lambda$11(wishListDetailsEpoxyController, wishListedPlaceActivity);
    }

    public void onClick(View view) {
        this.arg$1.onPlaceActivityClicked(this.arg$2);
    }
}
