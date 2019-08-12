package com.airbnb.android.pickwishlist;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class CreateWishListActivity$$Lambda$8 implements OnFocusChangeListener {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$8(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static OnFocusChangeListener lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$8(createWishListActivity);
    }

    public void onFocusChange(View view, boolean z) {
        CreateWishListActivity.lambda$initNameInput$2(this.arg$1, view, z);
    }
}
