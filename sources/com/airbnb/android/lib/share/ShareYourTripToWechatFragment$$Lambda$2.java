package com.airbnb.android.lib.share;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ShareYourTripToWechatFragment$$Lambda$2 implements Action1 {
    private final ShareYourTripToWechatFragment arg$1;

    private ShareYourTripToWechatFragment$$Lambda$2(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        this.arg$1 = shareYourTripToWechatFragment;
    }

    public static Action1 lambdaFactory$(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        return new ShareYourTripToWechatFragment$$Lambda$2(shareYourTripToWechatFragment);
    }

    public void call(Object obj) {
        ShareYourTripToWechatFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
