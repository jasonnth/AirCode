package com.airbnb.android.react;

import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class AirbnbNetworkingModule$$Lambda$2 implements Action1 {
    private static final AirbnbNetworkingModule$$Lambda$2 instance = new AirbnbNetworkingModule$$Lambda$2();

    private AirbnbNetworkingModule$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        NetworkUtil.startLoginActivityIfSessionExpired((Throwable) obj);
    }
}
