package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CohostingListingLevelNotificationEpoxyController$$Lambda$3 implements OnCheckedChangeListener {
    private final CohostingListingLevelNotificationEpoxyController arg$1;

    private CohostingListingLevelNotificationEpoxyController$$Lambda$3(CohostingListingLevelNotificationEpoxyController cohostingListingLevelNotificationEpoxyController) {
        this.arg$1 = cohostingListingLevelNotificationEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingListingLevelNotificationEpoxyController cohostingListingLevelNotificationEpoxyController) {
        return new CohostingListingLevelNotificationEpoxyController$$Lambda$3(cohostingListingLevelNotificationEpoxyController);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        this.arg$1.updateMuteTypeWhenSwitch(z);
    }
}
