package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchResult;

public class SearchResult extends GenSearchResult implements Mappable {
    public static final Creator<SearchResult> CREATOR = new Creator<SearchResult>() {
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }

        public SearchResult createFromParcel(Parcel source) {
            SearchResult object = new SearchResult();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mListing.equals(((SearchResult) o).mListing);
    }

    public int hashCode() {
        return this.mListing.hashCode();
    }

    public long getMappableId() {
        return this.mListing.getId();
    }

    public double getLatitude() {
        return getListing().getLatitude();
    }

    public double getLongitude() {
        return getListing().getLongitude();
    }
}
