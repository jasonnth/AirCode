package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCity;

public class City extends GenCity {
    public static final Creator<City> CREATOR = new Creator<City>() {
        public City[] newArray(int size) {
            return new City[size];
        }

        public City createFromParcel(Parcel source) {
            City object = new City();
            object.readFromParcel(source);
            return object;
        }
    };
}
