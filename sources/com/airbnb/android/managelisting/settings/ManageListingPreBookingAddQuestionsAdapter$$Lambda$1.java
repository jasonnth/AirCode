package com.airbnb.android.managelisting.settings;

import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class ManageListingPreBookingAddQuestionsAdapter$$Lambda$1 implements OnCheckedChangeListener {
    private final ManageListingPreBookingAddQuestionsAdapter arg$1;
    private final int arg$2;

    private ManageListingPreBookingAddQuestionsAdapter$$Lambda$1(ManageListingPreBookingAddQuestionsAdapter manageListingPreBookingAddQuestionsAdapter, int i) {
        this.arg$1 = manageListingPreBookingAddQuestionsAdapter;
        this.arg$2 = i;
    }

    public static OnCheckedChangeListener lambdaFactory$(ManageListingPreBookingAddQuestionsAdapter manageListingPreBookingAddQuestionsAdapter, int i) {
        return new ManageListingPreBookingAddQuestionsAdapter$$Lambda$1(manageListingPreBookingAddQuestionsAdapter, i);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.standardQuestionsChecked.set(this.arg$2, Boolean.valueOf(z));
    }
}
