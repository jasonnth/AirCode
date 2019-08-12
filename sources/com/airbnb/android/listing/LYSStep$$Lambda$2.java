package com.airbnb.android.listing;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.google.common.base.Predicate;
import java.util.List;

final /* synthetic */ class LYSStep$$Lambda$2 implements Predicate {
    private final LYSStep arg$1;
    private final Listing arg$2;
    private final List arg$3;
    private final boolean arg$4;
    private final boolean arg$5;
    private final ListingRegistrationProcess arg$6;
    private final boolean arg$7;

    private LYSStep$$Lambda$2(LYSStep lYSStep, Listing listing, List list, boolean z, boolean z2, ListingRegistrationProcess listingRegistrationProcess, boolean z3) {
        this.arg$1 = lYSStep;
        this.arg$2 = listing;
        this.arg$3 = list;
        this.arg$4 = z;
        this.arg$5 = z2;
        this.arg$6 = listingRegistrationProcess;
        this.arg$7 = z3;
    }

    public static Predicate lambdaFactory$(LYSStep lYSStep, Listing listing, List list, boolean z, boolean z2, ListingRegistrationProcess listingRegistrationProcess, boolean z3) {
        return new LYSStep$$Lambda$2(lYSStep, listing, list, z, z2, listingRegistrationProcess, z3);
    }

    public boolean apply(Object obj) {
        return LYSStep.lambda$getFirstIncompleteAndNonSkippableStep$1(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, (LYSStep) obj);
    }
}
