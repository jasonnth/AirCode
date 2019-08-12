package com.airbnb.android.pickwishlist;

import com.airbnb.android.utils.TextWatcherUtils.IsEmptyTextWatcher;

final /* synthetic */ class CreateWishListActivity$$Lambda$1 implements IsEmptyTextWatcher {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$1(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static IsEmptyTextWatcher lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$1(createWishListActivity);
    }

    public void textUpdated(boolean z) {
        CreateWishListActivity.lambda$new$4(this.arg$1, z);
    }
}
