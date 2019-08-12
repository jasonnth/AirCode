package com.airbnb.android.lib.views;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class JoinWishlistFragment$$Lambda$2 implements Action1 {
    private final JoinWishlistFragment arg$1;

    private JoinWishlistFragment$$Lambda$2(JoinWishlistFragment joinWishlistFragment) {
        this.arg$1 = joinWishlistFragment;
    }

    public static Action1 lambdaFactory$(JoinWishlistFragment joinWishlistFragment) {
        return new JoinWishlistFragment$$Lambda$2(joinWishlistFragment);
    }

    public void call(Object obj) {
        NetworkUtil.toastNetworkError(this.arg$1.getContext(), (NetworkException) (AirRequestNetworkException) obj);
    }
}
