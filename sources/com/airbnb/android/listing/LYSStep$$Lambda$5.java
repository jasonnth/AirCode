package com.airbnb.android.listing;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class LYSStep$$Lambda$5 implements Predicate {
    private final LYSStep arg$1;
    private final Listing arg$2;
    private final List arg$3;
    private final boolean arg$4;
    private final boolean arg$5;
    private final ListingRegistrationProcess arg$6;

    private LYSStep$$Lambda$5(LYSStep lYSStep, Listing listing, List list, boolean z, boolean z2, ListingRegistrationProcess listingRegistrationProcess) {
        this.arg$1 = lYSStep;
        this.arg$2 = listing;
        this.arg$3 = list;
        this.arg$4 = z;
        this.arg$5 = z2;
        this.arg$6 = listingRegistrationProcess;
    }

    public static Predicate lambdaFactory$(LYSStep lYSStep, Listing listing, List list, boolean z, boolean z2, ListingRegistrationProcess listingRegistrationProcess) {
        return new LYSStep$$Lambda$5(lYSStep, listing, list, z, z2, listingRegistrationProcess);
    }

    public boolean apply(Object obj) {
        return LYSStep.isStepComplete((LYSStep) obj, this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6);
    }
}
