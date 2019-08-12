package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingTextSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingTextSettingFragment_ObservableResubscriber(ManageListingTextSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingTextSettingFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
        setTag((AutoTaggableObserver) target.updateBookingSettingsListener, "ManageListingTextSettingFragment_updateBookingSettingsListener");
        group.resubscribeAll(target.updateBookingSettingsListener);
        setTag((AutoTaggableObserver) target.updateSelectListingListener, "ManageListingTextSettingFragment_updateSelectListingListener");
        group.resubscribeAll(target.updateSelectListingListener);
    }
}
