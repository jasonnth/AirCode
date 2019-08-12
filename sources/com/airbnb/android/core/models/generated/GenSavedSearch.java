package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.City;
import com.airbnb.android.core.models.SearchParams;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSavedSearch implements Parcelable {
    @JsonProperty("city")
    protected City mCity;
    @JsonProperty("modified_at")
    protected long mModifiedAt;
    @JsonProperty("saved_search_id")
    protected String mSavedSearchId;
    @JsonProperty("search_params")
    protected SearchParams mSearchParams;
    @JsonProperty("source")
    protected String mSource;

    protected GenSavedSearch(City city, SearchParams searchParams, String savedSearchId, String source, long modifiedAt) {
        this();
        this.mCity = city;
        this.mSearchParams = searchParams;
        this.mSavedSearchId = savedSearchId;
        this.mSource = source;
        this.mModifiedAt = modifiedAt;
    }

    protected GenSavedSearch() {
    }

    public City getCity() {
        return this.mCity;
    }

    @JsonProperty("city")
    public void setCity(City value) {
        this.mCity = value;
    }

    public SearchParams getSearchParams() {
        return this.mSearchParams;
    }

    @JsonProperty("search_params")
    public void setSearchParams(SearchParams value) {
        this.mSearchParams = value;
    }

    public String getSavedSearchId() {
        return this.mSavedSearchId;
    }

    @JsonProperty("saved_search_id")
    public void setSavedSearchId(String value) {
        this.mSavedSearchId = value;
    }

    public String getSource() {
        return this.mSource;
    }

    @JsonProperty("source")
    public void setSource(String value) {
        this.mSource = value;
    }

    public long getModifiedAt() {
        return this.mModifiedAt;
    }

    @JsonProperty("modified_at")
    public void setModifiedAt(long value) {
        this.mModifiedAt = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCity, 0);
        parcel.writeParcelable(this.mSearchParams, 0);
        parcel.writeString(this.mSavedSearchId);
        parcel.writeString(this.mSource);
        parcel.writeLong(this.mModifiedAt);
    }

    public void readFromParcel(Parcel source) {
        this.mCity = (City) source.readParcelable(City.class.getClassLoader());
        this.mSearchParams = (SearchParams) source.readParcelable(SearchParams.class.getClassLoader());
        this.mSavedSearchId = source.readString();
        this.mSource = source.readString();
        this.mModifiedAt = source.readLong();
    }
}
