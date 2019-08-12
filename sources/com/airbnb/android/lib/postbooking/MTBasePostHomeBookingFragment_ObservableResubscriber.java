package com.airbnb.android.lib.postbooking;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class MTBasePostHomeBookingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public MTBasePostHomeBookingFragment_ObservableResubscriber(MTBasePostHomeBookingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.phbRequestListener, "MTBasePostHomeBookingFragment_phbRequestListener");
        group.resubscribeAll(target.phbRequestListener);
    }
}
