package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.CheckInInformation;

final /* synthetic */ class ManageListingAllCheckInMethodsController$$Lambda$1 implements OnClickListener {
    private final ManageListingAllCheckInMethodsController arg$1;
    private final CheckInInformation arg$2;

    private ManageListingAllCheckInMethodsController$$Lambda$1(ManageListingAllCheckInMethodsController manageListingAllCheckInMethodsController, CheckInInformation checkInInformation) {
        this.arg$1 = manageListingAllCheckInMethodsController;
        this.arg$2 = checkInInformation;
    }

    public static OnClickListener lambdaFactory$(ManageListingAllCheckInMethodsController manageListingAllCheckInMethodsController, CheckInInformation checkInInformation) {
        return new ManageListingAllCheckInMethodsController$$Lambda$1(manageListingAllCheckInMethodsController, checkInInformation);
    }

    public void onClick(View view) {
        this.arg$1.listener.methodClicked(this.arg$2);
    }
}
