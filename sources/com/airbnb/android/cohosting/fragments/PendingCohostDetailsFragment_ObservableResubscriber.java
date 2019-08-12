package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PendingCohostDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PendingCohostDetailsFragment_ObservableResubscriber(PendingCohostDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.deleteInvitationListener, "PendingCohostDetailsFragment_deleteInvitationListener");
        group.resubscribeAll(target.deleteInvitationListener);
        setTag((AutoTaggableObserver) target.resendInvitationListener, "PendingCohostDetailsFragment_resendInvitationListener");
        group.resubscribeAll(target.resendInvitationListener);
    }
}
