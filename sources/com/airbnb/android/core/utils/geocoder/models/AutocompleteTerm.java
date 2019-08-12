package com.airbnb.android.core.utils.geocoder.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.geocoder.models.generated.GenAutocompleteTerm;

public class AutocompleteTerm extends GenAutocompleteTerm {
    public static final Creator<AutocompleteTerm> CREATOR = new Creator<AutocompleteTerm>() {
        public AutocompleteTerm[] newArray(int size) {
            return new AutocompleteTerm[size];
        }

        public AutocompleteTerm createFromParcel(Parcel source) {
            AutocompleteTerm object = new AutocompleteTerm();
            object.readFromParcel(source);
            return object;
        }
    };
}
