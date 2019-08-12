package com.airbnb.android.booking.fragments.alipay;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayPhoneFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayPhoneFragment_ObservableResubscriber(AlipayPhoneFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "AlipayPhoneFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
