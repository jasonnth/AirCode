package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayNationalIdFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayNationalIdFragment_ObservableResubscriber(AlipayNationalIdFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "AlipayNationalIdFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
