package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InboxFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public InboxFragment_ObservableResubscriber(InboxFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.archiveListener, "InboxFragment_archiveListener");
        group.resubscribeAll(target.archiveListener);
        setTag((AutoTaggableObserver) target.alertsListener, "InboxFragment_alertsListener");
        group.resubscribeAll(target.alertsListener);
        setTag((AutoTaggableObserver) target.inboxRequestListener, "InboxFragment_inboxRequestListener");
        group.resubscribeAll(target.inboxRequestListener);
    }
}
