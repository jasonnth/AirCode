package com.airbnb.android.lib.host.stats;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class HostReviewDetailsFragment$$Lambda$9 implements Action1 {
    private final HostReviewDetailsFragment arg$1;

    private HostReviewDetailsFragment$$Lambda$9(HostReviewDetailsFragment hostReviewDetailsFragment) {
        this.arg$1 = hostReviewDetailsFragment;
    }

    public static Action1 lambdaFactory$(HostReviewDetailsFragment hostReviewDetailsFragment) {
        return new HostReviewDetailsFragment$$Lambda$9(hostReviewDetailsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
