package com.airbnb.android.places.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class PlaceActivityPDPFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public PlaceActivityPDPFragment_ObservableResubscriber(PlaceActivityPDPFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.placeActivityRequestListener, "PlaceActivityPDPFragment_placeActivityRequestListener");
        group.resubscribeAll(target.placeActivityRequestListener);
        setTag((AutoTaggableObserver) target.meetupActivityRequestListener, "PlaceActivityPDPFragment_meetupActivityRequestListener");
        group.resubscribeAll(target.meetupActivityRequestListener);
        setTag((AutoTaggableObserver) target.reservationRequestListener, "PlaceActivityPDPFragment_reservationRequestListener");
        group.resubscribeAll(target.reservationRequestListener);
        setTag((AutoTaggableObserver) target.joinMeetupRequestListener, "PlaceActivityPDPFragment_joinMeetupRequestListener");
        group.resubscribeAll(target.joinMeetupRequestListener);
    }
}
