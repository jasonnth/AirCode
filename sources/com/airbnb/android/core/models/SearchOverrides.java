package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchOverrides;

public class SearchOverrides extends GenSearchOverrides {
    public static final Creator<SearchOverrides> CREATOR = new Creator<SearchOverrides>() {
        public SearchOverrides[] newArray(int size) {
            return new SearchOverrides[size];
        }

        public SearchOverrides createFromParcel(Parcel source) {
            SearchOverrides object = new SearchOverrides();
            object.readFromParcel(source);
            return object;
        }
    };
}
