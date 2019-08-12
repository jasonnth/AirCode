package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$13 implements WishListItemAction {
    private static final WishListData$$Lambda$13 instance = new WishListData$$Lambda$13();

    private WishListData$$Lambda$13() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removePlaceActivityIfExists(j);
    }
}
