package com.airbnb.android.lib.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ThreadFragment$$Lambda$14 implements Action1 {
    private static final ThreadFragment$$Lambda$14 instance = new ThreadFragment$$Lambda$14();

    private ThreadFragment$$Lambda$14() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ThreadFragment.lambda$new$14((AirRequestNetworkException) obj);
    }
}
