package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ExplorePromotionStyle;
import com.airbnb.android.utils.ParcelableStringMap;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenExplorePromotion implements Parcelable {
    @JsonProperty("cta")
    protected String mCta;
    @JsonProperty("data")
    protected ParcelableStringMap mData;
    @JsonProperty("style")
    protected ExplorePromotionStyle mStyle;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("type")
    protected int mType;

    protected GenExplorePromotion(ExplorePromotionStyle style, ParcelableStringMap data, String cta, String title, String subtitle, int type) {
        this();
        this.mStyle = style;
        this.mData = data;
        this.mCta = cta;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mType = type;
    }

    protected GenExplorePromotion() {
    }

    public ExplorePromotionStyle getStyle() {
        return this.mStyle;
    }

    @JsonProperty("style")
    public void setStyle(ExplorePromotionStyle value) {
        this.mStyle = value;
    }

    public ParcelableStringMap getData() {
        return this.mData;
    }

    @JsonProperty("data")
    public void setData(ParcelableStringMap value) {
        this.mData = value;
    }

    public String getCta() {
        return this.mCta;
    }

    @JsonProperty("cta")
    public void setCta(String value) {
        this.mCta = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public int getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(int value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStyle, 0);
        parcel.writeParcelable(this.mData, 0);
        parcel.writeString(this.mCta);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeInt(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mStyle = (ExplorePromotionStyle) source.readParcelable(ExplorePromotionStyle.class.getClassLoader());
        this.mData = (ParcelableStringMap) source.readParcelable(ParcelableStringMap.class.getClassLoader());
        this.mCta = source.readString();
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mType = source.readInt();
    }
}
