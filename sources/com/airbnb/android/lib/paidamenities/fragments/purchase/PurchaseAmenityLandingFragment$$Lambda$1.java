package com.airbnb.android.lib.paidamenities.fragments.purchase;

import com.airbnb.android.lib.paidamenities.responses.FetchAllPaidAmenitiesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PurchaseAmenityLandingFragment$$Lambda$1 implements Action1 {
    private final PurchaseAmenityLandingFragment arg$1;

    private PurchaseAmenityLandingFragment$$Lambda$1(PurchaseAmenityLandingFragment purchaseAmenityLandingFragment) {
        this.arg$1 = purchaseAmenityLandingFragment;
    }

    public static Action1 lambdaFactory$(PurchaseAmenityLandingFragment purchaseAmenityLandingFragment) {
        return new PurchaseAmenityLandingFragment$$Lambda$1(purchaseAmenityLandingFragment);
    }

    public void call(Object obj) {
        this.arg$1.navigationController.doneWithFetchingAmenities(((FetchAllPaidAmenitiesResponse) obj).paidAmenities);
    }
}
