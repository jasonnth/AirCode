package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenActionCardCopy;

public class ActionCardCopy extends GenActionCardCopy {
    public static final Creator<ActionCardCopy> CREATOR = new Creator<ActionCardCopy>() {
        public ActionCardCopy[] newArray(int size) {
            return new ActionCardCopy[size];
        }

        public ActionCardCopy createFromParcel(Parcel source) {
            ActionCardCopy object = new ActionCardCopy();
            object.readFromParcel(source);
            return object;
        }
    };
}
