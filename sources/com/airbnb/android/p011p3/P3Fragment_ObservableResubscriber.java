package com.airbnb.android.p011p3;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.p3.P3Fragment_ObservableResubscriber */
public class P3Fragment_ObservableResubscriber extends BaseObservableResubscriber {
    public P3Fragment_ObservableResubscriber(P3Fragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.referralStatusListener, "P3Fragment_referralStatusListener");
        group.resubscribeAll(target.referralStatusListener);
    }
}
