package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationContentSection;

public class ListingRegistrationContentSection extends GenListingRegistrationContentSection {
    public static final Creator<ListingRegistrationContentSection> CREATOR = new Creator<ListingRegistrationContentSection>() {
        public ListingRegistrationContentSection[] newArray(int size) {
            return new ListingRegistrationContentSection[size];
        }

        public ListingRegistrationContentSection createFromParcel(Parcel source) {
            ListingRegistrationContentSection object = new ListingRegistrationContentSection();
            object.readFromParcel(source);
            return object;
        }
    };
}
