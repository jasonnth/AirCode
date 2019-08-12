package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import java.util.List;

final /* synthetic */ class WishListData$$Lambda$2 implements WishListIdCallback {
    private static final WishListData$$Lambda$2 instance = new WishListData$$Lambda$2();

    private WishListData$$Lambda$2() {
    }

    public static WishListIdCallback lambdaFactory$() {
        return instance;
    }

    public List getWishListedIds(WishList wishList) {
        return wishList.getTripIds();
    }
}
