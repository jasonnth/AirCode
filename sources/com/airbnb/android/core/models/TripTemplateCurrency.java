package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTripTemplateCurrency;

public class TripTemplateCurrency extends GenTripTemplateCurrency {
    public static final Creator<TripTemplateCurrency> CREATOR = new Creator<TripTemplateCurrency>() {
        public TripTemplateCurrency[] newArray(int size) {
            return new TripTemplateCurrency[size];
        }

        public TripTemplateCurrency createFromParcel(Parcel source) {
            TripTemplateCurrency object = new TripTemplateCurrency();
            object.readFromParcel(source);
            return object;
        }
    };
}
