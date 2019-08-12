package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class MessageThreadWebLinkActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public MessageThreadWebLinkActivity_ObservableResubscriber(MessageThreadWebLinkActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.threadInboxTypeRequest, "MessageThreadWebLinkActivity_threadInboxTypeRequest");
        group.resubscribeAll(target.threadInboxTypeRequest);
    }
}
