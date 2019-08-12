package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCancellationPolicyFragment$$Lambda$2 implements Action1 {
    private final ManageListingCancellationPolicyFragment arg$1;

    private ManageListingCancellationPolicyFragment$$Lambda$2(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment) {
        this.arg$1 = manageListingCancellationPolicyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment) {
        return new ManageListingCancellationPolicyFragment$$Lambda$2(manageListingCancellationPolicyFragment);
    }

    public void call(Object obj) {
        ManageListingCancellationPolicyFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
