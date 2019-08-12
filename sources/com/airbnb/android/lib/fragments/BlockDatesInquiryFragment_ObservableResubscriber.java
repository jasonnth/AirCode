package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BlockDatesInquiryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BlockDatesInquiryFragment_ObservableResubscriber(BlockDatesInquiryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.blockDatesListener, "BlockDatesInquiryFragment_blockDatesListener");
        group.resubscribeAll(target.blockDatesListener);
    }
}
