package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$6 implements WishListItemAction {
    private static final WishListData$$Lambda$6 instance = new WishListData$$Lambda$6();

    private WishListData$$Lambda$6() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.addListingIfNotExists(j);
    }
}
