package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCountry implements Parcelable {
    @JsonProperty("alpha_2")
    protected String mAlpha_2;
    @JsonProperty("alpha_3")
    protected String mAlpha_3;
    @JsonProperty("localized_name")
    protected String mLocalizedName;

    protected GenCountry(String alpha_2, String alpha_3, String localizedName) {
        this();
        this.mAlpha_2 = alpha_2;
        this.mAlpha_3 = alpha_3;
        this.mLocalizedName = localizedName;
    }

    protected GenCountry() {
    }

    public String getAlpha_2() {
        return this.mAlpha_2;
    }

    @JsonProperty("alpha_2")
    public void setAlpha_2(String value) {
        this.mAlpha_2 = value;
    }

    public String getAlpha_3() {
        return this.mAlpha_3;
    }

    @JsonProperty("alpha_3")
    public void setAlpha_3(String value) {
        this.mAlpha_3 = value;
    }

    public String getLocalizedName() {
        return this.mLocalizedName;
    }

    @JsonProperty("localized_name")
    public void setLocalizedName(String value) {
        this.mLocalizedName = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mAlpha_2);
        parcel.writeString(this.mAlpha_3);
        parcel.writeString(this.mLocalizedName);
    }

    public void readFromParcel(Parcel source) {
        this.mAlpha_2 = source.readString();
        this.mAlpha_3 = source.readString();
        this.mLocalizedName = source.readString();
    }
}
