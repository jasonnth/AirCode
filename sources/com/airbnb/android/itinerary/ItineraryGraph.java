package com.airbnb.android.itinerary;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment;

public interface ItineraryGraph extends BaseGraph {
    void inject(ItineraryParentFragment itineraryParentFragment);
}
