package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGiftCardPromotion implements Parcelable {
    @JsonProperty("image_url")
    protected String mImageUrl;

    protected GenGiftCardPromotion(String imageUrl) {
        this();
        this.mImageUrl = imageUrl;
    }

    protected GenGiftCardPromotion() {
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mImageUrl);
    }

    public void readFromParcel(Parcel source) {
        this.mImageUrl = source.readString();
    }
}
