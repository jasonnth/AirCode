package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.google.common.base.Function;

final /* synthetic */ class ListingRegistrationSubmissionAdapter$$Lambda$1 implements Function {
    private final ListingRegistrationSubmissionAdapter arg$1;

    private ListingRegistrationSubmissionAdapter$$Lambda$1(ListingRegistrationSubmissionAdapter listingRegistrationSubmissionAdapter) {
        this.arg$1 = listingRegistrationSubmissionAdapter;
    }

    public static Function lambdaFactory$(ListingRegistrationSubmissionAdapter listingRegistrationSubmissionAdapter) {
        return new ListingRegistrationSubmissionAdapter$$Lambda$1(listingRegistrationSubmissionAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildModelFromInputGroup((ListingRegistrationProcessInputGroup) obj);
    }
}
