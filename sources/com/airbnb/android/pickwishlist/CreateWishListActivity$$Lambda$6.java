package com.airbnb.android.pickwishlist;

import com.airbnb.p027n2.interfaces.Scrollable.ScrollViewOnScrollListener;

final /* synthetic */ class CreateWishListActivity$$Lambda$6 implements ScrollViewOnScrollListener {
    private final CreateWishListActivity arg$1;

    private CreateWishListActivity$$Lambda$6(CreateWishListActivity createWishListActivity) {
        this.arg$1 = createWishListActivity;
    }

    public static ScrollViewOnScrollListener lambdaFactory$(CreateWishListActivity createWishListActivity) {
        return new CreateWishListActivity$$Lambda$6(createWishListActivity);
    }

    public void onScroll(int i, int i2, int i3, int i4) {
        CreateWishListActivity.lambda$onCreate$0(this.arg$1, i, i2, i3, i4);
    }
}
