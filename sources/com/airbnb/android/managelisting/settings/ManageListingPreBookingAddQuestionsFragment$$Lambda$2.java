package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingPreBookingAddQuestionsFragment$$Lambda$2 implements Action1 {
    private final ManageListingPreBookingAddQuestionsFragment arg$1;

    private ManageListingPreBookingAddQuestionsFragment$$Lambda$2(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        this.arg$1 = manageListingPreBookingAddQuestionsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        return new ManageListingPreBookingAddQuestionsFragment$$Lambda$2(manageListingPreBookingAddQuestionsFragment);
    }

    public void call(Object obj) {
        ManageListingPreBookingAddQuestionsFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
