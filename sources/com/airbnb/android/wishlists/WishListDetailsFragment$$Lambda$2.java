package com.airbnb.android.wishlists;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsFragment$$Lambda$2 implements Action1 {
    private final WishListDetailsFragment arg$1;

    private WishListDetailsFragment$$Lambda$2(WishListDetailsFragment wishListDetailsFragment) {
        this.arg$1 = wishListDetailsFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsFragment wishListDetailsFragment) {
        return new WishListDetailsFragment$$Lambda$2(wishListDetailsFragment);
    }

    public void call(Object obj) {
        this.arg$1.showNetworkError((AirRequestNetworkException) obj);
    }
}
