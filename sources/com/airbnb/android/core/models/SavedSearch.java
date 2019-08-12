package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSavedSearch;

public class SavedSearch extends GenSavedSearch {
    public static final Creator<SavedSearch> CREATOR = new Creator<SavedSearch>() {
        public SavedSearch[] newArray(int size) {
            return new SavedSearch[size];
        }

        public SavedSearch createFromParcel(Parcel source) {
            SavedSearch object = new SavedSearch();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String SOURCE_ANDROID = "android";

    public boolean hasDates() {
        SearchParams searchParams = getSearchParams();
        return (searchParams == null || searchParams.getCheckin() == null || searchParams.getCheckout() == null) ? false : true;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenSavedSearch)) {
            return false;
        }
        GenSavedSearch that = (GenSavedSearch) o;
        if (this.mSavedSearchId != null) {
            if (this.mSavedSearchId.equals(that.getSavedSearchId())) {
                return true;
            }
        } else if (that.getSavedSearchId() == null) {
            return true;
        }
        return false;
    }

    public String getSavedSearchId() {
        if (this.mSavedSearchId == null) {
            this.mSavedSearchId = String.valueOf(System.currentTimeMillis());
        }
        return this.mSavedSearchId;
    }

    public String getSource() {
        if (this.mSource == null) {
            this.mSource = "android";
        }
        return this.mSource;
    }

    public int hashCode() {
        if (this.mSavedSearchId != null) {
            return this.mSavedSearchId.hashCode();
        }
        return 0;
    }

    public boolean hasValidData() {
        return (getSavedSearchId() == null || getSearchParams() == null) ? false : true;
    }
}
