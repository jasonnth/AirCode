package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class RemoveCohostFragment$$Lambda$2 implements Action1 {
    private final RemoveCohostFragment arg$1;

    private RemoveCohostFragment$$Lambda$2(RemoveCohostFragment removeCohostFragment) {
        this.arg$1 = removeCohostFragment;
    }

    public static Action1 lambdaFactory$(RemoveCohostFragment removeCohostFragment) {
        return new RemoveCohostFragment$$Lambda$2(removeCohostFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
