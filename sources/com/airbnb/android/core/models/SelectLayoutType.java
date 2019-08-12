package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectLayoutType;

public class SelectLayoutType extends GenSelectLayoutType {
    public static final Creator<SelectLayoutType> CREATOR = new Creator<SelectLayoutType>() {
        public SelectLayoutType[] newArray(int size) {
            return new SelectLayoutType[size];
        }

        public SelectLayoutType createFromParcel(Parcel source) {
            SelectLayoutType object = new SelectLayoutType();
            object.readFromParcel(source);
            return object;
        }
    };
}
