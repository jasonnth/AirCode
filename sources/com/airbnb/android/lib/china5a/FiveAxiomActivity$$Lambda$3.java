package com.airbnb.android.lib.china5a;

import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomActivity$$Lambda$3 implements Action1 {
    private final FiveAxiomActivity arg$1;

    private FiveAxiomActivity$$Lambda$3(FiveAxiomActivity fiveAxiomActivity) {
        this.arg$1 = fiveAxiomActivity;
    }

    public static Action1 lambdaFactory$(FiveAxiomActivity fiveAxiomActivity) {
        return new FiveAxiomActivity$$Lambda$3(fiveAxiomActivity);
    }

    public void call(Object obj) {
        this.arg$1.onError((Throwable) obj);
    }
}
