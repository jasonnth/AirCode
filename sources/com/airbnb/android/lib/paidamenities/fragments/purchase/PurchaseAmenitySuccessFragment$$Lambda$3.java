package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PurchaseAmenitySuccessFragment$$Lambda$3 implements OnClickListener {
    private final PurchaseAmenitySuccessFragment arg$1;

    private PurchaseAmenitySuccessFragment$$Lambda$3(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        this.arg$1 = purchaseAmenitySuccessFragment;
    }

    public static OnClickListener lambdaFactory$(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        return new PurchaseAmenitySuccessFragment$$Lambda$3(purchaseAmenitySuccessFragment);
    }

    public void onClick(View view) {
        PurchaseAmenitySuccessFragment.lambda$setUpHeroMarquee$2(this.arg$1, view);
    }
}
