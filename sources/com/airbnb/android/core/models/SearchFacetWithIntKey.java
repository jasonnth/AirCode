package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchFacetWithIntKey;

public class SearchFacetWithIntKey extends GenSearchFacetWithIntKey {
    public static final Creator<SearchFacetWithIntKey> CREATOR = new Creator<SearchFacetWithIntKey>() {
        public SearchFacetWithIntKey[] newArray(int size) {
            return new SearchFacetWithIntKey[size];
        }

        public SearchFacetWithIntKey createFromParcel(Parcel source) {
            SearchFacetWithIntKey object = new SearchFacetWithIntKey();
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
        if (this.mKey != ((SearchFacetWithIntKey) o).mKey) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mKey;
    }
}
