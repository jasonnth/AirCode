package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.core.models.Listing;
import java.util.List;

public interface HostStatsInterface {
    void addListingsToCache(List<Listing> list);

    Listing getFirstListedListing();

    Bundle getListingSelectorBundle();

    boolean shouldEnableListingSelector();

    void showListingSelector(HostListingSelectorCallback hostListingSelectorCallback, boolean z);

    boolean userHasOnlyOneListedListing();
}
