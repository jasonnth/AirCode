package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenTravelDestinationsMetaData implements Parcelable {
    @JsonProperty("is_global_fallback")
    protected boolean mIsGlobalFallback;
    @JsonProperty("user_resolved_location")
    protected String mUserResolvedLocation;

    protected GenTravelDestinationsMetaData(String userResolvedLocation, boolean isGlobalFallback) {
        this();
        this.mUserResolvedLocation = userResolvedLocation;
        this.mIsGlobalFallback = isGlobalFallback;
    }

    protected GenTravelDestinationsMetaData() {
    }

    public String getUserResolvedLocation() {
        return this.mUserResolvedLocation;
    }

    @JsonProperty("user_resolved_location")
    public void setUserResolvedLocation(String value) {
        this.mUserResolvedLocation = value;
    }

    public boolean isGlobalFallback() {
        return this.mIsGlobalFallback;
    }

    @JsonProperty("is_global_fallback")
    public void setIsGlobalFallback(boolean value) {
        this.mIsGlobalFallback = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mUserResolvedLocation);
        parcel.writeBooleanArray(new boolean[]{this.mIsGlobalFallback});
    }

    public void readFromParcel(Parcel source) {
        this.mUserResolvedLocation = source.readString();
        this.mIsGlobalFallback = source.createBooleanArray()[0];
    }
}
