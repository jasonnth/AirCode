package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPlaceActivityHours;

public class PlaceActivityHours extends GenPlaceActivityHours {
    public static final Creator<PlaceActivityHours> CREATOR = new Creator<PlaceActivityHours>() {
        public PlaceActivityHours[] newArray(int size) {
            return new PlaceActivityHours[size];
        }

        public PlaceActivityHours createFromParcel(Parcel source) {
            PlaceActivityHours object = new PlaceActivityHours();
            object.readFromParcel(source);
            return object;
        }
    };
}
