package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.P3BusinessDetailsFragment_ObservableResubscriber */
public class P3BusinessDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public P3BusinessDetailsFragment_ObservableResubscriber(P3BusinessDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.commercialHostInfoRequestListener, "P3BusinessDetailsFragment_commercialHostInfoRequestListener");
        group.resubscribeAll(target.commercialHostInfoRequestListener);
    }
}
