package com.airbnb.p027n2.primitives;

/* renamed from: com.airbnb.n2.primitives.WishListIconView$$Lambda$1 */
final /* synthetic */ class WishListIconView$$Lambda$1 implements Runnable {
    private final WishListIconView arg$1;

    private WishListIconView$$Lambda$1(WishListIconView wishListIconView) {
        this.arg$1 = wishListIconView;
    }

    public static Runnable lambdaFactory$(WishListIconView wishListIconView) {
        return new WishListIconView$$Lambda$1(wishListIconView);
    }

    public void run() {
        this.arg$1.playAnimation();
    }
}
