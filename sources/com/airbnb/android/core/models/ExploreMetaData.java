package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExploreMetaData;

public class ExploreMetaData extends GenExploreMetaData {
    public static final Creator<ExploreMetaData> CREATOR = new Creator<ExploreMetaData>() {
        public ExploreMetaData[] newArray(int size) {
            return new ExploreMetaData[size];
        }

        public ExploreMetaData createFromParcel(Parcel source) {
            ExploreMetaData object = new ExploreMetaData();
            object.readFromParcel(source);
            return object;
        }
    };
}
