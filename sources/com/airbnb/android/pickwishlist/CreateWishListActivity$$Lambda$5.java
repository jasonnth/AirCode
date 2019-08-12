package com.airbnb.android.pickwishlist;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreateWishListActivity$$Lambda$5 implements Action1 {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$5(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static Action1 lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$5(createWishListActivity);
    }

    public void call(Object obj) {
        CreateWishListActivity.lambda$new$7(this.arg$1, (AirRequestNetworkException) obj);
    }
}
