package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPlaceActivityAttribute;

public class PlaceActivityAttribute extends GenPlaceActivityAttribute {
    public static final Creator<PlaceActivityAttribute> CREATOR = new Creator<PlaceActivityAttribute>() {
        public PlaceActivityAttribute[] newArray(int size) {
            return new PlaceActivityAttribute[size];
        }

        public PlaceActivityAttribute createFromParcel(Parcel source) {
            PlaceActivityAttribute object = new PlaceActivityAttribute();
            object.readFromParcel(source);
            return object;
        }
    };

    public int hashCode() {
        return getType().hashCode() << (getText().hashCode() + 16);
    }
}
