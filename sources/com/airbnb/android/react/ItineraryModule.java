package com.airbnb.android.react;

import android.text.TextUtils;
import com.airbnb.android.core.itinerary.ItineraryManager;
import com.airbnb.android.core.itinerary.ItineraryTripEventDataChangedListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;

public class ItineraryModule extends VersionedReactModuleBase implements ItineraryTripEventDataChangedListener {
    private static final int VERSION = 1;
    private final ItineraryManager itineraryManager;

    public ItineraryModule(ReactApplicationContext reactContext, ItineraryManager itineraryManager2) {
        super(reactContext, 1);
        this.itineraryManager = itineraryManager2;
        this.itineraryManager.setListener(this);
    }

    public String getName() {
        return "ItineraryBridge";
    }

    @ReactMethod
    public void onPlaceRemoved(int id) {
        this.itineraryManager.onPlaceRemoved((long) id);
    }

    @ReactMethod
    public void fetchHomeReservation(String id, boolean isPending) {
        this.itineraryManager.fetchHomeReservation(id, isPending);
    }

    @ReactMethod
    public void fetchPlaceReservation(String id) {
        this.itineraryManager.fetchPlaceReservation(id);
    }

    @ReactMethod
    public void fetchExperienceReservation(String id) {
        this.itineraryManager.fetchExperienceReservation(id);
    }

    public void updateTripEventContent(String id, String reservation, String errorMessage) {
        if (TextUtils.isEmpty(reservation)) {
            ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), String.format("onTripEventDataFailed:%s", new Object[]{id}), errorMessage);
            return;
        }
        ReactNativeUtils.maybeEmitEvent(getReactApplicationContext(), String.format("onTripEventDataChanged:%s", new Object[]{id}), reservation);
    }
}
