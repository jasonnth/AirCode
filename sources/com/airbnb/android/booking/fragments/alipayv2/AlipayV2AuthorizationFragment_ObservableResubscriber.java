package com.airbnb.android.booking.fragments.alipayv2;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayV2AuthorizationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayV2AuthorizationFragment_ObservableResubscriber(AlipayV2AuthorizationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchDeeplinkUrlListener, "AlipayV2AuthorizationFragment_fetchDeeplinkUrlListener");
        group.resubscribeAll(target.fetchDeeplinkUrlListener);
    }
}
