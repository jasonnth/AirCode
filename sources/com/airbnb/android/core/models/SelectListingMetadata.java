package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectListingMetadata;

public class SelectListingMetadata extends GenSelectListingMetadata {
    public static final Creator<SelectListingMetadata> CREATOR = new Creator<SelectListingMetadata>() {
        public SelectListingMetadata[] newArray(int size) {
            return new SelectListingMetadata[size];
        }

        public SelectListingMetadata createFromParcel(Parcel source) {
            SelectListingMetadata object = new SelectListingMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
