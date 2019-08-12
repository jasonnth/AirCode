package com.airbnb.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;

public class ParcelableStringMap extends HashMap<String, String> implements Parcelable {
    public static final Creator<ParcelableStringMap> CREATOR = new Creator<ParcelableStringMap>() {
        public ParcelableStringMap[] newArray(int size) {
            return new ParcelableStringMap[size];
        }

        public ParcelableStringMap createFromParcel(Parcel source) {
            ParcelableStringMap object = new ParcelableStringMap();
            object.readFromParcel(source);
            return object;
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelableUtils.writeStringMapToParcel(dest, this);
    }

    /* access modifiers changed from: private */
    public void readFromParcel(Parcel source) {
        ParcelableUtils.readStringMapFromParcel(source, this);
    }
}
