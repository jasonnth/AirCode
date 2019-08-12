package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.Listing;

public interface HostListingSelectorCallback {
    boolean isListingSelectable(Listing listing);

    void onAllListingsSelected();

    void onListingSelected(Listing listing);
}
