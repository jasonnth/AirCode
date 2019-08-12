package com.airbnb.android.core.utils.listing;

import com.airbnb.android.core.models.Listing;

public enum ListedStatus {
    Listed,
    Snoozed,
    Incomplete,
    Unlisted;

    public static ListedStatus calculate(Listing listing) {
        if (!listing.hasBeenListed()) {
            return Incomplete;
        }
        if (listing.hasAvailability()) {
            return Listed;
        }
        if (listing.isSnoozed()) {
            return Snoozed;
        }
        return Unlisted;
    }
}
