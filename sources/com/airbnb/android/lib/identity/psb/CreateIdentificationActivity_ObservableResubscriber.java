package com.airbnb.android.lib.identity.psb;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateIdentificationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateIdentificationActivity_ObservableResubscriber(CreateIdentificationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.saveIdentityRequestListener, "CreateIdentificationActivity_saveIdentityRequestListener");
        group.resubscribeAll(target.saveIdentityRequestListener);
    }
}
