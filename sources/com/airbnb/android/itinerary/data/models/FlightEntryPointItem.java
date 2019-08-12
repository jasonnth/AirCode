package com.airbnb.android.itinerary.data.models;

import com.airbnb.android.airdate.AirDateTime;

public abstract class FlightEntryPointItem implements BaseItineraryItem {

    public static abstract class Builder {
        public abstract Builder acceptText(String str);

        public abstract FlightEntryPointItem build();

        public abstract Builder dismissText(String str);

        /* renamed from: id */
        public abstract Builder mo57135id(String str);

        public abstract Builder title(String str);
    }

    public abstract String acceptText();

    public abstract String dismissText();

    /* renamed from: id */
    public abstract String mo57128id();

    public abstract String title();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }

    public static FlightEntryPointItem getDefaultFlightEntryPointItem() {
        return builder().mo57135id("flight").title("Do you want to add your flights to your itinerary?").acceptText("Yes").dismissText("No").build();
    }

    public AirDateTime getStartsAt() {
        return AirDateTime.now();
    }

    public AirDateTime getEndsAt() {
        return null;
    }

    public String getId() {
        return mo57128id();
    }
}
