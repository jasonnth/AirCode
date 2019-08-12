package com.airbnb.android.managelisting.picker;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageListingPickerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageListingPickerFragment_ObservableResubscriber(ManageListingPickerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingPickerInfoListener, "ManageListingPickerFragment_listingPickerInfoListener");
        group.resubscribeAll(target.listingPickerInfoListener);
    }
}
