package com.airbnb.android.lib.cancellation.host;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CancellationOverviewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CancellationOverviewFragment_ObservableResubscriber(CancellationOverviewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.deleteReservationRequestListener, "CancellationOverviewFragment_deleteReservationRequestListener");
        group.resubscribeAll(target.deleteReservationRequestListener);
    }
}
