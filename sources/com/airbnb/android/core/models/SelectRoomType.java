package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectRoomType;

public class SelectRoomType extends GenSelectRoomType {
    public static final Creator<SelectRoomType> CREATOR = new Creator<SelectRoomType>() {
        public SelectRoomType[] newArray(int size) {
            return new SelectRoomType[size];
        }

        public SelectRoomType createFromParcel(Parcel source) {
            SelectRoomType object = new SelectRoomType();
            object.readFromParcel(source);
            return object;
        }
    };
}
