package com.airbnb.android.lib.china5a;

import p032rx.functions.Action0;

final /* synthetic */ class FiveAxiomActivity$$Lambda$4 implements Action0 {
    private final FiveAxiomActivity arg$1;

    private FiveAxiomActivity$$Lambda$4(FiveAxiomActivity fiveAxiomActivity) {
        this.arg$1 = fiveAxiomActivity;
    }

    public static Action0 lambdaFactory$(FiveAxiomActivity fiveAxiomActivity) {
        return new FiveAxiomActivity$$Lambda$4(fiveAxiomActivity);
    }

    public void call() {
        this.arg$1.onComplete();
    }
}
