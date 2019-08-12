package com.airbnb.android.login.oauth.strategies;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlipayLoginStrategy_ObservableResubscriber extends BaseObservableResubscriber {
    public AlipayLoginStrategy_ObservableResubscriber(AlipayLoginStrategy target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.alipayAuthCodeParamsResponseRequestListener, "AlipayLoginStrategy_alipayAuthCodeParamsResponseRequestListener");
        group.resubscribeAll(target.alipayAuthCodeParamsResponseRequestListener);
    }
}
