package com.airbnb.android.core.views;

import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface.OnWishListedStatusSetListener;

final /* synthetic */ class WishListIcon$$Lambda$2 implements OnWishListedStatusSetListener {
    private final WishListIcon arg$1;

    private WishListIcon$$Lambda$2(WishListIcon wishListIcon) {
        this.arg$1 = wishListIcon;
    }

    public static OnWishListedStatusSetListener lambdaFactory$(WishListIcon wishListIcon) {
        return new WishListIcon$$Lambda$2(wishListIcon);
    }

    public void onWishListedStatusSet(boolean z) {
        this.arg$1.updateHeartDrawable(z);
    }
}