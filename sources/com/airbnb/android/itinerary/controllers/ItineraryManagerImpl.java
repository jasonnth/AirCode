package com.airbnb.android.itinerary.controllers;

import android.text.TextUtils;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.itinerary.ItineraryTripEventDataChangedListener;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.itinerary.listeners.ItineraryDataChangedListener;

public class ItineraryManagerImpl implements ItineraryManager, ItineraryDataChangedListener {
    private final ItineraryDataController itineraryDataController;
    private final ItineraryTableOpenHelper itineraryTableOpenHelper;
    private ItineraryTripEventDataChangedListener listener;

    public ItineraryManagerImpl(ItineraryTableOpenHelper itineraryTableOpenHelper2, ItineraryDataController itineraryDataController2) {
        this.itineraryTableOpenHelper = itineraryTableOpenHelper2;
        this.itineraryDataController = itineraryDataController2;
        this.itineraryDataController.addDataChangedListener(this);
    }

    public void fetchHomeReservation(String id, boolean isPending) {
        if (isPending) {
            this.itineraryDataController.fetchPendingHomeReservation(id);
        } else {
            this.itineraryDataController.fetchHomeReservation(id, true);
        }
    }

    public void fetchPlaceReservation(String id) {
        this.itineraryDataController.fetchPlaceReservation(id, true);
    }

    public void fetchExperienceReservation(String id) {
        this.itineraryDataController.fetchExperienceReservation(id, true);
    }

    public void onPlaceRemoved(long id) {
        this.itineraryTableOpenHelper.deleteTripEvent(id);
    }

    public void onClearAll() {
        this.itineraryTableOpenHelper.clearAll();
    }

    public void setListener(ItineraryTripEventDataChangedListener listener2) {
        this.listener = listener2;
    }

    public void onTimelineContentUpdated(boolean hasNewContent) {
    }

    public void onPendingContentUpdated() {
    }

    public void onTripContentUpdated() {
    }

    public void onReservationObjectContentUpdated(String reservation, String errorMessage) {
        String id = this.itineraryDataController.getReservationObjectId();
        if (!TextUtils.isEmpty(id) && this.listener != null) {
            this.listener.updateTripEventContent(id, reservation, errorMessage);
        }
    }
}
