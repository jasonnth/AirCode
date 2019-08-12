package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenHomesCollectionsApplicationsListing;

public class HomesCollectionsApplicationsListing extends GenHomesCollectionsApplicationsListing {
    public static final Creator<HomesCollectionsApplicationsListing> CREATOR = new Creator<HomesCollectionsApplicationsListing>() {
        public HomesCollectionsApplicationsListing[] newArray(int size) {
            return new HomesCollectionsApplicationsListing[size];
        }

        public HomesCollectionsApplicationsListing createFromParcel(Parcel source) {
            HomesCollectionsApplicationsListing object = new HomesCollectionsApplicationsListing();
            object.readFromParcel(source);
            return object;
        }
    };
}
