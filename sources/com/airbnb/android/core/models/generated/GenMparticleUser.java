package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMparticleUser implements Parcelable {
    @JsonProperty("mparticle_customer_id")
    protected String mMparticleCustomerId;

    protected GenMparticleUser(String mparticleCustomerId) {
        this();
        this.mMparticleCustomerId = mparticleCustomerId;
    }

    protected GenMparticleUser() {
    }

    public String getMparticleCustomerId() {
        return this.mMparticleCustomerId;
    }

    @JsonProperty("mparticle_customer_id")
    public void setMparticleCustomerId(String value) {
        this.mMparticleCustomerId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mMparticleCustomerId);
    }

    public void readFromParcel(Parcel source) {
        this.mMparticleCustomerId = source.readString();
    }
}
