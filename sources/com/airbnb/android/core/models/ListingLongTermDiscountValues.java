package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingLongTermDiscountValues;

public class ListingLongTermDiscountValues extends GenListingLongTermDiscountValues {
    public static final Creator<ListingLongTermDiscountValues> CREATOR = new Creator<ListingLongTermDiscountValues>() {
        public ListingLongTermDiscountValues[] newArray(int size) {
            return new ListingLongTermDiscountValues[size];
        }

        public ListingLongTermDiscountValues createFromParcel(Parcel source) {
            ListingLongTermDiscountValues object = new ListingLongTermDiscountValues();
            object.readFromParcel(source);
            return object;
        }
    };
}
