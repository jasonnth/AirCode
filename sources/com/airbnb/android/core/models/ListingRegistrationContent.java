package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationContent;

public class ListingRegistrationContent extends GenListingRegistrationContent {
    public static final Creator<ListingRegistrationContent> CREATOR = new Creator<ListingRegistrationContent>() {
        public ListingRegistrationContent[] newArray(int size) {
            return new ListingRegistrationContent[size];
        }

        public ListingRegistrationContent createFromParcel(Parcel source) {
            ListingRegistrationContent object = new ListingRegistrationContent();
            object.readFromParcel(source);
            return object;
        }
    };

    public int getHelpCenterArticle() {
        return getHelpCenterId();
    }
}
