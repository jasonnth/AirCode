package com.airbnb.android.core.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSHouseRulesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSHouseRulesFragment_ObservableResubscriber(DLSHouseRulesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.houseRulesResponseRequestListener, "DLSHouseRulesFragment_houseRulesResponseRequestListener");
        group.resubscribeAll(target.houseRulesResponseRequestListener);
    }
}
