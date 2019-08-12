package com.airbnb.android.hostcalendar.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostSingleCalendarActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HostSingleCalendarActivity_ObservableResubscriber(HostSingleCalendarActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingRequestListener, "HostSingleCalendarActivity_listingRequestListener");
        group.resubscribeAll(target.listingRequestListener);
    }
}
