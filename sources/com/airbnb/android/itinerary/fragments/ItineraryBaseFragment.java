package com.airbnb.android.itinerary.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.itinerary.controllers.ItineraryControllerInterface;
import com.airbnb.android.itinerary.controllers.ItineraryDataController;
import com.airbnb.android.itinerary.controllers.ItineraryJitneyLogger;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.controllers.ItineraryPerformanceAnalytics;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import com.airbnb.android.itinerary.listeners.ItineraryDataChangedListener;

public class ItineraryBaseFragment extends AirFragment implements ItineraryDataChangedListener {
    public static final int LOADING_VIEW_DELAY_MS = 100;
    public static final String TAG = "ItineraryBaseFragment";
    public ItineraryDataController itineraryDataController;
    public ItineraryJitneyLogger itineraryJitneyLogger;
    public ItineraryNavigationController itineraryNavigationController;
    public ItineraryPerformanceAnalytics itineraryPerformanceAnalytics;
    public ItineraryTableOpenHelper itineraryTableOpenHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.itineraryJitneyLogger = ((ItineraryControllerInterface) getParentFragment()).getJitneyLogger();
        this.itineraryPerformanceAnalytics = ((ItineraryControllerInterface) getParentFragment()).getPerformanceAnalytics();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.itineraryDataController = ((ItineraryControllerInterface) getParentFragment()).getDataController();
        this.itineraryNavigationController = ((ItineraryControllerInterface) getParentFragment()).getNavigationController();
        this.itineraryTableOpenHelper = ((ItineraryControllerInterface) getParentFragment()).getTableOpenHelper();
        if (this.itineraryJitneyLogger == null) {
            this.itineraryJitneyLogger = ((ItineraryControllerInterface) getParentFragment()).getJitneyLogger();
        }
        if (this.itineraryPerformanceAnalytics == null) {
            this.itineraryPerformanceAnalytics = ((ItineraryControllerInterface) getParentFragment()).getPerformanceAnalytics();
        }
    }

    public void onStart() {
        super.onStart();
        this.itineraryDataController.addDataChangedListener(this);
    }

    public void onStop() {
        super.onStop();
        this.itineraryDataController.removeDataChangedListener(this);
    }

    public void onTimelineContentUpdated(boolean hasNewContent) {
    }

    public void onPendingContentUpdated() {
    }

    public void onTripContentUpdated() {
    }

    public void onReservationObjectContentUpdated(String reservation, String errorMessage) {
    }
}
