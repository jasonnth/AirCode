package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingOccupancyInfo;

public class ListingOccupancyInfo extends GenListingOccupancyInfo {
    public static final Creator<ListingOccupancyInfo> CREATOR = new Creator<ListingOccupancyInfo>() {
        public ListingOccupancyInfo[] newArray(int size) {
            return new ListingOccupancyInfo[size];
        }

        public ListingOccupancyInfo createFromParcel(Parcel source) {
            ListingOccupancyInfo object = new ListingOccupancyInfo();
            object.readFromParcel(source);
            return object;
        }
    };
}
