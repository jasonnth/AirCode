package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CompleteProfilePhoneCodeChildFragment$$Lambda$5 implements OnClickListener {
    private final CompleteProfilePhoneCodeChildFragment arg$1;

    private CompleteProfilePhoneCodeChildFragment$$Lambda$5(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        this.arg$1 = completeProfilePhoneCodeChildFragment;
    }

    public static OnClickListener lambdaFactory$(CompleteProfilePhoneCodeChildFragment completeProfilePhoneCodeChildFragment) {
        return new CompleteProfilePhoneCodeChildFragment$$Lambda$5(completeProfilePhoneCodeChildFragment);
    }

    public void onClick(View view) {
        CompleteProfilePhoneCodeChildFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}