package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$10 implements WishListItemAction {
    private static final WishListData$$Lambda$10 instance = new WishListData$$Lambda$10();

    private WishListData$$Lambda$10() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.addPlaceIfNotExists(j);
    }
}
