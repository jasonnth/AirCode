package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PurchaseAmenitySuccessFragment$$Lambda$1 implements OnClickListener {
    private final PurchaseAmenitySuccessFragment arg$1;

    private PurchaseAmenitySuccessFragment$$Lambda$1(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        this.arg$1 = purchaseAmenitySuccessFragment;
    }

    public static OnClickListener lambdaFactory$(PurchaseAmenitySuccessFragment purchaseAmenitySuccessFragment) {
        return new PurchaseAmenitySuccessFragment$$Lambda$1(purchaseAmenitySuccessFragment);
    }

    public void onClick(View view) {
        PurchaseAmenitySuccessFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
