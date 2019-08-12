package com.airbnb.android.wishlists;

import com.airbnb.android.core.models.User;
import com.google.common.base.Function;

final /* synthetic */ class WishListDetailsEpoxyController$$Lambda$4 implements Function {
    private static final WishListDetailsEpoxyController$$Lambda$4 instance = new WishListDetailsEpoxyController$$Lambda$4();

    private WishListDetailsEpoxyController$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((User) obj).getPictureUrl();
    }
}
