package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.EmptyStateMetadata;
import com.airbnb.android.core.models.ExperiencesMetaData;
import com.airbnb.android.core.models.ExperimentMetadata;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.PlacesMetaData;
import com.airbnb.android.core.models.SearchMetaData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenExploreTab implements Parcelable {
    @JsonProperty("empty_state_metadata")
    protected EmptyStateMetadata mEmptyStateMetadata;
    @JsonProperty("experience_tab_metadata")
    protected ExperiencesMetaData mExperienceTabMetadata;
    @JsonProperty("experiments_metadata")
    protected List<ExperimentMetadata> mExperimentsMetadata;
    @JsonProperty("all_tab_metadata")
    protected ForYouMetaData mForYouMetaData;
    @JsonProperty("home_tab_metadata")
    protected SearchMetaData mHomeTabMetadata;
    @JsonProperty("pagination_metadata")
    protected PaginationMetadata mPaginationMetadata;
    @JsonProperty("place_tab_metadata")
    protected PlacesMetaData mPlaceTabMetadata;
    @JsonProperty("sections")
    protected List<ExploreSection> mSections;
    @JsonProperty("tab_id")
    protected String mTabId;
    @JsonProperty("tab_name")
    protected String mTabName;

    protected GenExploreTab(EmptyStateMetadata emptyStateMetadata, ExperiencesMetaData experienceTabMetadata, ForYouMetaData forYouMetaData, List<ExperimentMetadata> experimentsMetadata, List<ExploreSection> sections, PaginationMetadata paginationMetadata, PlacesMetaData placeTabMetadata, SearchMetaData homeTabMetadata, String tabName, String tabId) {
        this();
        this.mEmptyStateMetadata = emptyStateMetadata;
        this.mExperienceTabMetadata = experienceTabMetadata;
        this.mForYouMetaData = forYouMetaData;
        this.mExperimentsMetadata = experimentsMetadata;
        this.mSections = sections;
        this.mPaginationMetadata = paginationMetadata;
        this.mPlaceTabMetadata = placeTabMetadata;
        this.mHomeTabMetadata = homeTabMetadata;
        this.mTabName = tabName;
        this.mTabId = tabId;
    }

    protected GenExploreTab() {
    }

    public EmptyStateMetadata getEmptyStateMetadata() {
        return this.mEmptyStateMetadata;
    }

    @JsonProperty("empty_state_metadata")
    public void setEmptyStateMetadata(EmptyStateMetadata value) {
        this.mEmptyStateMetadata = value;
    }

    public ExperiencesMetaData getExperienceTabMetadata() {
        return this.mExperienceTabMetadata;
    }

    @JsonProperty("experience_tab_metadata")
    public void setExperienceTabMetadata(ExperiencesMetaData value) {
        this.mExperienceTabMetadata = value;
    }

    public ForYouMetaData getForYouMetaData() {
        return this.mForYouMetaData;
    }

    @JsonProperty("all_tab_metadata")
    public void setForYouMetaData(ForYouMetaData value) {
        this.mForYouMetaData = value;
    }

    public List<ExperimentMetadata> getExperimentsMetadata() {
        return this.mExperimentsMetadata;
    }

    @JsonProperty("experiments_metadata")
    public void setExperimentsMetadata(List<ExperimentMetadata> value) {
        this.mExperimentsMetadata = value;
    }

    public List<ExploreSection> getSections() {
        return this.mSections;
    }

    @JsonProperty("sections")
    public void setSections(List<ExploreSection> value) {
        this.mSections = value;
    }

    public PaginationMetadata getPaginationMetadata() {
        return this.mPaginationMetadata;
    }

    @JsonProperty("pagination_metadata")
    public void setPaginationMetadata(PaginationMetadata value) {
        this.mPaginationMetadata = value;
    }

    public PlacesMetaData getPlaceTabMetadata() {
        return this.mPlaceTabMetadata;
    }

    @JsonProperty("place_tab_metadata")
    public void setPlaceTabMetadata(PlacesMetaData value) {
        this.mPlaceTabMetadata = value;
    }

    public SearchMetaData getHomeTabMetadata() {
        return this.mHomeTabMetadata;
    }

    @JsonProperty("home_tab_metadata")
    public void setHomeTabMetadata(SearchMetaData value) {
        this.mHomeTabMetadata = value;
    }

    public String getTabName() {
        return this.mTabName;
    }

    @JsonProperty("tab_name")
    public void setTabName(String value) {
        this.mTabName = value;
    }

    public String getTabId() {
        return this.mTabId;
    }

    @JsonProperty("tab_id")
    public void setTabId(String value) {
        this.mTabId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mEmptyStateMetadata, 0);
        parcel.writeParcelable(this.mExperienceTabMetadata, 0);
        parcel.writeParcelable(this.mForYouMetaData, 0);
        parcel.writeTypedList(this.mExperimentsMetadata);
        parcel.writeTypedList(this.mSections);
        parcel.writeParcelable(this.mPaginationMetadata, 0);
        parcel.writeParcelable(this.mPlaceTabMetadata, 0);
        parcel.writeParcelable(this.mHomeTabMetadata, 0);
        parcel.writeString(this.mTabName);
        parcel.writeString(this.mTabId);
    }

    public void readFromParcel(Parcel source) {
        this.mEmptyStateMetadata = (EmptyStateMetadata) source.readParcelable(EmptyStateMetadata.class.getClassLoader());
        this.mExperienceTabMetadata = (ExperiencesMetaData) source.readParcelable(ExperiencesMetaData.class.getClassLoader());
        this.mForYouMetaData = (ForYouMetaData) source.readParcelable(ForYouMetaData.class.getClassLoader());
        this.mExperimentsMetadata = source.createTypedArrayList(ExperimentMetadata.CREATOR);
        this.mSections = source.createTypedArrayList(ExploreSection.CREATOR);
        this.mPaginationMetadata = (PaginationMetadata) source.readParcelable(PaginationMetadata.class.getClassLoader());
        this.mPlaceTabMetadata = (PlacesMetaData) source.readParcelable(PlacesMetaData.class.getClassLoader());
        this.mHomeTabMetadata = (SearchMetaData) source.readParcelable(SearchMetaData.class.getClassLoader());
        this.mTabName = source.readString();
        this.mTabId = source.readString();
    }
}
