package com.airbnb.android.core.wishlists;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.models.WishList;
import p032rx.functions.Action1;

final /* synthetic */ class WishListManager$$Lambda$7 implements Action1 {
    private final WishListManager arg$1;
    private final WishList arg$2;

    private WishListManager$$Lambda$7(WishListManager wishListManager, WishList wishList) {
        this.arg$1 = wishListManager;
        this.arg$2 = wishList;
    }

    public static Action1 lambdaFactory$(WishListManager wishListManager, WishList wishList) {
        return new WishListManager$$Lambda$7(wishListManager, wishList);
    }

    public void call(Object obj) {
        WishListManager.lambda$saveItemToSuggestedWishList$6(this.arg$1, this.arg$2, (AirRequestNetworkException) obj);
    }
}
