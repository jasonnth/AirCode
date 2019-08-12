package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectListing;

public class SelectListing extends GenSelectListing {
    public static final Creator<SelectListing> CREATOR = new Creator<SelectListing>() {
        public SelectListing[] newArray(int size) {
            return new SelectListing[size];
        }

        public SelectListing createFromParcel(Parcel source) {
            SelectListing object = new SelectListing();
            object.readFromParcel(source);
            return object;
        }
    };
}
