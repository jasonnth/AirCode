package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import java.util.List;

final /* synthetic */ class WishListData$$Lambda$4 implements WishListIdCallback {
    private static final WishListData$$Lambda$4 instance = new WishListData$$Lambda$4();

    private WishListData$$Lambda$4() {
    }

    public static WishListIdCallback lambdaFactory$() {
        return instance;
    }

    public List getWishListedIds(WishList wishList) {
        return wishList.getPlaceActivityIds();
    }
}
