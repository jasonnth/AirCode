package com.airbnb.android.pickwishlist;

import com.airbnb.android.core.responses.WishListResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreateWishListActivity$$Lambda$4 implements Action1 {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$4(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static Action1 lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$4(createWishListActivity);
    }

    public void call(Object obj) {
        CreateWishListActivity.lambda$new$6(this.arg$1, (WishListResponse) obj);
    }
}
