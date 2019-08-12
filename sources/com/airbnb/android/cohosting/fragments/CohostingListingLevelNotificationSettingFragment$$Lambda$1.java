package com.airbnb.android.cohosting.fragments;

import com.airbnb.android.core.responses.UpdateCohostingNotificationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingListingLevelNotificationSettingFragment$$Lambda$1 implements Action1 {
    private final CohostingListingLevelNotificationSettingFragment arg$1;

    private CohostingListingLevelNotificationSettingFragment$$Lambda$1(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment) {
        this.arg$1 = cohostingListingLevelNotificationSettingFragment;
    }

    public static Action1 lambdaFactory$(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment) {
        return new CohostingListingLevelNotificationSettingFragment$$Lambda$1(cohostingListingLevelNotificationSettingFragment);
    }

    public void call(Object obj) {
        CohostingListingLevelNotificationSettingFragment.lambda$new$0(this.arg$1, (UpdateCohostingNotificationResponse) obj);
    }
}
