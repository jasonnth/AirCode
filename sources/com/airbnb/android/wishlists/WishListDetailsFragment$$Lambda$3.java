package com.airbnb.android.wishlists;

import android.support.p000v4.widget.SwipeRefreshLayout.OnRefreshListener;

final /* synthetic */ class WishListDetailsFragment$$Lambda$3 implements OnRefreshListener {
    private final WishListDetailsFragment arg$1;

    private WishListDetailsFragment$$Lambda$3(WishListDetailsFragment wishListDetailsFragment) {
        this.arg$1 = wishListDetailsFragment;
    }

    public static OnRefreshListener lambdaFactory$(WishListDetailsFragment wishListDetailsFragment) {
        return new WishListDetailsFragment$$Lambda$3(wishListDetailsFragment);
    }

    public void onRefresh() {
        WishListDetailsFragment.lambda$onActivityCreated$0(this.arg$1);
    }
}
