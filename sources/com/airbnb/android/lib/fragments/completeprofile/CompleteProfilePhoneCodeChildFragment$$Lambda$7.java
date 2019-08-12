package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class CompleteProfilePhoneCodeChildFragment$$Lambda$7 implements OnFocusChangeListener {
    private final CompleteProfilePhoneCodeChildFragment arg$1;

    private CompleteProfilePhoneCodeChildFragment$$Lambda$7(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        this.arg$1 = completeProfilePhoneCodeChildFragment;
    }

    public static OnFocusChangeListener lambdaFactory$(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        return new CompleteProfilePhoneCodeChildFragment$$Lambda$7(completeProfilePhoneCodeChildFragment);
    }

    public void onFocusChange(View view, boolean z) {
        CompleteProfilePhoneCodeChildFragment.lambda$setupCodeEditText$3(this.arg$1, view, z);
    }
}
