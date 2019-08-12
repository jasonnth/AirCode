package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$7 implements WishListItemAction {
    private static final WishListData$$Lambda$7 instance = new WishListData$$Lambda$7();

    private WishListData$$Lambda$7() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.addTripIfNotExists(j);
    }
}
