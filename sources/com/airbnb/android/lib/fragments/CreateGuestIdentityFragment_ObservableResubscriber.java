package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateGuestIdentityFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateGuestIdentityFragment_ObservableResubscriber(CreateGuestIdentityFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.saveIdentityRequestListener, "CreateGuestIdentityFragment_saveIdentityRequestListener");
        group.resubscribeAll(target.saveIdentityRequestListener);
    }
}
