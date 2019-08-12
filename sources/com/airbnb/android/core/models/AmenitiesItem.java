package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAmenitiesItem;

public class AmenitiesItem extends GenAmenitiesItem {
    public static final Creator<AmenitiesItem> CREATOR = new Creator<AmenitiesItem>() {
        public AmenitiesItem[] newArray(int size) {
            return new AmenitiesItem[size];
        }

        public AmenitiesItem createFromParcel(Parcel source) {
            AmenitiesItem object = new AmenitiesItem();
            object.readFromParcel(source);
            return object;
        }
    };

    public AmenitiesItem() {
    }

    public AmenitiesItem(Amenity amenity, boolean enabled) {
        super(amenity, enabled);
    }

    public void toggleEnabled() {
        this.mEnabled = !this.mEnabled;
    }
}
