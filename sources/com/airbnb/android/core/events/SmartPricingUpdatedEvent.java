package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Listing;

public class SmartPricingUpdatedEvent {
    public final Listing listing;

    public SmartPricingUpdatedEvent(Listing listing2) {
        this.listing = listing2;
    }
}
