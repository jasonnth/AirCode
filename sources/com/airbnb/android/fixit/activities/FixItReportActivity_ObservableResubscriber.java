package com.airbnb.android.fixit.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class FixItReportActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public FixItReportActivity_ObservableResubscriber(FixItReportActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fixItReportListener, "FixItReportActivity_fixItReportListener");
        group.resubscribeAll(target.fixItReportListener);
    }
}
