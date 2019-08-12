package com.airbnb.android.itinerary.listeners;

public interface ItineraryDataChangedListener {
    void onPendingContentUpdated();

    void onReservationObjectContentUpdated(String str, String str2);

    void onTimelineContentUpdated(boolean z);

    void onTripContentUpdated();
}
