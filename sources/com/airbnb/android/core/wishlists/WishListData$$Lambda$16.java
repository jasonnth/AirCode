package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$16 implements WishListItemAction {
    private static final WishListData$$Lambda$16 instance = new WishListData$$Lambda$16();

    private WishListData$$Lambda$16() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removeListingIfExists(j);
    }
}
