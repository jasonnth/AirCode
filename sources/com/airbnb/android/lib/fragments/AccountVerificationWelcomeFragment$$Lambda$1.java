package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.OldAccountVerificationActivity;

final /* synthetic */ class AccountVerificationWelcomeFragment$$Lambda$1 implements OnClickListener {
    private final AccountVerificationWelcomeFragment arg$1;

    private AccountVerificationWelcomeFragment$$Lambda$1(AccountVerificationWelcomeFragment accountVerificationWelcomeFragment) {
        this.arg$1 = accountVerificationWelcomeFragment;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationWelcomeFragment accountVerificationWelcomeFragment) {
        return new AccountVerificationWelcomeFragment$$Lambda$1(accountVerificationWelcomeFragment);
    }

    public void onClick(View view) {
        ((OldAccountVerificationActivity) this.arg$1.getActivity()).onBeginClick();
    }
}
