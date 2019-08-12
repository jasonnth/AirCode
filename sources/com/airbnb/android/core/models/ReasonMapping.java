package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReasonMapping;

public class ReasonMapping extends GenReasonMapping {
    public static final Creator<ReasonMapping> CREATOR = new Creator<ReasonMapping>() {
        public ReasonMapping[] newArray(int size) {
            return new ReasonMapping[size];
        }

        public ReasonMapping createFromParcel(Parcel source) {
            ReasonMapping object = new ReasonMapping();
            object.readFromParcel(source);
            return object;
        }
    };
}
