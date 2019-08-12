package com.airbnb.android.lib.fragments.completeprofile;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CompleteProfilePhotoSelectChildFragment$$Lambda$2 implements OnClickListener {
    private final CompleteProfilePhotoSelectChildFragment arg$1;

    private CompleteProfilePhotoSelectChildFragment$$Lambda$2(CompleteProfilePhotoSelectChildFragment completeProfilePhotoSelectChildFragment) {
        this.arg$1 = completeProfilePhotoSelectChildFragment;
    }

    public static OnClickListener lambdaFactory$(CompleteProfilePhotoSelectChildFragment completeProfilePhotoSelectChildFragment) {
        return new CompleteProfilePhotoSelectChildFragment$$Lambda$2(completeProfilePhotoSelectChildFragment);
    }

    public void onClick(View view) {
        CompleteProfilePhotoSelectChildFragment.lambda$onCreateView$1(this.arg$1, view);
    }
}
