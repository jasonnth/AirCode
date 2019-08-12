package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationExemptionFields;

public class ListingRegistrationExemptionFields extends GenListingRegistrationExemptionFields {
    public static final Creator<ListingRegistrationExemptionFields> CREATOR = new Creator<ListingRegistrationExemptionFields>() {
        public ListingRegistrationExemptionFields[] newArray(int size) {
            return new ListingRegistrationExemptionFields[size];
        }

        public ListingRegistrationExemptionFields createFromParcel(Parcel source) {
            ListingRegistrationExemptionFields object = new ListingRegistrationExemptionFields();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Field {
        expiration_date,
        permit_number,
        zipcode
    }
}
