package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.responses.WishListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WishListManager$$Lambda$1 implements Action1 {
    private final WishListManager arg$1;

    private WishListManager$$Lambda$1(WishListManager wishListManager) {
        this.arg$1 = wishListManager;
    }

    public static Action1 lambdaFactory$(WishListManager wishListManager) {
        return new WishListManager$$Lambda$1(wishListManager);
    }

    public void call(Object obj) {
        this.arg$1.addWishList(((WishListResponse) obj).wishList);
    }
}
