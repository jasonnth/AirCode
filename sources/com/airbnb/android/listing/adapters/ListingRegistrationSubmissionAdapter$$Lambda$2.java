package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingRegistrationSubmissionData;
import com.google.common.base.Function;

final /* synthetic */ class ListingRegistrationSubmissionAdapter$$Lambda$2 implements Function {
    private final ListingRegistrationSubmissionAdapter arg$1;

    private ListingRegistrationSubmissionAdapter$$Lambda$2(ListingRegistrationSubmissionAdapter listingRegistrationSubmissionAdapter) {
        this.arg$1 = listingRegistrationSubmissionAdapter;
    }

    public static Function lambdaFactory$(ListingRegistrationSubmissionAdapter listingRegistrationSubmissionAdapter) {
        return new ListingRegistrationSubmissionAdapter$$Lambda$2(listingRegistrationSubmissionAdapter);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildSubmissionDataModel((ListingRegistrationSubmissionData) obj);
    }
}
