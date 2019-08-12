package com.airbnb.android.wishlists;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsParentFragment$$Lambda$2 implements Action1 {
    private final WishListDetailsParentFragment arg$1;

    private WishListDetailsParentFragment$$Lambda$2(WishListDetailsParentFragment wishListDetailsParentFragment) {
        this.arg$1 = wishListDetailsParentFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsParentFragment wishListDetailsParentFragment) {
        return new WishListDetailsParentFragment$$Lambda$2(wishListDetailsParentFragment);
    }

    public void call(Object obj) {
        WishListDetailsParentFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
