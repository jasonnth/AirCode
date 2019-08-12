package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.p027n2.components.ToggleActionRow;
import com.airbnb.p027n2.components.ToggleActionRow.OnCheckedChangeListener;

final /* synthetic */ class CohostingListingLevelNotificationEpoxyController$$Lambda$2 implements OnCheckedChangeListener {
    private final CohostingListingLevelNotificationEpoxyController arg$1;

    private CohostingListingLevelNotificationEpoxyController$$Lambda$2(CohostingListingLevelNotificationEpoxyController cohostingListingLevelNotificationEpoxyController) {
        this.arg$1 = cohostingListingLevelNotificationEpoxyController;
    }

    public static OnCheckedChangeListener lambdaFactory$(CohostingListingLevelNotificationEpoxyController cohostingListingLevelNotificationEpoxyController) {
        return new CohostingListingLevelNotificationEpoxyController$$Lambda$2(cohostingListingLevelNotificationEpoxyController);
    }

    public void onCheckedChanged(ToggleActionRow toggleActionRow, boolean z) {
        this.arg$1.updateMuteType(MuteType.MUTED);
    }
}
