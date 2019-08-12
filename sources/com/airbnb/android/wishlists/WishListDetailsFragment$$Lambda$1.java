package com.airbnb.android.wishlists;

import com.airbnb.android.core.responses.WishListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsFragment$$Lambda$1 implements Action1 {
    private final WishListDetailsFragment arg$1;

    private WishListDetailsFragment$$Lambda$1(WishListDetailsFragment wishListDetailsFragment) {
        this.arg$1 = wishListDetailsFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsFragment wishListDetailsFragment) {
        return new WishListDetailsFragment$$Lambda$1(wishListDetailsFragment);
    }

    public void call(Object obj) {
        WishListDetailsFragment.lambda$new$1(this.arg$1, (WishListResponse) obj);
    }
}
