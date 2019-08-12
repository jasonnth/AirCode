package com.airbnb.android.lib.china5a.fragments;

import com.airbnb.p027n2.collections.ProfilePhotoSheet.Delegate;

final /* synthetic */ class PhotoVerificationFragment$$Lambda$1 implements Delegate {
    private final PhotoVerificationFragment arg$1;

    private PhotoVerificationFragment$$Lambda$1(PhotoVerificationFragment photoVerificationFragment) {
        this.arg$1 = photoVerificationFragment;
    }

    public static Delegate lambdaFactory$(PhotoVerificationFragment photoVerificationFragment) {
        return new PhotoVerificationFragment$$Lambda$1(photoVerificationFragment);
    }

    public void onProfilePhotoClick() {
        PhotoVerificationFragment.lambda$onCreateView$0(this.arg$1);
    }
}
