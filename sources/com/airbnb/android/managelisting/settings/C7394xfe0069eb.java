package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.managelisting.settings.ManageListingAdditionalGuestRequirementsFragment_ObservableResubscriber */
public class C7394xfe0069eb extends BaseObservableResubscriber {
    public C7394xfe0069eb(ManageListingAdditionalGuestRequirementsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingAdditionalGuestRequirementsFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
