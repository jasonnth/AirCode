package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$12 implements WishListItemAction {
    private static final WishListData$$Lambda$12 instance = new WishListData$$Lambda$12();

    private WishListData$$Lambda$12() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removeTripIfExists(j);
    }
}
