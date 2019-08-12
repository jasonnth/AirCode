package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PendingAmenityOrderDetailFragment$$Lambda$3 implements OnClickListener {
    private final PendingAmenityOrderDetailFragment arg$1;

    private PendingAmenityOrderDetailFragment$$Lambda$3(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment) {
        this.arg$1 = pendingAmenityOrderDetailFragment;
    }

    public static OnClickListener lambdaFactory$(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment) {
        return new PendingAmenityOrderDetailFragment$$Lambda$3(pendingAmenityOrderDetailFragment);
    }

    public void onClick(View view) {
        PendingAmenityOrderDetailFragment.lambda$setUpHostActionButtons$0(this.arg$1, view);
    }
}
