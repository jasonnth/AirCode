package com.airbnb.android.lib.china5a;

import com.airbnb.android.core.interfaces.OnBackListener;

final /* synthetic */ class BaseVerificationFragment$$Lambda$1 implements OnBackListener {
    private final BaseVerificationFragment arg$1;

    private BaseVerificationFragment$$Lambda$1(BaseVerificationFragment baseVerificationFragment) {
        this.arg$1 = baseVerificationFragment;
    }

    public static OnBackListener lambdaFactory$(BaseVerificationFragment baseVerificationFragment) {
        return new BaseVerificationFragment$$Lambda$1(baseVerificationFragment);
    }

    public boolean onBackPressed() {
        return this.arg$1.onBackPressed();
    }
}
