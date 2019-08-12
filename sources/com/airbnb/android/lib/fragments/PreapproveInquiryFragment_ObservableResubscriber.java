package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PreapproveInquiryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PreapproveInquiryFragment_ObservableResubscriber(PreapproveInquiryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateInquiryListener, "PreapproveInquiryFragment_updateInquiryListener");
        group.resubscribeAll(target.updateInquiryListener);
        setTag((AutoTaggableObserver) target.inquiryListener, "PreapproveInquiryFragment_inquiryListener");
        group.resubscribeAll(target.inquiryListener);
    }
}
