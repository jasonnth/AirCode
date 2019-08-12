package com.airbnb.android.itinerary.data.models;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;

public interface BaseItineraryItem extends Parcelable {
    AirDateTime getEndsAt();

    String getId();

    AirDateTime getStartsAt();
}
