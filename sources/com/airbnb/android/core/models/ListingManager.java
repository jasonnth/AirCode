package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingManager;

public class ListingManager extends GenListingManager {
    public static final Creator<ListingManager> CREATOR = new Creator<ListingManager>() {
        public ListingManager[] newArray(int size) {
            return new ListingManager[size];
        }

        public ListingManager createFromParcel(Parcel source) {
            ListingManager object = new ListingManager();
            object.readFromParcel(source);
            return object;
        }
    };
}
