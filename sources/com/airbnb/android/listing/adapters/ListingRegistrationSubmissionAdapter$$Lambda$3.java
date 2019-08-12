package com.airbnb.android.listing.adapters;

import com.google.common.base.Predicate;

final /* synthetic */ class ListingRegistrationSubmissionAdapter$$Lambda$3 implements Predicate {
    private static final ListingRegistrationSubmissionAdapter$$Lambda$3 instance = new ListingRegistrationSubmissionAdapter$$Lambda$3();

    private ListingRegistrationSubmissionAdapter$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ListingRegistrationSubmissionAdapter.lambda$getDisplayAddress$0((String) obj);
    }
}
