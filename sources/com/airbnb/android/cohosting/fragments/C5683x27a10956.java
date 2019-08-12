package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.cohosting.fragments.CohostingListingLevelNotificationSettingFragment_ObservableResubscriber */
public class C5683x27a10956 extends BaseObservableResubscriber {
    public C5683x27a10956(CohostingListingLevelNotificationSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateCohostingNotificationListener, "CohostingListingLevelNotificationSettingFragment_updateCohostingNotificationListener");
        group.resubscribeAll(target.updateCohostingNotificationListener);
    }
}
