package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingTranslatedDescription;

public class ListingTranslatedDescription extends GenListingTranslatedDescription {
    public static final Creator<ListingTranslatedDescription> CREATOR = new Creator<ListingTranslatedDescription>() {
        public ListingTranslatedDescription[] newArray(int size) {
            return new ListingTranslatedDescription[size];
        }

        public ListingTranslatedDescription createFromParcel(Parcel source) {
            ListingTranslatedDescription object = new ListingTranslatedDescription();
            object.readFromParcel(source);
            return object;
        }
    };
}
