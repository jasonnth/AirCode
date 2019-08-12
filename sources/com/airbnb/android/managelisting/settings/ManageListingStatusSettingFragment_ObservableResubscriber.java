package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingStatusSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingStatusSettingFragment_ObservableResubscriber(ManageListingStatusSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingStatusSettingFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
        setTag((AutoTaggableObserver) target.deleteListingListener, "ManageListingStatusSettingFragment_deleteListingListener");
        group.resubscribeAll(target.deleteListingListener);
    }
}
