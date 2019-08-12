package com.airbnb.android.pickwishlist;

import com.airbnb.p027n2.utils.RadioRowManagerSelectionListener;

final /* synthetic */ class CreateWishListActivity$$Lambda$7 implements RadioRowManagerSelectionListener {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$7(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static RadioRowManagerSelectionListener lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$7(createWishListActivity);
    }

    public void onRadioRowSelection(Object obj) {
        CreateWishListActivity.lambda$onCreate$1(this.arg$1, (Boolean) obj);
    }
}
