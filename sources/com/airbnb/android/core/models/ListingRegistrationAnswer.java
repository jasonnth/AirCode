package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationAnswer;

public class ListingRegistrationAnswer extends GenListingRegistrationAnswer {
    public static final Creator<ListingRegistrationAnswer> CREATOR = new Creator<ListingRegistrationAnswer>() {
        public ListingRegistrationAnswer[] newArray(int size) {
            return new ListingRegistrationAnswer[size];
        }

        public ListingRegistrationAnswer createFromParcel(Parcel source) {
            ListingRegistrationAnswer object = new ListingRegistrationAnswer();
            object.readFromParcel(source);
            return object;
        }
    };
}
