package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPreBookingAddQuestionsFragment$$Lambda$3 implements OnClickListener {
    private final ManageListingPreBookingAddQuestionsFragment arg$1;

    private ManageListingPreBookingAddQuestionsFragment$$Lambda$3(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        this.arg$1 = manageListingPreBookingAddQuestionsFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingPreBookingAddQuestionsFragment manageListingPreBookingAddQuestionsFragment) {
        return new ManageListingPreBookingAddQuestionsFragment$$Lambda$3(manageListingPreBookingAddQuestionsFragment);
    }

    public void onClick(View view) {
        this.arg$1.checkUpdateBookingSettings();
    }
}