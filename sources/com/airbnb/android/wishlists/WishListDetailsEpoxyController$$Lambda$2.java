package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$2 implements OnClickListener {
    private final WishListDetailsEpoxyController arg$1;

    private WishListDetailsEpoxyController$$Lambda$2(WishListDetailsEpoxyController wishListDetailsEpoxyController) {
        this.arg$1 = wishListDetailsEpoxyController;
    }

    public static OnClickListener lambdaFactory$(WishListDetailsEpoxyController wishListDetailsEpoxyController) {
        return new WishListDetailsEpoxyController$$Lambda$2(wishListDetailsEpoxyController);
    }

    public void onClick(View view) {
        WishListDetailsEpoxyController.lambda$addMarquee$0(this.arg$1, view);
    }
}
