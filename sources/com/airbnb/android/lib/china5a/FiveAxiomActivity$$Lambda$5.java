package com.airbnb.android.lib.china5a;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FiveAxiomActivity$$Lambda$5 implements OnClickListener {
    private final FiveAxiomActivity arg$1;

    private FiveAxiomActivity$$Lambda$5(FiveAxiomActivity fiveAxiomActivity) {
        this.arg$1 = fiveAxiomActivity;
    }

    public static OnClickListener lambdaFactory$(FiveAxiomActivity fiveAxiomActivity) {
        return new FiveAxiomActivity$$Lambda$5(fiveAxiomActivity);
    }

    public void onClick(View view) {
        this.arg$1.onBackPressed();
    }
}
