package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSeeAllInfoQuery implements Parcelable {
    @JsonProperty("experience_filters")
    protected ExperienceFilters mExperienceFilters;
    @JsonProperty("guidebook_filters")
    protected PlaceFilters mGuidebookFilters;
    @JsonProperty("top_level_params")
    protected TopLevelSearchParams mTopLevelParams;

    protected GenSeeAllInfoQuery(ExperienceFilters experienceFilters, PlaceFilters guidebookFilters, TopLevelSearchParams topLevelParams) {
        this();
        this.mExperienceFilters = experienceFilters;
        this.mGuidebookFilters = guidebookFilters;
        this.mTopLevelParams = topLevelParams;
    }

    protected GenSeeAllInfoQuery() {
    }

    public ExperienceFilters getExperienceFilters() {
        return this.mExperienceFilters;
    }

    @JsonProperty("experience_filters")
    public void setExperienceFilters(ExperienceFilters value) {
        this.mExperienceFilters = value;
    }

    public PlaceFilters getGuidebookFilters() {
        return this.mGuidebookFilters;
    }

    @JsonProperty("guidebook_filters")
    public void setGuidebookFilters(PlaceFilters value) {
        this.mGuidebookFilters = value;
    }

    public TopLevelSearchParams getTopLevelParams() {
        return this.mTopLevelParams;
    }

    @JsonProperty("top_level_params")
    public void setTopLevelParams(TopLevelSearchParams value) {
        this.mTopLevelParams = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mExperienceFilters, 0);
        parcel.writeParcelable(this.mGuidebookFilters, 0);
        parcel.writeParcelable(this.mTopLevelParams, 0);
    }

    public void readFromParcel(Parcel source) {
        this.mExperienceFilters = (ExperienceFilters) source.readParcelable(ExperienceFilters.class.getClassLoader());
        this.mGuidebookFilters = (PlaceFilters) source.readParcelable(PlaceFilters.class.getClassLoader());
        this.mTopLevelParams = (TopLevelSearchParams) source.readParcelable(TopLevelSearchParams.class.getClassLoader());
    }
}
