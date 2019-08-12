package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.RecommendationItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenRecommendationSection implements Parcelable {
    @JsonProperty("items")
    protected List<RecommendationItem> mItems;
    @JsonProperty("title")
    protected String mTitle;

    protected GenRecommendationSection(List<RecommendationItem> items, String title) {
        this();
        this.mItems = items;
        this.mTitle = title;
    }

    protected GenRecommendationSection() {
    }

    public List<RecommendationItem> getItems() {
        return this.mItems;
    }

    @JsonProperty("items")
    public void setItems(List<RecommendationItem> value) {
        this.mItems = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mItems);
        parcel.writeString(this.mTitle);
    }

    public void readFromParcel(Parcel source) {
        this.mItems = source.createTypedArrayList(RecommendationItem.CREATOR);
        this.mTitle = source.readString();
    }
}
