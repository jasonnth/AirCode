package com.airbnb.android.booking.fragments.alipay;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlipayVerificationFragment$$Lambda$1 implements OnClickListener {
    private final AlipayVerificationFragment arg$1;

    private AlipayVerificationFragment$$Lambda$1(AlipayVerificationFragment alipayVerificationFragment) {
        this.arg$1 = alipayVerificationFragment;
    }

    public static OnClickListener lambdaFactory$(AlipayVerificationFragment alipayVerificationFragment) {
        return new AlipayVerificationFragment$$Lambda$1(alipayVerificationFragment);
    }

    public void onClick(View view) {
        this.arg$1.resendCode();
    }
}
