package com.airbnb.android.lib.share;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ShareYourTripToWechatFragment$$Lambda$1 implements Action1 {
    private final ShareYourTripToWechatFragment arg$1;

    private ShareYourTripToWechatFragment$$Lambda$1(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        this.arg$1 = shareYourTripToWechatFragment;
    }

    public static Action1 lambdaFactory$(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        return new ShareYourTripToWechatFragment$$Lambda$1(shareYourTripToWechatFragment);
    }

    public void call(Object obj) {
        ShareYourTripToWechatFragment.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
