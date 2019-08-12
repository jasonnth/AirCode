package com.airbnb.android.core.wishlists;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.models.WishList;
import p032rx.functions.Action1;

final /* synthetic */ class WishListManager$$Lambda$5 implements Action1 {
    private final WishListManager arg$1;
    private final WishListableData arg$2;
    private final WishList arg$3;

    private WishListManager$$Lambda$5(WishListManager wishListManager, WishListableData wishListableData, WishList wishList) {
        this.arg$1 = wishListManager;
        this.arg$2 = wishListableData;
        this.arg$3 = wishList;
    }

    public static Action1 lambdaFactory$(WishListManager wishListManager, WishListableData wishListableData, WishList wishList) {
        return new WishListManager$$Lambda$5(wishListManager, wishListableData, wishList);
    }

    public void call(Object obj) {
        this.arg$1.handleSaveItemFailure(this.arg$2, this.arg$3, (AirRequestNetworkException) obj);
    }
}
