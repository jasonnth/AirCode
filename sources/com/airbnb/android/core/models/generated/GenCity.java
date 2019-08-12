package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCity implements Parcelable {
    @JsonProperty("hero_photo")
    protected String mHeroPhoto;
    @JsonProperty("localized_name")
    protected String mLocalizedName;
    @JsonProperty("snapshot")
    protected String mSnapshot;

    protected GenCity(String localizedName, String heroPhoto, String snapshot) {
        this();
        this.mLocalizedName = localizedName;
        this.mHeroPhoto = heroPhoto;
        this.mSnapshot = snapshot;
    }

    protected GenCity() {
    }

    public String getLocalizedName() {
        return this.mLocalizedName;
    }

    @JsonProperty("localized_name")
    public void setLocalizedName(String value) {
        this.mLocalizedName = value;
    }

    public String getHeroPhoto() {
        return this.mHeroPhoto;
    }

    @JsonProperty("hero_photo")
    public void setHeroPhoto(String value) {
        this.mHeroPhoto = value;
    }

    public String getSnapshot() {
        return this.mSnapshot;
    }

    @JsonProperty("snapshot")
    public void setSnapshot(String value) {
        this.mSnapshot = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mLocalizedName);
        parcel.writeString(this.mHeroPhoto);
        parcel.writeString(this.mSnapshot);
    }

    public void readFromParcel(Parcel source) {
        this.mLocalizedName = source.readString();
        this.mHeroPhoto = source.readString();
        this.mSnapshot = source.readString();
    }
}
