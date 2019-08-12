package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationExemptionData;

public class ListingRegistrationExemptionData extends GenListingRegistrationExemptionData {
    public static final Creator<ListingRegistrationExemptionData> CREATOR = new Creator<ListingRegistrationExemptionData>() {
        public ListingRegistrationExemptionData[] newArray(int size) {
            return new ListingRegistrationExemptionData[size];
        }

        public ListingRegistrationExemptionData createFromParcel(Parcel source) {
            ListingRegistrationExemptionData object = new ListingRegistrationExemptionData();
            object.readFromParcel(source);
            return object;
        }
    };
}
