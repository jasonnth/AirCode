package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CompleteProfilePhotoFragment$$Lambda$3 implements OnClickListener {
    private final CompleteProfilePhotoFragment arg$1;

    private CompleteProfilePhotoFragment$$Lambda$3(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        this.arg$1 = completeProfilePhotoFragment;
    }

    public static OnClickListener lambdaFactory$(CompleteProfilePhotoFragment completeProfilePhotoFragment) {
        return new CompleteProfilePhotoFragment$$Lambda$3(completeProfilePhotoFragment);
    }

    public void onClick(View view) {
        CompleteProfilePhotoFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
