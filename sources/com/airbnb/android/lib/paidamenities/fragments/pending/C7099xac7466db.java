package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment.PendingServicesListAdapter;

/* renamed from: com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment$PendingServicesListAdapter$$Lambda$2 */
final /* synthetic */ class C7099xac7466db implements OnClickListener {
    private final PendingServicesListAdapter arg$1;
    private final PaidAmenityOrder arg$2;

    private C7099xac7466db(PendingServicesListAdapter pendingServicesListAdapter, PaidAmenityOrder paidAmenityOrder) {
        this.arg$1 = pendingServicesListAdapter;
        this.arg$2 = paidAmenityOrder;
    }

    public static OnClickListener lambdaFactory$(PendingServicesListAdapter pendingServicesListAdapter, PaidAmenityOrder paidAmenityOrder) {
        return new C7099xac7466db(pendingServicesListAdapter, paidAmenityOrder);
    }

    public void onClick(View view) {
        PendingServicesListAdapter.lambda$paidAmenityOrderToRowModel$1(this.arg$1, this.arg$2, view);
    }
}
