package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingInstantBookUpsellFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingInstantBookUpsellFragment_ObservableResubscriber(ManageListingInstantBookUpsellFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "ManageListingInstantBookUpsellFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
