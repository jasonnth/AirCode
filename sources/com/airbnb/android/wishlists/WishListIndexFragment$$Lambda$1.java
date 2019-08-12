package com.airbnb.android.wishlists;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class WishListIndexFragment$$Lambda$1 implements OnRefreshListener {
    private final WishListIndexFragment arg$1;

    private WishListIndexFragment$$Lambda$1(WishListIndexFragment wishListIndexFragment) {
        this.arg$1 = wishListIndexFragment;
    }

    public static OnRefreshListener lambdaFactory$(WishListIndexFragment wishListIndexFragment) {
        return new WishListIndexFragment$$Lambda$1(wishListIndexFragment);
    }

    public void onRefresh() {
        WishListIndexFragment.lambda$initializeList$0(this.arg$1);
    }
}
