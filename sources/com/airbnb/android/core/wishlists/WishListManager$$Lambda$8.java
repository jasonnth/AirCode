package com.airbnb.android.core.wishlists;

final /* synthetic */ class WishListManager$$Lambda$8 implements Runnable {
    private final WishListManager arg$1;
    private final boolean arg$2;

    private WishListManager$$Lambda$8(WishListManager wishListManager, boolean z) {
        this.arg$1 = wishListManager;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(WishListManager wishListManager, boolean z) {
        return new WishListManager$$Lambda$8(wishListManager, z);
    }

    public void run() {
        this.arg$1.refreshWishLists(this.arg$2);
    }
}
