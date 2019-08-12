package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AlipayNationalIdFragment$$Lambda$1 implements Action1 {
    private final AlipayNationalIdFragment arg$1;

    private AlipayNationalIdFragment$$Lambda$1(AlipayNationalIdFragment alipayNationalIdFragment) {
        this.arg$1 = alipayNationalIdFragment;
    }

    public static Action1 lambdaFactory$(AlipayNationalIdFragment alipayNationalIdFragment) {
        return new AlipayNationalIdFragment$$Lambda$1(alipayNationalIdFragment);
    }

    public void call(Object obj) {
        AlipayNationalIdFragment.lambda$new$0(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
