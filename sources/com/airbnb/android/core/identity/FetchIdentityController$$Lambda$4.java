package com.airbnb.android.core.identity;

import com.airbnb.android.core.models.AccountVerification;
import com.google.common.base.Predicate;

final /* synthetic */ class FetchIdentityController$$Lambda$4 implements Predicate {
    private final FetchIdentityController arg$1;

    private FetchIdentityController$$Lambda$4(FetchIdentityController fetchIdentityController) {
        this.arg$1 = fetchIdentityController;
    }

    public static Predicate lambdaFactory$(FetchIdentityController fetchIdentityController) {
        return new FetchIdentityController$$Lambda$4(fetchIdentityController);
    }

    public boolean apply(Object obj) {
        return this.arg$1.filterOfflineIdAndSelfie((AccountVerification) obj);
    }
}
