package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationContentObject;

public class ListingRegistrationContentObject extends GenListingRegistrationContentObject {
    public static final Creator<ListingRegistrationContentObject> CREATOR = new Creator<ListingRegistrationContentObject>() {
        public ListingRegistrationContentObject[] newArray(int size) {
            return new ListingRegistrationContentObject[size];
        }

        public ListingRegistrationContentObject createFromParcel(Parcel source) {
            ListingRegistrationContentObject object = new ListingRegistrationContentObject();
            object.readFromParcel(source);
            return object;
        }
    };
}
