package com.airbnb.android.lib.tripassistant;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HelpThreadFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HelpThreadFragment_ObservableResubscriber(HelpThreadFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.eligibleTripAssistantReservationRequestListener, "HelpThreadFragment_eligibleTripAssistantReservationRequestListener");
        group.resubscribeAll(target.eligibleTripAssistantReservationRequestListener);
        setTag((AutoTaggableObserver) target.threadRequestListener, "HelpThreadFragment_threadRequestListener");
        group.resubscribeAll(target.threadRequestListener);
    }
}
