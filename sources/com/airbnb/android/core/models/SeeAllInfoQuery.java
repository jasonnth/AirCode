package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSeeAllInfoQuery;

public class SeeAllInfoQuery extends GenSeeAllInfoQuery {
    public static final Creator<SeeAllInfoQuery> CREATOR = new Creator<SeeAllInfoQuery>() {
        public SeeAllInfoQuery[] newArray(int size) {
            return new SeeAllInfoQuery[size];
        }

        public SeeAllInfoQuery createFromParcel(Parcel source) {
            SeeAllInfoQuery object = new SeeAllInfoQuery();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean isEmpty() {
        return this.mExperienceFilters == null && this.mGuidebookFilters == null && this.mTopLevelParams == null;
    }

    public String getLocation() {
        if (this.mTopLevelParams == null) {
            return null;
        }
        return this.mTopLevelParams.searchTerm;
    }
}
