package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingEditAddressFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingEditAddressFragment_ObservableResubscriber(ManageListingEditAddressFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRegistrationProcessesListener, "ManageListingEditAddressFragment_listingRegistrationProcessesListener");
        group.resubscribeAll(target.listingRegistrationProcessesListener);
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingEditAddressFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
