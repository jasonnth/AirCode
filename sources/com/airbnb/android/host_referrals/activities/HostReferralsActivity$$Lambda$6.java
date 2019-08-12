package com.airbnb.android.host_referrals.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.host_referrals.C6405R;
import p032rx.functions.Action1;

final /* synthetic */ class HostReferralsActivity$$Lambda$6 implements Action1 {
    private final HostReferralsActivity arg$1;

    private HostReferralsActivity$$Lambda$6(HostReferralsActivity hostReferralsActivity) {
        this.arg$1 = hostReferralsActivity;
    }

    public static Action1 lambdaFactory$(HostReferralsActivity hostReferralsActivity) {
        return new HostReferralsActivity$$Lambda$6(hostReferralsActivity);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.findViewById(C6405R.C6407id.content_container), (AirRequestNetworkException) obj);
    }
}
