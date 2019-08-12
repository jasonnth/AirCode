package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingAmenityInformation;

public class ListingAmenityInformation extends GenListingAmenityInformation {
    public static final Creator<ListingAmenityInformation> CREATOR = new Creator<ListingAmenityInformation>() {
        public ListingAmenityInformation[] newArray(int size) {
            return new ListingAmenityInformation[size];
        }

        public ListingAmenityInformation createFromParcel(Parcel source) {
            ListingAmenityInformation object = new ListingAmenityInformation();
            object.readFromParcel(source);
            return object;
        }
    };
}
