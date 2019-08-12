package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMarket;

public class Market extends GenMarket {
    public static final Creator<Market> CREATOR = new Creator<Market>() {
        public Market[] newArray(int size) {
            return new Market[size];
        }

        public Market createFromParcel(Parcel source) {
            Market object = new Market();
            object.readFromParcel(source);
            return object;
        }
    };
}
