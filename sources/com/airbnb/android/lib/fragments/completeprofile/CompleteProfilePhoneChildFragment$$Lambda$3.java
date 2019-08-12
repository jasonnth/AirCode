package com.airbnb.android.lib.fragments.completeprofile;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

final /* synthetic */ class CompleteProfilePhoneChildFragment$$Lambda$3 implements OnKeyListener {
    private final CompleteProfilePhoneChildFragment arg$1;

    private CompleteProfilePhoneChildFragment$$Lambda$3(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        this.arg$1 = completeProfilePhoneChildFragment;
    }

    public static OnKeyListener lambdaFactory$(CompleteProfilePhoneChildFragment completeProfilePhoneChildFragment) {
        return new CompleteProfilePhoneChildFragment$$Lambda$3(completeProfilePhoneChildFragment);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return CompleteProfilePhoneChildFragment.lambda$setupPhoneNumberEditText$0(this.arg$1, view, i, keyEvent);
    }
}
