package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingBasePriceSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingBasePriceSettingFragment_ObservableResubscriber(ManageListingBasePriceSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingBasePriceSettingFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
