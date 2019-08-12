package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSearchFacetWithStringKey;

public class SearchFacetWithStringKey extends GenSearchFacetWithStringKey {
    public static final Creator<SearchFacetWithStringKey> CREATOR = new Creator<SearchFacetWithStringKey>() {
        public SearchFacetWithStringKey[] newArray(int size) {
            return new SearchFacetWithStringKey[size];
        }

        public SearchFacetWithStringKey createFromParcel(Parcel source) {
            SearchFacetWithStringKey object = new SearchFacetWithStringKey();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SearchFacetWithStringKey)) {
            return false;
        }
        SearchFacetWithStringKey that = (SearchFacetWithStringKey) o;
        if (this.mKey != null) {
            if (this.mKey.equals(that.mKey)) {
                return true;
            }
        } else if (that.mKey == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.mKey != null) {
            return this.mKey.hashCode();
        }
        return 0;
    }
}
