package com.airbnb.android.lib.fragments.completeprofile;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

final /* synthetic */ class CompleteProfilePhoneCodeChildFragment$$Lambda$6 implements OnKeyListener {
    private final CompleteProfilePhoneCodeChildFragment arg$1;

    private CompleteProfilePhoneCodeChildFragment$$Lambda$6(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        this.arg$1 = completeProfilePhoneCodeChildFragment;
    }

    public static OnKeyListener lambdaFactory$(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        return new CompleteProfilePhoneCodeChildFragment$$Lambda$6(completeProfilePhoneCodeChildFragment);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return CompleteProfilePhoneCodeChildFragment.lambda$setupCodeEditText$2(this.arg$1, view, i, keyEvent);
    }
}
