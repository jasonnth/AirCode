package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InviteGuestsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public InviteGuestsFragment_ObservableResubscriber(InviteGuestsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "InviteGuestsFragment_listener");
        group.resubscribeAll(target.listener);
        setTag((AutoTaggableObserver) target.deleteUserListener, "InviteGuestsFragment_deleteUserListener");
        group.resubscribeAll(target.deleteUserListener);
    }
}
