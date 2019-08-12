package com.airbnb.android.core;

import com.airbnb.android.core.requests.DeleteOauthTokenRequest;
import com.airbnb.android.core.utils.NetworkUtil;

final /* synthetic */ class AirbnbApi$$Lambda$2 implements Runnable {
    private final DeleteOauthTokenRequest arg$1;

    private AirbnbApi$$Lambda$2(DeleteOauthTokenRequest deleteOauthTokenRequest) {
        this.arg$1 = deleteOauthTokenRequest;
    }

    public static Runnable lambdaFactory$(DeleteOauthTokenRequest deleteOauthTokenRequest) {
        return new AirbnbApi$$Lambda$2(deleteOauthTokenRequest);
    }

    public void run() {
        this.arg$1.execute(NetworkUtil.singleFireExecutor());
    }
}
