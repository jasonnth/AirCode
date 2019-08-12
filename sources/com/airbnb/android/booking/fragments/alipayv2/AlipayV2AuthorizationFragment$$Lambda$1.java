package com.airbnb.android.booking.fragments.alipayv2;

import p032rx.functions.Action1;

final /* synthetic */ class AlipayV2AuthorizationFragment$$Lambda$1 implements Action1 {
    private final AlipayV2AuthorizationFragment arg$1;

    private AlipayV2AuthorizationFragment$$Lambda$1(AlipayV2AuthorizationFragment alipayV2AuthorizationFragment) {
        this.arg$1 = alipayV2AuthorizationFragment;
    }

    public static Action1 lambdaFactory$(AlipayV2AuthorizationFragment alipayV2AuthorizationFragment) {
        return new AlipayV2AuthorizationFragment$$Lambda$1(alipayV2AuthorizationFragment);
    }

    public void call(Object obj) {
        AlipayV2AuthorizationFragment.lambda$startPolling$0(this.arg$1, (Long) obj);
    }
}
