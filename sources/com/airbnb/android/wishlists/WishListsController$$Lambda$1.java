package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WishListsController$$Lambda$1 implements OnClickListener {
    private static final WishListsController$$Lambda$1 instance = new WishListsController$$Lambda$1();

    private WishListsController$$Lambda$1() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        WishListsController.lambda$buildModels$0(view);
    }
}
