package com.airbnb.android.lib.identity.psb;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class IdentificationNameFragment$$Lambda$2 implements OnFocusChangeListener {
    private final IdentificationNameFragment arg$1;

    private IdentificationNameFragment$$Lambda$2(IdentificationNameFragment identificationNameFragment) {
        this.arg$1 = identificationNameFragment;
    }

    public static OnFocusChangeListener lambdaFactory$(IdentificationNameFragment identificationNameFragment) {
        return new IdentificationNameFragment$$Lambda$2(identificationNameFragment);
    }

    public void onFocusChange(View view, boolean z) {
        IdentificationNameFragment.lambda$onCreateView$1(this.arg$1, view, z);
    }
}
