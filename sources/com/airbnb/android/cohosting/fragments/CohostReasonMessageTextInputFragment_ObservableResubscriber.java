package com.airbnb.android.cohosting.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CohostReasonMessageTextInputFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CohostReasonMessageTextInputFragment_ObservableResubscriber(CohostReasonMessageTextInputFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reasonsRequestListener, "CohostReasonMessageTextInputFragment_reasonsRequestListener");
        group.resubscribeAll(target.reasonsRequestListener);
    }
}
