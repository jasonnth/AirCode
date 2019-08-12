package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingAvailabilitySettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingAvailabilitySettingsFragment_ObservableResubscriber(ManageListingAvailabilitySettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.calendarRulesListener, "ManageListingAvailabilitySettingsFragment_calendarRulesListener");
        group.resubscribeAll(target.calendarRulesListener);
    }
}
