package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CreatedAmenityFragment$$Lambda$2 implements Action1 {
    private final CreatedAmenityFragment arg$1;

    private CreatedAmenityFragment$$Lambda$2(CreatedAmenityFragment createdAmenityFragment) {
        this.arg$1 = createdAmenityFragment;
    }

    public static Action1 lambdaFactory$(CreatedAmenityFragment createdAmenityFragment) {
        return new CreatedAmenityFragment$$Lambda$2(createdAmenityFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
