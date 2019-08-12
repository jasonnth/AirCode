package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment.PendingAmenityOrderDetailAdapter;

/* renamed from: com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment$PendingAmenityOrderDetailAdapter$$Lambda$1 */
final /* synthetic */ class C7096x8763339d implements OnClickListener {
    private final PendingAmenityOrderDetailAdapter arg$1;

    private C7096x8763339d(PendingAmenityOrderDetailAdapter pendingAmenityOrderDetailAdapter) {
        this.arg$1 = pendingAmenityOrderDetailAdapter;
    }

    public static OnClickListener lambdaFactory$(PendingAmenityOrderDetailAdapter pendingAmenityOrderDetailAdapter) {
        return new C7096x8763339d(pendingAmenityOrderDetailAdapter);
    }

    public void onClick(View view) {
        PendingAmenityOrderDetailAdapter.lambda$configureCancelLinkRow$0(this.arg$1, view);
    }
}
