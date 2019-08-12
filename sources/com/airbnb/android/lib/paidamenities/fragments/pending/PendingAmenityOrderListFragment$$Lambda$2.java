package com.airbnb.android.lib.paidamenities.fragments.pending;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PendingAmenityOrderListFragment$$Lambda$2 implements Action1 {
    private final PendingAmenityOrderListFragment arg$1;

    private PendingAmenityOrderListFragment$$Lambda$2(PendingAmenityOrderListFragment pendingAmenityOrderListFragment) {
        this.arg$1 = pendingAmenityOrderListFragment;
    }

    public static Action1 lambdaFactory$(PendingAmenityOrderListFragment pendingAmenityOrderListFragment) {
        return new PendingAmenityOrderListFragment$$Lambda$2(pendingAmenityOrderListFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
