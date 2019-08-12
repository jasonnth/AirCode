package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationDateAnswer;

public class ListingRegistrationDateAnswer extends GenListingRegistrationDateAnswer {
    public static final Creator<ListingRegistrationDateAnswer> CREATOR = new Creator<ListingRegistrationDateAnswer>() {
        public ListingRegistrationDateAnswer[] newArray(int size) {
            return new ListingRegistrationDateAnswer[size];
        }

        public ListingRegistrationDateAnswer createFromParcel(Parcel source) {
            ListingRegistrationDateAnswer object = new ListingRegistrationDateAnswer();
            object.readFromParcel(source);
            return object;
        }
    };
}
