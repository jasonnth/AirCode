package com.airbnb.android.insights;

import android.os.Bundle;
import com.airbnb.android.insights.InsightsDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class InsightsDataController$$Icepick<T extends InsightsDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9340H = new Helper("com.airbnb.android.insights.InsightsDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.allListings = f9340H.getParcelableArrayList(state, "allListings");
            target.retrievedListingsId = (HashSet) f9340H.getSerializable(state, "retrievedListingsId");
            target.firstListingPosition = f9340H.getInt(state, "firstListingPosition");
            target.currentListingOffset = f9340H.getInt(state, "currentListingOffset");
            target.singleInsightOnly = f9340H.getBoolean(state, "singleInsightOnly");
            target.calendarDays = (HashMap) f9340H.getSerializable(state, "calendarDays");
            target.insightIdToSmartPromoId = (HashMap) f9340H.getSerializable(state, "insightIdToSmartPromoId");
            target.averagePrices = (HashMap) f9340H.getSerializable(state, "averagePrices");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9340H.putParcelableArrayList(state, "allListings", target.allListings);
        f9340H.putSerializable(state, "retrievedListingsId", target.retrievedListingsId);
        f9340H.putInt(state, "firstListingPosition", target.firstListingPosition);
        f9340H.putInt(state, "currentListingOffset", target.currentListingOffset);
        f9340H.putBoolean(state, "singleInsightOnly", target.singleInsightOnly);
        f9340H.putSerializable(state, "calendarDays", target.calendarDays);
        f9340H.putSerializable(state, "insightIdToSmartPromoId", target.insightIdToSmartPromoId);
        f9340H.putSerializable(state, "averagePrices", target.averagePrices);
    }
}
