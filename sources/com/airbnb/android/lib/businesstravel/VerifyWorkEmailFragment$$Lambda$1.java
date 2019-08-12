package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class VerifyWorkEmailFragment$$Lambda$1 implements OnClickListener {
    private final VerifyWorkEmailFragment arg$1;

    private VerifyWorkEmailFragment$$Lambda$1(VerifyWorkEmailFragment verifyWorkEmailFragment) {
        this.arg$1 = verifyWorkEmailFragment;
    }

    public static OnClickListener lambdaFactory$(VerifyWorkEmailFragment verifyWorkEmailFragment) {
        return new VerifyWorkEmailFragment$$Lambda$1(verifyWorkEmailFragment);
    }

    public void onClick(View view) {
        this.arg$1.finishWorkEmailVerification();
    }
}
