package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$8 implements WishListItemAction {
    private static final WishListData$$Lambda$8 instance = new WishListData$$Lambda$8();

    private WishListData$$Lambda$8() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.addPlaceActivityIfNotExists(j);
    }
}
