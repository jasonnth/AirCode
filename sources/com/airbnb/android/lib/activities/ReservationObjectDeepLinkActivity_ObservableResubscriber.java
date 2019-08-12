package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReservationObjectDeepLinkActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ReservationObjectDeepLinkActivity_ObservableResubscriber(ReservationObjectDeepLinkActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.savedMessagesRequestListener, "ReservationObjectDeepLinkActivity_savedMessagesRequestListener");
        group.resubscribeAll(target.savedMessagesRequestListener);
    }
}
