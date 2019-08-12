package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListData$$Lambda$9 implements WishListItemAction {
    private static final WishListData$$Lambda$9 instance = new WishListData$$Lambda$9();

    private WishListData$$Lambda$9() {
    }

    public static WishListItemAction lambdaFactory$() {
        return instance;
    }

    public void action(WishList wishList, long j) {
        wishList.addStoryArticleIfNotExists(j);
    }
}
