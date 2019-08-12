package com.airbnb.android.login.oauth.strategies;

import p032rx.functions.Action1;

final /* synthetic */ class AlipayLoginStrategy$$Lambda$2 implements Action1 {
    private final AlipayLoginStrategy arg$1;

    private AlipayLoginStrategy$$Lambda$2(AlipayLoginStrategy alipayLoginStrategy) {
        this.arg$1 = alipayLoginStrategy;
    }

    public static Action1 lambdaFactory$(AlipayLoginStrategy alipayLoginStrategy) {
        return new AlipayLoginStrategy$$Lambda$2(alipayLoginStrategy);
    }

    public void call(Object obj) {
        this.arg$1.finishWithError();
    }
}
