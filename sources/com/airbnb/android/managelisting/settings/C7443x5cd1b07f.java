package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.managelisting.settings.ManageListingSeasonalCalendarSettingsFragment_ObservableResubscriber */
public class C7443x5cd1b07f extends BaseObservableResubscriber {
    public C7443x5cd1b07f(ManageListingSeasonalCalendarSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateCalendarListener, "ManageListingSeasonalCalendarSettingsFragment_updateCalendarListener");
        group.resubscribeAll(target.updateCalendarListener);
    }
}
