package com.airbnb.android.lib.businesstravel;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class VerifyWorkEmailFragment$$Lambda$2 implements OnClickListener {
    private final VerifyWorkEmailFragment arg$1;

    private VerifyWorkEmailFragment$$Lambda$2(VerifyWorkEmailFragment verifyWorkEmailFragment) {
        this.arg$1 = verifyWorkEmailFragment;
    }

    public static OnClickListener lambdaFactory$(VerifyWorkEmailFragment verifyWorkEmailFragment) {
        return new VerifyWorkEmailFragment$$Lambda$2(verifyWorkEmailFragment);
    }

    public void onClick(View view) {
        VerifyWorkEmailFragment.lambda$setupResendEmailButton$1(this.arg$1, view);
    }
}
