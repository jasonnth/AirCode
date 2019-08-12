package com.airbnb.android.core.modules;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class NetworkModule$$Lambda$6 implements Action1 {
    private static final NetworkModule$$Lambda$6 instance = new NetworkModule$$Lambda$6();

    private NetworkModule$$Lambda$6() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        NetworkUtil.startLoginActivityIfSessionExpired((Throwable) obj);
    }
}
