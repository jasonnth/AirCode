package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class UserProfileFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public UserProfileFragment_ObservableResubscriber(UserProfileFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.userFlagResponseRequestListener, "UserProfileFragment_userFlagResponseRequestListener");
        group.resubscribeAll(target.userFlagResponseRequestListener);
        setTag((AutoTaggableObserver) target.userRequestListener, "UserProfileFragment_userRequestListener");
        group.resubscribeAll(target.userRequestListener);
        setTag((AutoTaggableObserver) target.userListingsListener, "UserProfileFragment_userListingsListener");
        group.resubscribeAll(target.userListingsListener);
    }
}
