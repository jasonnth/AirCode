package com.airbnb.android.core.itinerary;

public interface ItineraryManager {
    void fetchExperienceReservation(String str);

    void fetchHomeReservation(String str, boolean z);

    void fetchPlaceReservation(String str);

    void onClearAll();

    void onPlaceRemoved(long j);

    void setListener(ItineraryTripEventDataChangedListener itineraryTripEventDataChangedListener);
}
