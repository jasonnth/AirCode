package com.airbnb.android.itinerary.controllers;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ItineraryDataController_ObservableResubscriber extends BaseObservableResubscriber {
    public ItineraryDataController_ObservableResubscriber(ItineraryDataController target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.timelineListener, "ItineraryDataController_timelineListener");
        group.resubscribeAll(target.timelineListener);
        setTag((AutoTaggableObserver) target.pendingTimelineListener, "ItineraryDataController_pendingTimelineListener");
        group.resubscribeAll(target.pendingTimelineListener);
        setTag((AutoTaggableObserver) target.tripScheduleCardsListener, "ItineraryDataController_tripScheduleCardsListener");
        group.resubscribeAll(target.tripScheduleCardsListener);
        setTag((AutoTaggableObserver) target.suggestionsListener, "ItineraryDataController_suggestionsListener");
        group.resubscribeAll(target.suggestionsListener);
    }
}
