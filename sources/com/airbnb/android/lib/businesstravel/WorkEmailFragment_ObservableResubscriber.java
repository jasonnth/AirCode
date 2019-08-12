package com.airbnb.android.lib.businesstravel;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WorkEmailFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public WorkEmailFragment_ObservableResubscriber(WorkEmailFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.addWorkEmailListener, "WorkEmailFragment_addWorkEmailListener");
        group.resubscribeAll(target.addWorkEmailListener);
        setTag((AutoTaggableObserver) target.removeWorkEmailListener, "WorkEmailFragment_removeWorkEmailListener");
        group.resubscribeAll(target.removeWorkEmailListener);
    }
}
