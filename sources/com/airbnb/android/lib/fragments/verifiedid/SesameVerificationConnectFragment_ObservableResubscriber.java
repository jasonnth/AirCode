package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SesameVerificationConnectFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SesameVerificationConnectFragment_ObservableResubscriber(SesameVerificationConnectFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.getUrlRequestListener, "SesameVerificationConnectFragment_getUrlRequestListener");
        group.resubscribeAll(target.getUrlRequestListener);
    }
}
