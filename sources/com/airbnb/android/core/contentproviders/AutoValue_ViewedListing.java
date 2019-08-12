package com.airbnb.android.core.contentproviders;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_ViewedListing extends C$AutoValue_ViewedListing {
    public static final Creator<AutoValue_ViewedListing> CREATOR = new Creator<AutoValue_ViewedListing>() {
        public AutoValue_ViewedListing createFromParcel(Parcel in) {
            return new AutoValue_ViewedListing(in.readLong(), in.readLong());
        }

        public AutoValue_ViewedListing[] newArray(int size) {
            return new AutoValue_ViewedListing[size];
        }
    };

    AutoValue_ViewedListing(long listingId, long timestamp) {
        super(listingId, timestamp);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getListingId());
        dest.writeLong(getTimestamp());
    }

    public int describeContents() {
        return 0;
    }
}
