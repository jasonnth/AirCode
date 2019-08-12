package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTrebuchet;

public class Trebuchet extends GenTrebuchet {
    public static final Creator<Trebuchet> CREATOR = new Creator<Trebuchet>() {
        public Trebuchet[] newArray(int size) {
            return new Trebuchet[size];
        }

        public Trebuchet createFromParcel(Parcel source) {
            Trebuchet object = new Trebuchet();
            object.readFromParcel(source);
            return object;
        }
    };

    public static Trebuchet create(String id, boolean launch) {
        Trebuchet trebuchet = new Trebuchet();
        trebuchet.setId(id);
        trebuchet.setLaunch(launch);
        return trebuchet;
    }
}
