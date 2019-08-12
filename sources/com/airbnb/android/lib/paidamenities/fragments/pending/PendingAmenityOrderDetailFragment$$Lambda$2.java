package com.airbnb.android.lib.paidamenities.fragments.pending;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PendingAmenityOrderDetailFragment$$Lambda$2 implements Action1 {
    private final PendingAmenityOrderDetailFragment arg$1;

    private PendingAmenityOrderDetailFragment$$Lambda$2(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment) {
        this.arg$1 = pendingAmenityOrderDetailFragment;
    }

    public static Action1 lambdaFactory$(PendingAmenityOrderDetailFragment pendingAmenityOrderDetailFragment) {
        return new PendingAmenityOrderDetailFragment$$Lambda$2(pendingAmenityOrderDetailFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
