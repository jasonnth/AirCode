package com.airbnb.android.managelisting.settings;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.CheckInInformation;

final /* synthetic */ class ManageListingSelfCheckInMethodsController$$Lambda$1 implements OnClickListener {
    private final ManageListingSelfCheckInMethodsController arg$1;
    private final CheckInInformation arg$2;

    private ManageListingSelfCheckInMethodsController$$Lambda$1(ManageListingSelfCheckInMethodsController manageListingSelfCheckInMethodsController, CheckInInformation checkInInformation) {
        this.arg$1 = manageListingSelfCheckInMethodsController;
        this.arg$2 = checkInInformation;
    }

    public static OnClickListener lambdaFactory$(ManageListingSelfCheckInMethodsController manageListingSelfCheckInMethodsController, CheckInInformation checkInInformation) {
        return new ManageListingSelfCheckInMethodsController$$Lambda$1(manageListingSelfCheckInMethodsController, checkInInformation);
    }

    public void onClick(View view) {
        this.arg$1.listener.methodSelected(this.arg$2);
    }
}
