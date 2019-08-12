package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishList;

final /* synthetic */ class WishListsController$$Lambda$3 implements OnClickListener {
    private final WishListsController arg$1;
    private final WishList arg$2;

    private WishListsController$$Lambda$3(WishListsController wishListsController, WishList wishList) {
        this.arg$1 = wishListsController;
        this.arg$2 = wishList;
    }

    public static OnClickListener lambdaFactory$(WishListsController wishListsController, WishList wishList) {
        return new WishListsController$$Lambda$3(wishListsController, wishList);
    }

    public void onClick(View view) {
        this.arg$1.listener.onWishListSelected(this.arg$2);
    }
}
