package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AlipayPhoneFragment$$Lambda$1 implements Action1 {
    private final AlipayPhoneFragment arg$1;

    private AlipayPhoneFragment$$Lambda$1(AlipayPhoneFragment alipayPhoneFragment) {
        this.arg$1 = alipayPhoneFragment;
    }

    public static Action1 lambdaFactory$(AlipayPhoneFragment alipayPhoneFragment) {
        return new AlipayPhoneFragment$$Lambda$1(alipayPhoneFragment);
    }

    public void call(Object obj) {
        AlipayPhoneFragment.lambda$new$0(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
