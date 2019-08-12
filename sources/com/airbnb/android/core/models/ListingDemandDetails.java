package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingDemandDetails;

public class ListingDemandDetails extends GenListingDemandDetails {
    public static final Creator<ListingDemandDetails> CREATOR = new Creator<ListingDemandDetails>() {
        public ListingDemandDetails[] newArray(int size) {
            return new ListingDemandDetails[size];
        }

        public ListingDemandDetails createFromParcel(Parcel source) {
            ListingDemandDetails object = new ListingDemandDetails();
            object.readFromParcel(source);
            return object;
        }
    };
}
