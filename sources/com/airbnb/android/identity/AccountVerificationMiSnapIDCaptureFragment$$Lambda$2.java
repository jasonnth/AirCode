package com.airbnb.android.identity;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.misnap.MiSnapIdentityCaptureActivity;

final /* synthetic */ class AccountVerificationMiSnapIDCaptureFragment$$Lambda$2 implements OnClickListener {
    private final AccountVerificationMiSnapIDCaptureFragment arg$1;
    private final String arg$2;

    private AccountVerificationMiSnapIDCaptureFragment$$Lambda$2(AccountVerificationMiSnapIDCaptureFragment accountVerificationMiSnapIDCaptureFragment, String str) {
        this.arg$1 = accountVerificationMiSnapIDCaptureFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(AccountVerificationMiSnapIDCaptureFragment accountVerificationMiSnapIDCaptureFragment, String str) {
        return new AccountVerificationMiSnapIDCaptureFragment$$Lambda$2(accountVerificationMiSnapIDCaptureFragment, str);
    }

    public void onClick(View view) {
        this.arg$1.startActivityForResult(MiSnapIdentityCaptureActivity.createIntentForBackPhotoOfDocumentType(this.arg$1.getActivity(), this.arg$2), 3);
    }
}