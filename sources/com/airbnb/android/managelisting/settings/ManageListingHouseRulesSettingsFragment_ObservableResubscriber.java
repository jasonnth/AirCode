package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingHouseRulesSettingsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingHouseRulesSettingsFragment_ObservableResubscriber(ManageListingHouseRulesSettingsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingListener, "ManageListingHouseRulesSettingsFragment_listingListener");
        group.resubscribeAll(target.listingListener);
        setTag((AutoTaggableObserver) target.updateGuestControlsListener, "ManageListingHouseRulesSettingsFragment_updateGuestControlsListener");
        group.resubscribeAll(target.updateGuestControlsListener);
    }
}
