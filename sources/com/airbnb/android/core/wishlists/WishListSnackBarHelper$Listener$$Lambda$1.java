package com.airbnb.android.core.wishlists;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WishListSnackBarHelper$Listener$$Lambda$1 implements OnClickListener {
    private final Listener arg$1;
    private final boolean arg$2;
    private final WishListChangeInfo arg$3;

    private WishListSnackBarHelper$Listener$$Lambda$1(Listener listener, boolean z, WishListChangeInfo wishListChangeInfo) {
        this.arg$1 = listener;
        this.arg$2 = z;
        this.arg$3 = wishListChangeInfo;
    }

    public static OnClickListener lambdaFactory$(Listener listener, boolean z, WishListChangeInfo wishListChangeInfo) {
        return new WishListSnackBarHelper$Listener$$Lambda$1(listener, z, wishListChangeInfo);
    }

    public void onClick(View view) {
        Listener.lambda$onWishListsChanged$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
