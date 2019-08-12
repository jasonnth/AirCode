package com.airbnb.android.lib.enums;

import com.airbnb.android.core.enums.VerificationFlow;
import com.google.common.base.Predicate;

final /* synthetic */ class VerificationFlowToLayoutId$$Lambda$1 implements Predicate {
    private final VerificationFlow arg$1;

    private VerificationFlowToLayoutId$$Lambda$1(VerificationFlow verificationFlow) {
        this.arg$1 = verificationFlow;
    }

    public static Predicate lambdaFactory$(VerificationFlow verificationFlow) {
        return new VerificationFlowToLayoutId$$Lambda$1(verificationFlow);
    }

    public boolean apply(Object obj) {
        return ((VerificationFlowToLayoutId) obj).verificationFlow.equals(this.arg$1);
    }
}
