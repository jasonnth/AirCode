package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import java.util.List;

final /* synthetic */ class WishListData$$Lambda$5 implements WishListIdCallback {
    private static final WishListData$$Lambda$5 instance = new WishListData$$Lambda$5();

    private WishListData$$Lambda$5() {
    }

    public static WishListIdCallback lambdaFactory$() {
        return instance;
    }

    public List getWishListedIds(WishList wishList) {
        return wishList.getArticleIds();
    }
}
