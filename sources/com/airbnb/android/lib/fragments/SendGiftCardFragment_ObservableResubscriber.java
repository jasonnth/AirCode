package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class SendGiftCardFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public SendGiftCardFragment_ObservableResubscriber(SendGiftCardFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.templateRequestListener, "SendGiftCardFragment_templateRequestListener");
        group.resubscribeAll(target.templateRequestListener);
        setTag((AutoTaggableObserver) target.createRequestListener, "SendGiftCardFragment_createRequestListener");
        group.resubscribeAll(target.createRequestListener);
    }
}
