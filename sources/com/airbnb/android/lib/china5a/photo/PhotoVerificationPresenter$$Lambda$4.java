package com.airbnb.android.lib.china5a.photo;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class PhotoVerificationPresenter$$Lambda$4 implements Predicate {
    private static final PhotoVerificationPresenter$$Lambda$4 instance = new PhotoVerificationPresenter$$Lambda$4();

    private PhotoVerificationPresenter$$Lambda$4() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PhotoVerificationPresenter.lambda$null$2((AccountVerification) obj);
    }
}
