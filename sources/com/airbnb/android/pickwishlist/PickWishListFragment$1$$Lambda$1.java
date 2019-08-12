package com.airbnb.android.pickwishlist;

final /* synthetic */ class PickWishListFragment$1$$Lambda$1 implements Runnable {
    private final C76121 arg$1;

    private PickWishListFragment$1$$Lambda$1(C76121 r1) {
        this.arg$1 = r1;
    }

    public static Runnable lambdaFactory$(C76121 r1) {
        return new PickWishListFragment$1$$Lambda$1(r1);
    }

    public void run() {
        C76121.lambda$onWishListSelected$0(this.arg$1);
    }
}
