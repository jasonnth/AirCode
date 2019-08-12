package com.airbnb.android.core.wishlists;

import com.squareup.otto.Bus;

final /* synthetic */ class WishListManager$$Lambda$2 implements Runnable {
    private final WishListManager arg$1;
    private final Bus arg$2;

    private WishListManager$$Lambda$2(WishListManager wishListManager, Bus bus) {
        this.arg$1 = wishListManager;
        this.arg$2 = bus;
    }

    public static Runnable lambdaFactory$(WishListManager wishListManager, Bus bus) {
        return new WishListManager$$Lambda$2(wishListManager, bus);
    }

    public void run() {
        this.arg$2.register(this.arg$1);
    }
}
