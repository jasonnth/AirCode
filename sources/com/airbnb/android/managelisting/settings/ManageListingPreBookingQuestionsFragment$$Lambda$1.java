package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ManageListingPreBookingQuestionsFragment$$Lambda$1 implements OnClickListener {
    private final ManageListingPreBookingQuestionsFragment arg$1;

    private ManageListingPreBookingQuestionsFragment$$Lambda$1(ManageListingPreBookingQuestionsFragment manageListingPreBookingQuestionsFragment) {
        this.arg$1 = manageListingPreBookingQuestionsFragment;
    }

    public static OnClickListener lambdaFactory$(ManageListingPreBookingQuestionsFragment manageListingPreBookingQuestionsFragment) {
        return new ManageListingPreBookingQuestionsFragment$$Lambda$1(manageListingPreBookingQuestionsFragment);
    }

    public void onClick(View view) {
        this.arg$1.controller.actionExecutor.instantBookUpsell();
    }
}
