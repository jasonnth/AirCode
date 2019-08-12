package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AlipayPhoneFragment$$Lambda$2 implements Action1 {
    private final AlipayPhoneFragment arg$1;

    private AlipayPhoneFragment$$Lambda$2(AlipayPhoneFragment alipayPhoneFragment) {
        this.arg$1 = alipayPhoneFragment;
    }

    public static Action1 lambdaFactory$(AlipayPhoneFragment alipayPhoneFragment) {
        return new AlipayPhoneFragment$$Lambda$2(alipayPhoneFragment);
    }

    public void call(Object obj) {
        AlipayPhoneFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
