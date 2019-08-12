package com.airbnb.android.lib.views;

import p032rx.functions.Action1;

final /* synthetic */ class JoinWishlistFragment$$Lambda$1 implements Action1 {
    private final JoinWishlistFragment arg$1;

    private JoinWishlistFragment$$Lambda$1(JoinWishlistFragment joinWishlistFragment) {
        this.arg$1 = joinWishlistFragment;
    }

    public static Action1 lambdaFactory$(JoinWishlistFragment joinWishlistFragment) {
        return new JoinWishlistFragment$$Lambda$1(joinWishlistFragment);
    }

    public void call(Object obj) {
        JoinWishlistFragment.lambda$new$0(this.arg$1, (Boolean) obj);
    }
}
