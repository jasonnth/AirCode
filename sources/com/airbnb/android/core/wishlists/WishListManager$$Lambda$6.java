package com.airbnb.android.core.wishlists;

import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.responses.WishListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WishListManager$$Lambda$6 implements Action1 {
    private final WishListManager arg$1;
    private final WishList arg$2;

    private WishListManager$$Lambda$6(WishListManager wishListManager, WishList wishList) {
        this.arg$1 = wishListManager;
        this.arg$2 = wishList;
    }

    public static Action1 lambdaFactory$(WishListManager wishListManager, WishList wishList) {
        return new WishListManager$$Lambda$6(wishListManager, wishList);
    }

    public void call(Object obj) {
        WishListManager.lambda$saveItemToSuggestedWishList$5(this.arg$1, this.arg$2, (WishListResponse) obj);
    }
}