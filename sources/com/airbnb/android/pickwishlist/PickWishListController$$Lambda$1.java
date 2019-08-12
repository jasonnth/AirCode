package com.airbnb.android.pickwishlist;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.WishList;

final /* synthetic */ class PickWishListController$$Lambda$1 implements OnClickListener {
    private final PickWishListController arg$1;
    private final WishList arg$2;

    private PickWishListController$$Lambda$1(PickWishListController pickWishListController, WishList wishList) {
        this.arg$1 = pickWishListController;
        this.arg$2 = wishList;
    }

    public static OnClickListener lambdaFactory$(PickWishListController pickWishListController, WishList wishList) {
        return new PickWishListController$$Lambda$1(pickWishListController, wishList);
    }

    public void onClick(View view) {
        PickWishListController.lambda$buildModels$0(this.arg$1, this.arg$2, view);
    }
}
