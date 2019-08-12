package com.airbnb.android.listing;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class LYSStep$$Lambda$4 implements Predicate {
    private final Listing arg$1;
    private final ListingRegistrationProcess arg$2;
    private final LYSStep arg$3;
    private final List arg$4;
    private final boolean arg$5;
    private final boolean arg$6;
    private final boolean arg$7;

    private LYSStep$$Lambda$4(Listing listing, ListingRegistrationProcess listingRegistrationProcess, LYSStep lYSStep, List list, boolean z, boolean z2, boolean z3) {
        this.arg$1 = listing;
        this.arg$2 = listingRegistrationProcess;
        this.arg$3 = lYSStep;
        this.arg$4 = list;
        this.arg$5 = z;
        this.arg$6 = z2;
        this.arg$7 = z3;
    }

    public static Predicate lambdaFactory$(Listing listing, ListingRegistrationProcess listingRegistrationProcess, LYSStep lYSStep, List list, boolean z, boolean z2, boolean z3) {
        return new LYSStep$$Lambda$4(listing, listingRegistrationProcess, lYSStep, list, z, z2, z3);
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getFirstNonskippableStep$3(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, (LYSStep) obj);
    }
}
