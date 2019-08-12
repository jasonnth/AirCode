package com.airbnb.android.wishlists;

import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsParentFragment$$Lambda$4 implements Action1 {
    private final WishListDetailsParentFragment arg$1;

    private WishListDetailsParentFragment$$Lambda$4(WishListDetailsParentFragment wishListDetailsParentFragment) {
        this.arg$1 = wishListDetailsParentFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsParentFragment wishListDetailsParentFragment) {
        return new WishListDetailsParentFragment$$Lambda$4(wishListDetailsParentFragment);
    }

    public void call(Object obj) {
        WishListDetailsParentFragment.lambda$new$3(this.arg$1, (Boolean) obj);
    }
}
