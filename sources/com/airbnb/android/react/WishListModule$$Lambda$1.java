package com.airbnb.android.react;

import com.airbnb.android.core.wishlists.WishListableData;

final /* synthetic */ class WishListModule$$Lambda$1 implements Runnable {
    private final WishListModule arg$1;
    private final WishListableData arg$2;

    private WishListModule$$Lambda$1(WishListModule wishListModule, WishListableData wishListableData) {
        this.arg$1 = wishListModule;
        this.arg$2 = wishListableData;
    }

    public static Runnable lambdaFactory$(WishListModule wishListModule, WishListableData wishListableData) {
        return new WishListModule$$Lambda$1(wishListModule, wishListableData);
    }

    public void run() {
        WishListModule.lambda$toggleItem$0(this.arg$1, this.arg$2);
    }
}
