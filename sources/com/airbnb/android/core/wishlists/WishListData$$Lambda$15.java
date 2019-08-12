package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$15 implements WishListItemAction {
    private static final WishListData$$Lambda$15 instance = new WishListData$$Lambda$15();

    private WishListData$$Lambda$15() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removePlaceIfExists(j);
    }
}
