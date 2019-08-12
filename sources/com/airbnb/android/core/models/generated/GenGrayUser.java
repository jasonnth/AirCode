package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGrayUser implements Parcelable {
    @JsonProperty("email")
    protected String mEmail;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("profile_pic_url")
    protected String mProfile_pic_url;

    protected GenGrayUser(String email, String name, String profile_pic_url) {
        this();
        this.mEmail = email;
        this.mName = name;
        this.mProfile_pic_url = profile_pic_url;
    }

    protected GenGrayUser() {
    }

    public String getEmail() {
        return this.mEmail;
    }

    @JsonProperty("email")
    public void setEmail(String value) {
        this.mEmail = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getProfile_pic_url() {
        return this.mProfile_pic_url;
    }

    @JsonProperty("profile_pic_url")
    public void setProfile_pic_url(String value) {
        this.mProfile_pic_url = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mName);
        parcel.writeString(this.mProfile_pic_url);
    }

    public void readFromParcel(Parcel source) {
        this.mEmail = source.readString();
        this.mName = source.readString();
        this.mProfile_pic_url = source.readString();
    }
}
