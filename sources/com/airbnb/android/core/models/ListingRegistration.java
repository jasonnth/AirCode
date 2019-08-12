package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.ListingRegistrationStatus;
import com.airbnb.android.core.models.generated.GenListingRegistration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListingRegistration extends GenListingRegistration {
    public static final Creator<ListingRegistration> CREATOR = new Creator<ListingRegistration>() {
        public ListingRegistration[] newArray(int size) {
            return new ListingRegistration[size];
        }

        public ListingRegistration createFromParcel(Parcel source) {
            ListingRegistration object = new ListingRegistration();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("status")
    public void setStatus(String status) {
        super.setStatus(ListingRegistrationStatus.fromKey(status));
    }
}
