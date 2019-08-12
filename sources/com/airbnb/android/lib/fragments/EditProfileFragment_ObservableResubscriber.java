package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class EditProfileFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public EditProfileFragment_ObservableResubscriber(EditProfileFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateVerificationsListener, "EditProfileFragment_updateVerificationsListener");
        group.resubscribeAll(target.updateVerificationsListener);
        setTag((AutoTaggableObserver) target.deleteManualVerificationRequestListener, "EditProfileFragment_deleteManualVerificationRequestListener");
        group.resubscribeAll(target.deleteManualVerificationRequestListener);
        setTag((AutoTaggableObserver) target.userRequestListener, "EditProfileFragment_userRequestListener");
        group.resubscribeAll(target.userRequestListener);
    }
}
