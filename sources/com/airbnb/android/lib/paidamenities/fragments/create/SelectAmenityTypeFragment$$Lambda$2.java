package com.airbnb.android.lib.paidamenities.fragments.create;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class SelectAmenityTypeFragment$$Lambda$2 implements Action1 {
    private final SelectAmenityTypeFragment arg$1;

    private SelectAmenityTypeFragment$$Lambda$2(SelectAmenityTypeFragment selectAmenityTypeFragment) {
        this.arg$1 = selectAmenityTypeFragment;
    }

    public static Action1 lambdaFactory$(SelectAmenityTypeFragment selectAmenityTypeFragment) {
        return new SelectAmenityTypeFragment$$Lambda$2(selectAmenityTypeFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
