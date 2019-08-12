package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PendingCohostDetailsFragment$$Lambda$4 implements Action1 {
    private final PendingCohostDetailsFragment arg$1;

    private PendingCohostDetailsFragment$$Lambda$4(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        this.arg$1 = pendingCohostDetailsFragment;
    }

    public static Action1 lambdaFactory$(PendingCohostDetailsFragment pendingCohostDetailsFragment) {
        return new PendingCohostDetailsFragment$$Lambda$4(pendingCohostDetailsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj, C5658R.string.cohosting_resend_invite_error, C5658R.string.try_again_later);
    }
}
