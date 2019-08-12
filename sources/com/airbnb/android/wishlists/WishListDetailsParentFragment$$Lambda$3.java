package com.airbnb.android.wishlists;

import com.airbnb.android.core.responses.WishListMembershipsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class WishListDetailsParentFragment$$Lambda$3 implements Action1 {
    private final WishListDetailsParentFragment arg$1;

    private WishListDetailsParentFragment$$Lambda$3(WishListDetailsParentFragment wishListDetailsParentFragment) {
        this.arg$1 = wishListDetailsParentFragment;
    }

    public static Action1 lambdaFactory$(WishListDetailsParentFragment wishListDetailsParentFragment) {
        return new WishListDetailsParentFragment$$Lambda$3(wishListDetailsParentFragment);
    }

    public void call(Object obj) {
        WishListDetailsParentFragment.lambda$new$2(this.arg$1, (WishListMembershipsResponse) obj);
    }
}
