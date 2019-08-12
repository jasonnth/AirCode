package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchMetaData;

public class SearchMetaData extends GenSearchMetaData {
    public static final Creator<SearchMetaData> CREATOR = new Creator<SearchMetaData>() {
        public SearchMetaData[] newArray(int size) {
            return new SearchMetaData[size];
        }

        public SearchMetaData createFromParcel(Parcel source) {
            SearchMetaData object = new SearchMetaData();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean isPrecisionBroaderThanCity() {
        return getGeography() == null || getGeography().isPrecisionBroaderThanCity();
    }

    public boolean isIbOverrideOn() {
        return getOverrides() != null && getOverrides().isIb();
    }

    public boolean hasFacet() {
        return this.mFacets != null;
    }

    public boolean hasGeography() {
        return this.mGeography != null;
    }
}
