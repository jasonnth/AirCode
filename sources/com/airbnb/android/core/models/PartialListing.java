package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPartialListing;

public class PartialListing extends GenPartialListing {
    public static final Creator<PartialListing> CREATOR = new Creator<PartialListing>() {
        public PartialListing[] newArray(int size) {
            return new PartialListing[size];
        }

        public PartialListing createFromParcel(Parcel source) {
            PartialListing object = new PartialListing();
            object.readFromParcel(source);
            return object;
        }
    };
}
