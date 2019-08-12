package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMario;

public class Mario extends GenMario {
    public static final Creator<Mario> CREATOR = new Creator<Mario>() {
        public Mario[] newArray(int size) {
            return new Mario[size];
        }

        public Mario createFromParcel(Parcel source) {
            Mario object = new Mario();
            object.readFromParcel(source);
            return object;
        }
    };
}
