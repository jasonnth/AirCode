package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.generated.GenListingRegistrationProcess;
import java.util.List;

public class ListingRegistrationProcess extends GenListingRegistrationProcess {
    public static final Creator<ListingRegistrationProcess> CREATOR = new Creator<ListingRegistrationProcess>() {
        public ListingRegistrationProcess[] newArray(int size) {
            return new ListingRegistrationProcess[size];
        }

        public ListingRegistrationProcess createFromParcel(Parcel source) {
            ListingRegistrationProcess object = new ListingRegistrationProcess();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String REGULATORY_BODY_CHICAGO = "chicago";
    private static final String REGULATORY_BODY_NOLA = "new_orleans";
    private static final String REGULATORY_BODY_SF = "san_francisco";

    public boolean isRegulatoryBodySupported() {
        return (isRegulatoryBodyChicago() && FeatureToggles.showListingRegistrationChicago()) || (isRegulatoryBodyNOLA() && FeatureToggles.showListingRegistrationNOLA()) || (isRegulatoryBodySF() && FeatureToggles.showListingRegistrationSF());
    }

    public boolean isRegulatoryBodyChicago() {
        return getRegulatoryBody().equals(REGULATORY_BODY_CHICAGO);
    }

    public boolean isRegulatoryBodyNOLA() {
        return getRegulatoryBody().equals(REGULATORY_BODY_NOLA);
    }

    public boolean isRegulatoryBodySF() {
        return getRegulatoryBody().equals(REGULATORY_BODY_SF);
    }

    public ListingRegistrationProcessInputGroup getInputGroupFromIndex(int groupIndex) {
        if (groupIndex < 0) {
            return null;
        }
        List<ListingRegistrationProcessInputGroup> groups = getInputGroups();
        if (groups == null || groups.size() <= groupIndex) {
            return null;
        }
        return (ListingRegistrationProcessInputGroup) groups.get(groupIndex);
    }

    public int indexOfInputGroup(String groupId) {
        if (groupId == null) {
            return -1;
        }
        List<ListingRegistrationProcessInputGroup> groups = getInputGroups();
        for (int i = 0; i < groups.size(); i++) {
            if (((ListingRegistrationProcessInputGroup) groups.get(i)).getGroupId() == groupId) {
                return i;
            }
        }
        return -1;
    }
}
