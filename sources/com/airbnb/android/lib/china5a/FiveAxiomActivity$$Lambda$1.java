package com.airbnb.android.lib.china5a;

import p032rx.functions.Action0;

final /* synthetic */ class FiveAxiomActivity$$Lambda$1 implements Action0 {
    private final FiveAxiomActivity arg$1;

    private FiveAxiomActivity$$Lambda$1(FiveAxiomActivity fiveAxiomActivity) {
        this.arg$1 = fiveAxiomActivity;
    }

    public static Action0 lambdaFactory$(FiveAxiomActivity fiveAxiomActivity) {
        return new FiveAxiomActivity$$Lambda$1(fiveAxiomActivity);
    }

    public void call() {
        this.arg$1.onSubscribe();
    }
}
