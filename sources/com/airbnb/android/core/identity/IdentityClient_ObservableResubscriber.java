package com.airbnb.android.core.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class IdentityClient_ObservableResubscriber extends BaseObservableResubscriber {
    public IdentityClient_ObservableResubscriber(IdentityClient target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchGovernmentIdResultsListenerForSubmit, "IdentityClient_fetchGovernmentIdResultsListenerForSubmit");
        group.resubscribeAll(target.fetchGovernmentIdResultsListenerForSubmit);
    }
}
