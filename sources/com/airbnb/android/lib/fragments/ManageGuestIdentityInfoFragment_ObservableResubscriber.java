package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ManageGuestIdentityInfoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ManageGuestIdentityInfoFragment_ObservableResubscriber(ManageGuestIdentityInfoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.deleteIdentityRequestListener, "ManageGuestIdentityInfoFragment_deleteIdentityRequestListener");
        group.resubscribeAll(target.deleteIdentityRequestListener);
    }
}
