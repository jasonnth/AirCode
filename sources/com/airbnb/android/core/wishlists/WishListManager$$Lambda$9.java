package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import p032rx.functions.Action1;

final /* synthetic */ class WishListManager$$Lambda$9 implements Action1 {
    private final WishListManager arg$1;
    private final WishList arg$2;

    private WishListManager$$Lambda$9(WishListManager wishListManager, WishList wishList) {
        this.arg$1 = wishListManager;
        this.arg$2 = wishList;
    }

    public static Action1 lambdaFactory$(WishListManager wishListManager, WishList wishList) {
        return new WishListManager$$Lambda$9(wishListManager, wishList);
    }

    public void call(Object obj) {
        this.arg$1.delayedRefreshWishList(this.arg$2);
    }
}
