package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CalendarFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CalendarFragment_ObservableResubscriber(CalendarFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingLoaderListener, "CalendarFragment_listingLoaderListener");
        group.resubscribeAll(target.listingLoaderListener);
    }
}
