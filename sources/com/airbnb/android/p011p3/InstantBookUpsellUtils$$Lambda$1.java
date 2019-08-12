package com.airbnb.android.p011p3;

import com.airbnb.android.core.models.SimilarListing;
import com.google.common.base.Predicate;

/* renamed from: com.airbnb.android.p3.InstantBookUpsellUtils$$Lambda$1 */
final /* synthetic */ class InstantBookUpsellUtils$$Lambda$1 implements Predicate {
    private static final InstantBookUpsellUtils$$Lambda$1 instance = new InstantBookUpsellUtils$$Lambda$1();

    private InstantBookUpsellUtils$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return InstantBookUpsellUtils.lambda$getIBSimilarListingCount$0((SimilarListing) obj);
    }
}
