package com.airbnb.android.login.oauth.strategies;

import com.airbnb.android.core.responses.AlipayAuthCodeParamsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AlipayLoginStrategy$$Lambda$1 implements Action1 {
    private final AlipayLoginStrategy arg$1;

    private AlipayLoginStrategy$$Lambda$1(AlipayLoginStrategy alipayLoginStrategy) {
        this.arg$1 = alipayLoginStrategy;
    }

    public static Action1 lambdaFactory$(AlipayLoginStrategy alipayLoginStrategy) {
        return new AlipayLoginStrategy$$Lambda$1(alipayLoginStrategy);
    }

    public void call(Object obj) {
        this.arg$1.onAlipayResponse((AlipayAuthCodeParamsResponse) obj);
    }
}
