package com.airbnb.android.lib.share;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ShareYourTripToWechatFragment$$Lambda$4 implements OnClickListener {
    private final ShareYourTripToWechatFragment arg$1;

    private ShareYourTripToWechatFragment$$Lambda$4(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        this.arg$1 = shareYourTripToWechatFragment;
    }

    public static OnClickListener lambdaFactory$(ShareYourTripToWechatFragment shareYourTripToWechatFragment) {
        return new ShareYourTripToWechatFragment$$Lambda$4(shareYourTripToWechatFragment);
    }

    public void onClick(View view) {
        this.arg$1.maxPhotoReachedSnackBar.dismiss();
    }
}
