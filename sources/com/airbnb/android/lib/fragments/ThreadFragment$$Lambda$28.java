package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.responses.CohostedListingsResponse;
import com.google.common.base.Function;

final /* synthetic */ class ThreadFragment$$Lambda$28 implements Function {
    private static final ThreadFragment$$Lambda$28 instance = new ThreadFragment$$Lambda$28();

    private ThreadFragment$$Lambda$28() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((CohostedListingsResponse) obj).listings;
    }
}
