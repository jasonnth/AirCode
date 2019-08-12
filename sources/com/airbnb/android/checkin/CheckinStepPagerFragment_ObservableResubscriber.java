package com.airbnb.android.checkin;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CheckinStepPagerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CheckinStepPagerFragment_ObservableResubscriber(CheckinStepPagerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.checkInNotificationListener, "CheckinStepPagerFragment_checkInNotificationListener");
        group.resubscribeAll(target.checkInNotificationListener);
    }
}
