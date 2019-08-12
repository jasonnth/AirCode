package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExperiencesMetaData;

public class ExperiencesMetaData extends GenExperiencesMetaData {
    public static final Creator<ExperiencesMetaData> CREATOR = new Creator<ExperiencesMetaData>() {
        public ExperiencesMetaData[] newArray(int size) {
            return new ExperiencesMetaData[size];
        }

        public ExperiencesMetaData createFromParcel(Parcel source) {
            ExperiencesMetaData object = new ExperiencesMetaData();
            object.readFromParcel(source);
            return object;
        }
    };
}
