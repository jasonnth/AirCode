package com.airbnb.android.itinerary.controllers;

import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;

public interface ItineraryControllerInterface {
    ItineraryDataController getDataController();

    ItineraryJitneyLogger getJitneyLogger();

    ItineraryNavigationController getNavigationController();

    ItineraryPerformanceAnalytics getPerformanceAnalytics();

    ItineraryTableOpenHelper getTableOpenHelper();
}
