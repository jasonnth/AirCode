package com.airbnb.android.lib.share;

import com.airbnb.android.utils.ConcurrentUtil;

final /* synthetic */ class ShareYourTripToWechatFragment$$Lambda$3 implements Runnable {
    private final ShareYourTripToWechatFragment arg$1;

    private ShareYourTripToWechatFragment$$Lambda$3(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        this.arg$1 = shareYourTripToWechatFragment;
    }

    public static Runnable lambdaFactory$(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        return new ShareYourTripToWechatFragment$$Lambda$3(shareYourTripToWechatFragment);
    }

    public void run() {
        ConcurrentUtil.deferParallel(ShareYourTripToWechatFragment$$Lambda$8.lambdaFactory$(this.arg$1));
    }
}
