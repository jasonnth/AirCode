package com.airbnb.android.lib.china5a;

import com.airbnb.android.core.identity.AccountVerificationStep;
import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomActivity$$Lambda$2 implements Action1 {
    private final FiveAxiomActivity arg$1;

    private FiveAxiomActivity$$Lambda$2(FiveAxiomActivity fiveAxiomActivity) {
        this.arg$1 = fiveAxiomActivity;
    }

    public static Action1 lambdaFactory$(FiveAxiomActivity fiveAxiomActivity) {
        return new FiveAxiomActivity$$Lambda$2(fiveAxiomActivity);
    }

    public void call(Object obj) {
        this.arg$1.onNext((AccountVerificationStep) obj);
    }
}
