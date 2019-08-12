package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.core.models.CohostingStandard;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.HostEarnings;
import com.airbnb.android.core.models.HostStandardMetrics;
import com.airbnb.android.core.models.SuperhostData;
import com.airbnb.android.lib.host.stats.HostStatsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostStatsFragment$$Icepick<T extends HostStatsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9598H = new Helper("com.airbnb.android.lib.host.stats.HostStatsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.earningsForThisMonth = (HostEarnings) f9598H.getParcelable(state, "earningsForThisMonth");
            target.superhostData = (SuperhostData) f9598H.getParcelable(state, "superhostData");
            target.hostStandardMetrics = (HostStandardMetrics) f9598H.getParcelable(state, "hostStandardMetrics");
            target.cohostingStandard = (CohostingStandard) f9598H.getParcelable(state, "cohostingStandard");
            target.listings = f9598H.getParcelableArrayList(state, "listings");
            target.insights = f9598H.getParcelableArrayList(state, "insights");
            target.currentUserAverageRating = f9598H.getDouble(state, "currentUserAverageRating");
            target.similarHostAverageRating = f9598H.getBoxedDouble(state, "similarHostAverageRating");
            target.totalListingViewsCount = f9598H.getInt(state, "totalListingViewsCount");
            target.newBookings = f9598H.getInt(state, "newBookings");
            target.totalPaidOutThisYear = (CurrencyAmount) f9598H.getParcelable(state, "totalPaidOutThisYear");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9598H.putParcelable(state, "earningsForThisMonth", target.earningsForThisMonth);
        f9598H.putParcelable(state, "superhostData", target.superhostData);
        f9598H.putParcelable(state, "hostStandardMetrics", target.hostStandardMetrics);
        f9598H.putParcelable(state, "cohostingStandard", target.cohostingStandard);
        f9598H.putParcelableArrayList(state, "listings", target.listings);
        f9598H.putParcelableArrayList(state, "insights", target.insights);
        f9598H.putDouble(state, "currentUserAverageRating", target.currentUserAverageRating);
        f9598H.putBoxedDouble(state, "similarHostAverageRating", target.similarHostAverageRating);
        f9598H.putInt(state, "totalListingViewsCount", target.totalListingViewsCount);
        f9598H.putInt(state, "newBookings", target.newBookings);
        f9598H.putParcelable(state, "totalPaidOutThisYear", target.totalPaidOutThisYear);
    }
}
