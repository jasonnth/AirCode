package com.airbnb.android.registration;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateSocialAccountFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateSocialAccountFragment_ObservableResubscriber(CreateSocialAccountFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createSocialAccountRequestListener, "CreateSocialAccountFragment_createSocialAccountRequestListener");
        group.resubscribeAll(target.createSocialAccountRequestListener);
    }
}
