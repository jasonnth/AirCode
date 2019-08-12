package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRegistrationHelpLink;

public class ListingRegistrationHelpLink extends GenListingRegistrationHelpLink {
    public static final Creator<ListingRegistrationHelpLink> CREATOR = new Creator<ListingRegistrationHelpLink>() {
        public ListingRegistrationHelpLink[] newArray(int size) {
            return new ListingRegistrationHelpLink[size];
        }

        public ListingRegistrationHelpLink createFromParcel(Parcel source) {
            ListingRegistrationHelpLink object = new ListingRegistrationHelpLink();
            object.readFromParcel(source);
            return object;
        }
    };
}
