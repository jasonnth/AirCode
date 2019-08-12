package com.airbnb.android.lib.paidamenities.fragments.purchase;

import com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityItemDetailFragment.PurchaseAmenityItemDetailAdapter;
import com.airbnb.p027n2.interfaces.StepperRowInterface.OnValueChangedListener;

/* renamed from: com.airbnb.android.lib.paidamenities.fragments.purchase.PurchaseAmenityItemDetailFragment$PurchaseAmenityItemDetailAdapter$$Lambda$1 */
final /* synthetic */ class C7100x3ece98df implements OnValueChangedListener {
    private final PurchaseAmenityItemDetailAdapter arg$1;

    private C7100x3ece98df(PurchaseAmenityItemDetailAdapter purchaseAmenityItemDetailAdapter) {
        this.arg$1 = purchaseAmenityItemDetailAdapter;
    }

    public static OnValueChangedListener lambdaFactory$(PurchaseAmenityItemDetailAdapter purchaseAmenityItemDetailAdapter) {
        return new C7100x3ece98df(purchaseAmenityItemDetailAdapter);
    }

    public void onValueChanged(int i, int i2) {
        PurchaseAmenityItemDetailAdapter.lambda$configureAmenityDetails$0(this.arg$1, i, i2);
    }
}
