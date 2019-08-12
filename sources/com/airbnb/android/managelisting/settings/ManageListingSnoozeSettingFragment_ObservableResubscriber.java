package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingSnoozeSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingSnoozeSettingFragment_ObservableResubscriber(ManageListingSnoozeSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.snoozeUpdateListener, "ManageListingSnoozeSettingFragment_snoozeUpdateListener");
        group.resubscribeAll(target.snoozeUpdateListener);
    }
}
