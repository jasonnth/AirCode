package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class EditProfileActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public EditProfileActivity_ObservableResubscriber(EditProfileActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.userRequestListenerForProfile, "EditProfileActivity_userRequestListenerForProfile");
        group.resubscribeAll(target.userRequestListenerForProfile);
    }
}
