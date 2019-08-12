package com.airbnb.android.lib.share;

import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

final /* synthetic */ class ShareYourTripToWechatFragment$$Lambda$6 implements OnClickListener {
    private final ShareYourTripToWechatFragment arg$1;
    private final Uri arg$2;
    private final AirImageView arg$3;

    private ShareYourTripToWechatFragment$$Lambda$6(ShareYourTripToWechatFragment shareYourTripToWechatFragment, Uri uri, AirImageView airImageView) {
        this.arg$1 = shareYourTripToWechatFragment;
        this.arg$2 = uri;
        this.arg$3 = airImageView;
    }

    public static OnClickListener lambdaFactory$(ShareYourTripToWechatFragment shareYourTripToWechatFragment, Uri uri, AirImageView airImageView) {
        return new ShareYourTripToWechatFragment$$Lambda$6(shareYourTripToWechatFragment, uri, airImageView);
    }

    public void onClick(View view) {
        ShareYourTripToWechatFragment.lambda$buildDoubleImageRow$6(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
