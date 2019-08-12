package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationMetadata implements Parcelable {
    @JsonProperty("has_next_page")
    protected boolean mHasNextPage;
    @JsonProperty("landing_app_tab_id")
    protected String mLandingAppTabId;

    protected GenReservationMetadata(String landingAppTabId, boolean hasNextPage) {
        this();
        this.mLandingAppTabId = landingAppTabId;
        this.mHasNextPage = hasNextPage;
    }

    protected GenReservationMetadata() {
    }

    public String getLandingAppTabId() {
        return this.mLandingAppTabId;
    }

    @JsonProperty("landing_app_tab_id")
    public void setLandingAppTabId(String value) {
        this.mLandingAppTabId = value;
    }

    public boolean hasNextPage() {
        return this.mHasNextPage;
    }

    @JsonProperty("has_next_page")
    public void setHasNextPage(boolean value) {
        this.mHasNextPage = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLandingAppTabId);
        parcel.writeBooleanArray(new boolean[]{this.mHasNextPage});
    }

    public void readFromParcel(Parcel source) {
        this.mLandingAppTabId = source.readString();
        this.mHasNextPage = source.createBooleanArray()[0];
    }
}
