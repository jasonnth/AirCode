package com.airbnb.android.booking.fragments.alipayv2;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AlipayV2RetryFragment$$Lambda$2 implements OnClickListener {
    private final AlipayV2RetryFragment arg$1;

    private AlipayV2RetryFragment$$Lambda$2(AlipayV2RetryFragment alipayV2RetryFragment) {
        this.arg$1 = alipayV2RetryFragment;
    }

    public static OnClickListener lambdaFactory$(AlipayV2RetryFragment alipayV2RetryFragment) {
        return new AlipayV2RetryFragment$$Lambda$2(alipayV2RetryFragment);
    }

    public void onClick(View view) {
        AlipayV2RetryFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
