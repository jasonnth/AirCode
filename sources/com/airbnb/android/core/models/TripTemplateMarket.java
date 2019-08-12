package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTripTemplateMarket;

public class TripTemplateMarket extends GenTripTemplateMarket {
    public static final Creator<TripTemplateMarket> CREATOR = new Creator<TripTemplateMarket>() {
        public TripTemplateMarket[] newArray(int size) {
            return new TripTemplateMarket[size];
        }

        public TripTemplateMarket createFromParcel(Parcel source) {
            TripTemplateMarket object = new TripTemplateMarket();
            object.readFromParcel(source);
            return object;
        }
    };
}
