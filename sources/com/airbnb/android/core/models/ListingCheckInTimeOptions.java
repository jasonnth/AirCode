package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingCheckInTimeOptions;

public class ListingCheckInTimeOptions extends GenListingCheckInTimeOptions {
    public static final Creator<ListingCheckInTimeOptions> CREATOR = new Creator<ListingCheckInTimeOptions>() {
        public ListingCheckInTimeOptions[] newArray(int size) {
            return new ListingCheckInTimeOptions[size];
        }

        public ListingCheckInTimeOptions createFromParcel(Parcel source) {
            ListingCheckInTimeOptions object = new ListingCheckInTimeOptions();
            object.readFromParcel(source);
            return object;
        }
    };
}
