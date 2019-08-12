package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenRestaurant implements Parcelable {
    @JsonProperty("about")
    protected String mAbout;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("num_rating")
    protected int mNumRating;
    @JsonProperty("rating")
    protected float mRating;

    protected GenRestaurant(String name, String about, float rating, int numRating) {
        this();
        this.mName = name;
        this.mAbout = about;
        this.mRating = rating;
        this.mNumRating = numRating;
    }

    protected GenRestaurant() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getAbout() {
        return this.mAbout;
    }

    @JsonProperty("about")
    public void setAbout(String value) {
        this.mAbout = value;
    }

    public float getRating() {
        return this.mRating;
    }

    @JsonProperty("rating")
    public void setRating(float value) {
        this.mRating = value;
    }

    public int getNumRating() {
        return this.mNumRating;
    }

    @JsonProperty("num_rating")
    public void setNumRating(int value) {
        this.mNumRating = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mAbout);
        parcel.writeFloat(this.mRating);
        parcel.writeInt(this.mNumRating);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mAbout = source.readString();
        this.mRating = source.readFloat();
        this.mNumRating = source.readInt();
    }
}
