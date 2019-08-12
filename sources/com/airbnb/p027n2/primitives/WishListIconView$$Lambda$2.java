package com.airbnb.p027n2.primitives;

import com.airbnb.p027n2.primitives.wish_lists.WishListHeartInterface.OnWishListedStatusSetListener;

/* renamed from: com.airbnb.n2.primitives.WishListIconView$$Lambda$2 */
final /* synthetic */ class WishListIconView$$Lambda$2 implements OnWishListedStatusSetListener {
    private final WishListIconView arg$1;

    private WishListIconView$$Lambda$2(WishListIconView wishListIconView) {
        this.arg$1 = wishListIconView;
    }

    public static OnWishListedStatusSetListener lambdaFactory$(WishListIconView wishListIconView) {
        return new WishListIconView$$Lambda$2(wishListIconView);
    }

    public void onWishListedStatusSet(boolean z) {
        this.arg$1.setIsWishListed(z);
    }
}
