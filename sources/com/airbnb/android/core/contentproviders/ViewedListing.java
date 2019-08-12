package com.airbnb.android.core.contentproviders;

import android.os.Parcelable;

public abstract class ViewedListing implements Parcelable {
    public abstract long getListingId();

    public abstract long getTimestamp();

    public static ViewedListing create(long listingId, long timeStamp) {
        return new AutoValue_ViewedListing(listingId, timeStamp);
    }
}
