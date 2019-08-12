package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InboxSearchFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public InboxSearchFragment_ObservableResubscriber(InboxSearchFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "InboxSearchFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
