package com.airbnb.android.core.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSCancellationPolicyFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSCancellationPolicyFragment_ObservableResubscriber(DLSCancellationPolicyFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.cancellationPolicyRequestListener, "DLSCancellationPolicyFragment_cancellationPolicyRequestListener");
        group.resubscribeAll(target.cancellationPolicyRequestListener);
    }
}
