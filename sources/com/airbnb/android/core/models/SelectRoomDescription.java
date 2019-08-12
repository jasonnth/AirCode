package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectRoomDescription;

public class SelectRoomDescription extends GenSelectRoomDescription {
    public static final Creator<SelectRoomDescription> CREATOR = new Creator<SelectRoomDescription>() {
        public SelectRoomDescription[] newArray(int size) {
            return new SelectRoomDescription[size];
        }

        public SelectRoomDescription createFromParcel(Parcel source) {
            SelectRoomDescription object = new SelectRoomDescription();
            object.readFromParcel(source);
            return object;
        }
    };
}
