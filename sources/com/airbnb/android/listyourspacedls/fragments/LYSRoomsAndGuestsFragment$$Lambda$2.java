package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSRoomsAndGuestsFragment$$Lambda$2 implements Action1 {
    private final LYSRoomsAndGuestsFragment arg$1;

    private LYSRoomsAndGuestsFragment$$Lambda$2(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        this.arg$1 = lYSRoomsAndGuestsFragment;
    }

    public static Action1 lambdaFactory$(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment) {
        return new LYSRoomsAndGuestsFragment$$Lambda$2(lYSRoomsAndGuestsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
