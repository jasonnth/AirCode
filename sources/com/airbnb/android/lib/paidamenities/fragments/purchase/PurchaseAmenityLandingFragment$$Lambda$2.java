package com.airbnb.android.lib.paidamenities.fragments.purchase;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PurchaseAmenityLandingFragment$$Lambda$2 implements Action1 {
    private final PurchaseAmenityLandingFragment arg$1;

    private PurchaseAmenityLandingFragment$$Lambda$2(PurchaseAmenityLandingFragment purchaseAmenityLandingFragment) {
        this.arg$1 = purchaseAmenityLandingFragment;
    }

    public static Action1 lambdaFactory$(PurchaseAmenityLandingFragment purchaseAmenityLandingFragment) {
        return new PurchaseAmenityLandingFragment$$Lambda$2(purchaseAmenityLandingFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
