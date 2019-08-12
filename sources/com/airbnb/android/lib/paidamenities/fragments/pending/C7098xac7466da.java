package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment.PendingServicesListAdapter;

/* renamed from: com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment$PendingServicesListAdapter$$Lambda$1 */
final /* synthetic */ class C7098xac7466da implements OnClickListener {
    private final PendingServicesListAdapter arg$1;

    private C7098xac7466da(PendingServicesListAdapter pendingServicesListAdapter) {
        this.arg$1 = pendingServicesListAdapter;
    }

    public static OnClickListener lambdaFactory$(PendingServicesListAdapter pendingServicesListAdapter) {
        return new C7098xac7466da(pendingServicesListAdapter);
    }

    public void onClick(View view) {
        PendingAmenityOrderListFragment.this.requestAnotherService();
    }
}
