package com.airbnb.android.lib.businesstravel;

import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.lib.C0880R;
import p032rx.functions.Action1;

final /* synthetic */ class BusinessTravelWelcomeFragment$$Lambda$2 implements Action1 {
    private final BusinessTravelWelcomeFragment arg$1;

    private BusinessTravelWelcomeFragment$$Lambda$2(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        this.arg$1 = businessTravelWelcomeFragment;
    }

    public static Action1 lambdaFactory$(BusinessTravelWelcomeFragment businessTravelWelcomeFragment) {
        return new BusinessTravelWelcomeFragment$$Lambda$2(businessTravelWelcomeFragment);
    }

    public void call(Object obj) {
        ErrorUtils.showErrorUsingSnackbar(this.arg$1.getView(), C0880R.string.error_request);
    }
}
