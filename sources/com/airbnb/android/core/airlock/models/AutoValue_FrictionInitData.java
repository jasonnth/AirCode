package com.airbnb.android.core.airlock.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Map;

final class AutoValue_FrictionInitData extends C$AutoValue_FrictionInitData {
    public static final Creator<AutoValue_FrictionInitData> CREATOR = new Creator<AutoValue_FrictionInitData>() {
        public AutoValue_FrictionInitData createFromParcel(Parcel in) {
            return new AutoValue_FrictionInitData(in.readHashMap(BaseAirlockFriction.class.getClassLoader()));
        }

        public AutoValue_FrictionInitData[] newArray(int size) {
            return new AutoValue_FrictionInitData[size];
        }
    };

    AutoValue_FrictionInitData(Map<String, BaseAirlockFriction> frictionMap) {
        super(frictionMap);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(frictionMap());
    }

    public int describeContents() {
        return 0;
    }
}
