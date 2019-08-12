package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.BookingSettingsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingPreBookingAddQuestionsFragment$$Lambda$1 implements Action1 {
    private final ManageListingPreBookingAddQuestionsFragment arg$1;

    private ManageListingPreBookingAddQuestionsFragment$$Lambda$1(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        this.arg$1 = manageListingPreBookingAddQuestionsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        return new ManageListingPreBookingAddQuestionsFragment$$Lambda$1(manageListingPreBookingAddQuestionsFragment);
    }

    public void call(Object obj) {
        ManageListingPreBookingAddQuestionsFragment.lambda$new$0(this.arg$1, (BookingSettingsResponse) obj);
    }
}
