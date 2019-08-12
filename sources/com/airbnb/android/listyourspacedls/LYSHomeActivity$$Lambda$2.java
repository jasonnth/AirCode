package com.airbnb.android.listyourspacedls;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class LYSHomeActivity$$Lambda$2 implements Action1 {
    private final LYSHomeActivity arg$1;

    private LYSHomeActivity$$Lambda$2(LYSHomeActivity lYSHomeActivity) {
        this.arg$1 = lYSHomeActivity;
    }

    public static Action1 lambdaFactory$(LYSHomeActivity lYSHomeActivity) {
        return new LYSHomeActivity$$Lambda$2(lYSHomeActivity);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.findViewById(C7251R.C7253id.fragment_container), (NetworkException) (AirRequestNetworkException) obj, LYSHomeActivity$$Lambda$6.lambdaFactory$(this.arg$1, this.arg$1.getIntent().getLongExtra("listing_id", -1)));
    }
}
