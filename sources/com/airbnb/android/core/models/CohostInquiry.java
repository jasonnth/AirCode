package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCohostInquiry;

public class CohostInquiry extends GenCohostInquiry {
    public static final Creator<CohostInquiry> CREATOR = new Creator<CohostInquiry>() {
        public CohostInquiry[] newArray(int size) {
            return new CohostInquiry[size];
        }

        public CohostInquiry createFromParcel(Parcel source) {
            CohostInquiry object = new CohostInquiry();
            object.readFromParcel(source);
            return object;
        }
    };
}
