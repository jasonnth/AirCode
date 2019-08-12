package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.lib.host.stats.HostReviewDetailsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostReviewDetailsFragment$$Icepick<T extends HostReviewDetailsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9596H = new Helper("com.airbnb.android.lib.host.stats.HostReviewDetailsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.listingsWithReviewScores = f9596H.getParcelableArrayList(state, "listingsWithReviewScores");
            target.listings = f9596H.getParcelableArrayList(state, "listings");
            target.aggregateRatingStatistics = f9596H.getParcelableArrayList(state, "aggregateRatingStatistics");
            target.similarHostAverageRating = f9596H.getBoxedDouble(state, "similarHostAverageRating");
            target.listingRatingAggregations = f9596H.getParcelableArrayList(state, "listingRatingAggregations");
            target.listingRatingBreakdownCache = (HashMap) f9596H.getSerializable(state, "listingRatingBreakdownCache");
            target.listingReviewsCache = (HashMap) f9596H.getSerializable(state, "listingReviewsCache");
            target.selectedListingId = f9596H.getLong(state, "selectedListingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9596H.putParcelableArrayList(state, "listingsWithReviewScores", target.listingsWithReviewScores);
        f9596H.putParcelableArrayList(state, "listings", target.listings);
        f9596H.putParcelableArrayList(state, "aggregateRatingStatistics", target.aggregateRatingStatistics);
        f9596H.putBoxedDouble(state, "similarHostAverageRating", target.similarHostAverageRating);
        f9596H.putParcelableArrayList(state, "listingRatingAggregations", target.listingRatingAggregations);
        f9596H.putSerializable(state, "listingRatingBreakdownCache", target.listingRatingBreakdownCache);
        f9596H.putSerializable(state, "listingReviewsCache", target.listingReviewsCache);
        f9596H.putLong(state, "selectedListingId", target.selectedListingId);
    }
}
