package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenForYouMetaData;

public class ForYouMetaData extends GenForYouMetaData {
    public static final Creator<ForYouMetaData> CREATOR = new Creator<ForYouMetaData>() {
        public ForYouMetaData[] newArray(int size) {
            return new ForYouMetaData[size];
        }

        public ForYouMetaData createFromParcel(Parcel source) {
            ForYouMetaData object = new ForYouMetaData();
            object.readFromParcel(source);
            return object;
        }
    };
}
