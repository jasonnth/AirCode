package com.airbnb.android.lib.cancellation;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DLSCancelReservationSummaryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DLSCancelReservationSummaryFragment_ObservableResubscriber(DLSCancelReservationSummaryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.breakdownListener, "DLSCancelReservationSummaryFragment_breakdownListener");
        group.resubscribeAll(target.breakdownListener);
    }
}
