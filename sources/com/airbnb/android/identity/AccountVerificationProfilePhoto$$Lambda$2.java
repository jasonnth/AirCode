package com.airbnb.android.identity;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountVerificationProfilePhoto$$Lambda$2 implements OnClickListener {
    private final AccountVerificationProfilePhoto arg$1;

    private AccountVerificationProfilePhoto$$Lambda$2(AccountVerificationProfilePhoto accountVerificationProfilePhoto) {
        this.arg$1 = accountVerificationProfilePhoto;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationProfilePhoto accountVerificationProfilePhoto) {
        return new AccountVerificationProfilePhoto$$Lambda$2(accountVerificationProfilePhoto);
    }

    public void onClick(View view) {
        this.arg$1.onProfilePhotoClick();
    }
}
