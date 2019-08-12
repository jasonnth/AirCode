package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SearchGeography;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenExperiencesMetaData implements Parcelable {
    @JsonProperty("count")
    protected int mCount;
    @JsonProperty("geography")
    protected SearchGeography mGeography;

    protected GenExperiencesMetaData(SearchGeography geography, int count) {
        this();
        this.mGeography = geography;
        this.mCount = count;
    }

    protected GenExperiencesMetaData() {
    }

    public SearchGeography getGeography() {
        return this.mGeography;
    }

    @JsonProperty("geography")
    public void setGeography(SearchGeography value) {
        this.mGeography = value;
    }

    public int getCount() {
        return this.mCount;
    }

    @JsonProperty("count")
    public void setCount(int value) {
        this.mCount = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mGeography, 0);
        parcel.writeInt(this.mCount);
    }

    public void readFromParcel(Parcel source) {
        this.mGeography = (SearchGeography) source.readParcelable(SearchGeography.class.getClassLoader());
        this.mCount = source.readInt();
    }
}
