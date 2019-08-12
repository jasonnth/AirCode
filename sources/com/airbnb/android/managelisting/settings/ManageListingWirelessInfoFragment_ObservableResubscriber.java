package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingWirelessInfoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingWirelessInfoFragment_ObservableResubscriber(ManageListingWirelessInfoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateWifiInfoListener, "ManageListingWirelessInfoFragment_updateWifiInfoListener");
        group.resubscribeAll(target.updateWifiInfoListener);
        setTag((AutoTaggableObserver) target.deleteWifiInfoListener, "ManageListingWirelessInfoFragment_deleteWifiInfoListener");
        group.resubscribeAll(target.deleteWifiInfoListener);
    }
}
