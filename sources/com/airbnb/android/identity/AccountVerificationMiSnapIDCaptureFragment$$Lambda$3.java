package com.airbnb.android.identity;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AccountVerificationMiSnapIDCaptureFragment$$Lambda$3 implements OnClickListener {
    private final AccountVerificationMiSnapIDCaptureFragment arg$1;

    private AccountVerificationMiSnapIDCaptureFragment$$Lambda$3(AccountVerificationMiSnapIDCaptureFragment accountVerificationMiSnapIDCaptureFragment) {
        this.arg$1 = accountVerificationMiSnapIDCaptureFragment;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationMiSnapIDCaptureFragment accountVerificationMiSnapIDCaptureFragment) {
        return new AccountVerificationMiSnapIDCaptureFragment$$Lambda$3(accountVerificationMiSnapIDCaptureFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.onLicensePhotosCaptured(this.arg$1.frontPhotoPath, this.arg$1.backPhotoPath, this.arg$1.capturedBarcode);
    }
}
