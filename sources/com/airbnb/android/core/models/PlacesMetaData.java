package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPlacesMetaData;

public class PlacesMetaData extends GenPlacesMetaData {
    public static final Creator<PlacesMetaData> CREATOR = new Creator<PlacesMetaData>() {
        public PlacesMetaData[] newArray(int size) {
            return new PlacesMetaData[size];
        }

        public PlacesMetaData createFromParcel(Parcel source) {
            PlacesMetaData object = new PlacesMetaData();
            object.readFromParcel(source);
            return object;
        }
    };
}
