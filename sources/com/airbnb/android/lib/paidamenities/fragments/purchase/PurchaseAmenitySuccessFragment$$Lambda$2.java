package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PurchaseAmenitySuccessFragment$$Lambda$2 implements OnClickListener {
    private final PurchaseAmenitySuccessFragment arg$1;

    private PurchaseAmenitySuccessFragment$$Lambda$2(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        this.arg$1 = purchaseAmenitySuccessFragment;
    }

    public static OnClickListener lambdaFactory$(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        return new PurchaseAmenitySuccessFragment$$Lambda$2(purchaseAmenitySuccessFragment);
    }

    public void onClick(View view) {
        PurchaseAmenitySuccessFragment.lambda$setUpHeroMarquee$1(this.arg$1, view);
    }
}
