package com.airbnb.android.cohosting.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AcceptCohostInvitationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public AcceptCohostInvitationActivity_ObservableResubscriber(AcceptCohostInvitationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.cohostInvitationListener, "AcceptCohostInvitationActivity_cohostInvitationListener");
        group.resubscribeAll(target.cohostInvitationListener);
    }
}
