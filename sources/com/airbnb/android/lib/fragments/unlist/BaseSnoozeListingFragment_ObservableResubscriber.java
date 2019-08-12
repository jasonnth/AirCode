package com.airbnb.android.lib.fragments.unlist;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BaseSnoozeListingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BaseSnoozeListingFragment_ObservableResubscriber(BaseSnoozeListingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingUpdateRequestListener, "BaseSnoozeListingFragment_listingUpdateRequestListener");
    }
}
