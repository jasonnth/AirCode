package com.airbnb.android.core.identity;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReplaceVerifiedIdWithIdentityActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ReplaceVerifiedIdWithIdentityActivity_ObservableResubscriber(ReplaceVerifiedIdWithIdentityActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.userRequestListener, "ReplaceVerifiedIdWithIdentityActivity_userRequestListener");
        group.resubscribeAll(target.userRequestListener);
    }
}
