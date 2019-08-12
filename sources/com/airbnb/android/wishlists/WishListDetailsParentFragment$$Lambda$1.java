package com.airbnb.android.wishlists;

import com.airbnb.android.core.responses.WishListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsParentFragment$$Lambda$1 implements Action1 {
    private final WishListDetailsParentFragment arg$1;

    private WishListDetailsParentFragment$$Lambda$1(WishListDetailsParentFragment wishListDetailsParentFragment) {
        this.arg$1 = wishListDetailsParentFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsParentFragment wishListDetailsParentFragment) {
        return new WishListDetailsParentFragment$$Lambda$1(wishListDetailsParentFragment);
    }

    public void call(Object obj) {
        this.arg$1.handleUpdatedWishList(((WishListResponse) obj).wishList);
    }
}
