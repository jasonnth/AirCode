package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListManager$$Lambda$3 implements Runnable {
    private final WishListManager arg$1;
    private final WishList arg$2;

    private WishListManager$$Lambda$3(WishListManager wishListManager, WishList wishList) {
        this.arg$1 = wishListManager;
        this.arg$2 = wishList;
    }

    public static Runnable lambdaFactory$(WishListManager wishListManager, WishList wishList) {
        return new WishListManager$$Lambda$3(wishListManager, wishList);
    }

    public void run() {
        this.arg$1.immediateRefreshWishList(this.arg$2);
    }
}
