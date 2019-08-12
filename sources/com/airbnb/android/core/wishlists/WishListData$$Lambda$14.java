package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$14 implements WishListItemAction {
    private static final WishListData$$Lambda$14 instance = new WishListData$$Lambda$14();

    private WishListData$$Lambda$14() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.removeStoryArticleIfExists(j);
    }
}
