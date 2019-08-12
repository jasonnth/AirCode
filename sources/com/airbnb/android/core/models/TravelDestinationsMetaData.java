package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTravelDestinationsMetaData;

public class TravelDestinationsMetaData extends GenTravelDestinationsMetaData {
    public static final Creator<TravelDestinationsMetaData> CREATOR = new Creator<TravelDestinationsMetaData>() {
        public TravelDestinationsMetaData[] newArray(int size) {
            return new TravelDestinationsMetaData[size];
        }

        public TravelDestinationsMetaData createFromParcel(Parcel source) {
            TravelDestinationsMetaData object = new TravelDestinationsMetaData();
            object.readFromParcel(source);
            return object;
        }
    };
}
