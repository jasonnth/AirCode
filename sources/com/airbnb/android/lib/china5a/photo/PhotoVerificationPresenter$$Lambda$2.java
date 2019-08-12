package com.airbnb.android.lib.china5a.photo;

import com.airbnb.android.lib.china5a.VerificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PhotoVerificationPresenter$$Lambda$2 implements Action1 {
    private final PhotoVerificationPresenter arg$1;

    private PhotoVerificationPresenter$$Lambda$2(PhotoVerificationPresenter photoVerificationPresenter) {
        this.arg$1 = photoVerificationPresenter;
    }

    public static Action1 lambdaFactory$(PhotoVerificationPresenter photoVerificationPresenter) {
        return new PhotoVerificationPresenter$$Lambda$2(photoVerificationPresenter);
    }

    public void call(Object obj) {
        PhotoVerificationPresenter.lambda$start$1(this.arg$1, (VerificationResponse) obj);
    }
}
