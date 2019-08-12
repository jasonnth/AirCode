package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DeclineInquiryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DeclineInquiryFragment_ObservableResubscriber(DeclineInquiryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.declineInquiryListener, "DeclineInquiryFragment_declineInquiryListener");
        group.resubscribeAll(target.declineInquiryListener);
    }
}
