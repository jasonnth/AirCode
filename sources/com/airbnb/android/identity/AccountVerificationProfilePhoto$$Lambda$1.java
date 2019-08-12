package com.airbnb.android.identity;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountVerificationProfilePhoto$$Lambda$1 implements OnClickListener {
    private final AccountVerificationProfilePhotoListener arg$1;

    private AccountVerificationProfilePhoto$$Lambda$1(AccountVerificationProfilePhotoListener accountVerificationProfilePhotoListener) {
        this.arg$1 = accountVerificationProfilePhotoListener;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationProfilePhotoListener accountVerificationProfilePhotoListener) {
        return new AccountVerificationProfilePhoto$$Lambda$1(accountVerificationProfilePhotoListener);
    }

    public void onClick(View view) {
        this.arg$1.onNext();
    }
}
