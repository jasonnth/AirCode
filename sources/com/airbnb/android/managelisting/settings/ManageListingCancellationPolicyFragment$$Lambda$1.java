package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCancellationPolicyFragment$$Lambda$1 implements Action1 {
    private final ManageListingCancellationPolicyFragment arg$1;

    private ManageListingCancellationPolicyFragment$$Lambda$1(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment) {
        this.arg$1 = manageListingCancellationPolicyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment) {
        return new ManageListingCancellationPolicyFragment$$Lambda$1(manageListingCancellationPolicyFragment);
    }

    public void call(Object obj) {
        ManageListingCancellationPolicyFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
