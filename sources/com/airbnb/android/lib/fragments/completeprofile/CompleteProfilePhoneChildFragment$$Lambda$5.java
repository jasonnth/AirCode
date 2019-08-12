package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CompleteProfilePhoneChildFragment$$Lambda$5 implements OnClickListener {
    private final CompleteProfilePhoneChildFragment arg$1;

    private CompleteProfilePhoneChildFragment$$Lambda$5(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        this.arg$1 = completeProfilePhoneChildFragment;
    }

    public static OnClickListener lambdaFactory$(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        return new CompleteProfilePhoneChildFragment$$Lambda$5(completeProfilePhoneChildFragment);
    }

    public void onClick(View view) {
        CompleteProfilePhoneChildFragment.lambda$setupSendButton$2(this.arg$1, view);
    }
}
