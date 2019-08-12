package com.airbnb.android.cohosting.utils;

import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext.Builder;
import java.util.List;

public class CohostingLoggingUtil {
    public static C1951CohostingContext getCohostingContext(Listing listing, List<ListingManager> listingManagers) {
        return new Builder(Long.valueOf(listing.getUserId()), Long.valueOf(listing.getId()), Long.valueOf(((long) listingManagers.size()) - 1), Long.valueOf(listing.getPrimaryHost().getId())).build();
    }
}
