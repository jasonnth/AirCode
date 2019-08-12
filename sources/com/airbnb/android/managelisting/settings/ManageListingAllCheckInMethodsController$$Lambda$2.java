package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.CheckInInformation;

final /* synthetic */ class ManageListingAllCheckInMethodsController$$Lambda$2 implements OnClickListener {
    private final ManageListingAllCheckInMethodsController arg$1;
    private final CheckInInformation arg$2;

    private ManageListingAllCheckInMethodsController$$Lambda$2(ManageListingAllCheckInMethodsController manageListingAllCheckInMethodsController, CheckInInformation checkInInformation) {
        this.arg$1 = manageListingAllCheckInMethodsController;
        this.arg$2 = checkInInformation;
    }

    public static OnClickListener lambdaFactory$(ManageListingAllCheckInMethodsController manageListingAllCheckInMethodsController, CheckInInformation checkInInformation) {
        return new ManageListingAllCheckInMethodsController$$Lambda$2(manageListingAllCheckInMethodsController, checkInInformation);
    }

    public void onClick(View view) {
        this.arg$1.listener.editMethodNoteClicked(this.arg$2);
    }
}
