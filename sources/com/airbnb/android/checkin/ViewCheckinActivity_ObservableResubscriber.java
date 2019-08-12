package com.airbnb.android.checkin;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ViewCheckinActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ViewCheckinActivity_ObservableResubscriber(ViewCheckinActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getGuideListener, "ViewCheckinActivity_getGuideListener");
        group.resubscribeAll(target.getGuideListener);
        setTag((AutoTaggableObserver) target.getExampleGuideListener, "ViewCheckinActivity_getExampleGuideListener");
        group.resubscribeAll(target.getExampleGuideListener);
    }
}
