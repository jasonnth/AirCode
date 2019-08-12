package com.airbnb.android.wishlists;

import com.airbnb.android.core.models.Photo;
import com.google.common.base.Function;

final /* synthetic */ class WishListsController$$Lambda$2 implements Function {
    private static final WishListsController$$Lambda$2 instance = new WishListsController$$Lambda$2();

    private WishListsController$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((Photo) obj).getXLargeUrl();
    }
}
