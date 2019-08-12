package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenEmptyStateMetadata implements Parcelable {
    @JsonProperty("cta")
    protected String mCta;
    @JsonProperty("description")
    protected String mDescription;

    protected GenEmptyStateMetadata(String description, String cta) {
        this();
        this.mDescription = description;
        this.mCta = cta;
    }

    protected GenEmptyStateMetadata() {
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getCta() {
        return this.mCta;
    }

    @JsonProperty("cta")
    public void setCta(String value) {
        this.mCta = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mCta);
    }

    public void readFromParcel(Parcel source) {
        this.mDescription = source.readString();
        this.mCta = source.readString();
    }
}
