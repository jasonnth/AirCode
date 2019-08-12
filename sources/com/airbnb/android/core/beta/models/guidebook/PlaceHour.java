package com.airbnb.android.core.beta.models.guidebook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.beta.models.guidebook.generated.GenPlaceHour;

public class PlaceHour extends GenPlaceHour {
    public static final Creator<PlaceHour> CREATOR = new Creator<PlaceHour>() {
        public PlaceHour[] newArray(int size) {
            return new PlaceHour[size];
        }

        public PlaceHour createFromParcel(Parcel source) {
            PlaceHour object = new PlaceHour();
            object.readFromParcel(source);
            return object;
        }
    };
}
