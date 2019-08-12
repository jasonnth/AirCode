package com.airbnb.android.lib.paidamenities.fragments.pending;

import com.airbnb.android.lib.paidamenities.responses.PaidAmenityOrdersResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PendingAmenityOrderListFragment$$Lambda$1 implements Action1 {
    private final PendingAmenityOrderListFragment arg$1;

    private PendingAmenityOrderListFragment$$Lambda$1(PendingAmenityOrderListFragment pendingAmenityOrderListFragment) {
        this.arg$1 = pendingAmenityOrderListFragment;
    }

    public static Action1 lambdaFactory$(PendingAmenityOrderListFragment pendingAmenityOrderListFragment) {
        return new PendingAmenityOrderListFragment$$Lambda$1(pendingAmenityOrderListFragment);
    }

    public void call(Object obj) {
        PendingAmenityOrderListFragment.lambda$new$0(this.arg$1, (PaidAmenityOrdersResponse) obj);
    }
}
