package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$11 implements WishListItemAction {
    private static final WishListData$$Lambda$11 instance = new WishListData$$Lambda$11();

    private WishListData$$Lambda$11() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removeListingIfExists(j);
    }
}
