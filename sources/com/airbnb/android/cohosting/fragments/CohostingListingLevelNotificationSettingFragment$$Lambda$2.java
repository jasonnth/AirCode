package com.airbnb.android.cohosting.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CohostingListingLevelNotificationSettingFragment$$Lambda$2 implements Action1 {
    private final CohostingListingLevelNotificationSettingFragment arg$1;

    private CohostingListingLevelNotificationSettingFragment$$Lambda$2(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment) {
        this.arg$1 = cohostingListingLevelNotificationSettingFragment;
    }

    public static Action1 lambdaFactory$(CohostingListingLevelNotificationSettingFragment cohostingListingLevelNotificationSettingFragment) {
        return new CohostingListingLevelNotificationSettingFragment$$Lambda$2(cohostingListingLevelNotificationSettingFragment);
    }

    public void call(Object obj) {
        CohostingListingLevelNotificationSettingFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
