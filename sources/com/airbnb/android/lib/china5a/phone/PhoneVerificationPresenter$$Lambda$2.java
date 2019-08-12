package com.airbnb.android.lib.china5a.phone;

import com.airbnb.android.lib.china5a.VerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhoneVerificationPresenter$$Lambda$2 implements Action1 {
    private final PhoneVerificationPresenter arg$1;

    private PhoneVerificationPresenter$$Lambda$2(PhoneVerificationPresenter phoneVerificationPresenter) {
        this.arg$1 = phoneVerificationPresenter;
    }

    public static Action1 lambdaFactory$(PhoneVerificationPresenter phoneVerificationPresenter) {
        return new PhoneVerificationPresenter$$Lambda$2(phoneVerificationPresenter);
    }

    public void call(Object obj) {
        PhoneVerificationPresenter.lambda$start$1(this.arg$1, (VerificationResponse) obj);
    }
}
