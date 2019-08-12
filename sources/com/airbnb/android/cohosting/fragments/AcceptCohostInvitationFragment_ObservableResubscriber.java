package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AcceptCohostInvitationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AcceptCohostInvitationFragment_ObservableResubscriber(AcceptCohostInvitationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.verificationListener, "AcceptCohostInvitationFragment_verificationListener");
        group.resubscribeAll(target.verificationListener);
        setTag((AutoTaggableObserver) target.acceptCohostInvitationListener, "AcceptCohostInvitationFragment_acceptCohostInvitationListener");
        group.resubscribeAll(target.acceptCohostInvitationListener);
    }
}
