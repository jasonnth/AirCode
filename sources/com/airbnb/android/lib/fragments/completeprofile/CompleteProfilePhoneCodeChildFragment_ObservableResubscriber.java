package com.airbnb.android.lib.fragments.completeprofile;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CompleteProfilePhoneCodeChildFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CompleteProfilePhoneCodeChildFragment_ObservableResubscriber(CompleteProfilePhoneCodeChildFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getActiveAccountRequestListener, "CompleteProfilePhoneCodeChildFragment_getActiveAccountRequestListener");
        group.resubscribeAll(target.getActiveAccountRequestListener);
        setTag((AutoTaggableObserver) target.editPhoneNumberRequestListener, "CompleteProfilePhoneCodeChildFragment_editPhoneNumberRequestListener");
        group.resubscribeAll(target.editPhoneNumberRequestListener);
    }
}
