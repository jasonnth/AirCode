package com.airbnb.android.wishlists;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsParentFragment$$Lambda$5 implements Action1 {
    private final WishListDetailsParentFragment arg$1;

    private WishListDetailsParentFragment$$Lambda$5(WishListDetailsParentFragment wishListDetailsParentFragment) {
        this.arg$1 = wishListDetailsParentFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsParentFragment wishListDetailsParentFragment) {
        return new WishListDetailsParentFragment$$Lambda$5(wishListDetailsParentFragment);
    }

    public void call(Object obj) {
        WishListDetailsParentFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
