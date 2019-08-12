package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaginationMetadata implements Parcelable {
    @JsonProperty("has_next_page")
    protected boolean mHasNextPage;
    @JsonProperty("items_offset")
    protected int mItemsOffset;
    @JsonProperty("recommendation_item_cursor")
    protected String mRecommendationItemCursor;
    @JsonProperty("search_session_id")
    protected String mSearchSessionId;
    @JsonProperty("section_offset")
    protected int mSectionOffset;

    protected GenPaginationMetadata(String recommendationItemCursor, String searchSessionId, boolean hasNextPage, int sectionOffset, int itemsOffset) {
        this();
        this.mRecommendationItemCursor = recommendationItemCursor;
        this.mSearchSessionId = searchSessionId;
        this.mHasNextPage = hasNextPage;
        this.mSectionOffset = sectionOffset;
        this.mItemsOffset = itemsOffset;
    }

    protected GenPaginationMetadata() {
    }

    public String getRecommendationItemCursor() {
        return this.mRecommendationItemCursor;
    }

    @JsonProperty("recommendation_item_cursor")
    public void setRecommendationItemCursor(String value) {
        this.mRecommendationItemCursor = value;
    }

    public String getSearchSessionId() {
        return this.mSearchSessionId;
    }

    @JsonProperty("search_session_id")
    public void setSearchSessionId(String value) {
        this.mSearchSessionId = value;
    }

    public boolean hasNextPage() {
        return this.mHasNextPage;
    }

    @JsonProperty("has_next_page")
    public void setHasNextPage(boolean value) {
        this.mHasNextPage = value;
    }

    public int getSectionOffset() {
        return this.mSectionOffset;
    }

    @JsonProperty("section_offset")
    public void setSectionOffset(int value) {
        this.mSectionOffset = value;
    }

    public int getItemsOffset() {
        return this.mItemsOffset;
    }

    @JsonProperty("items_offset")
    public void setItemsOffset(int value) {
        this.mItemsOffset = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mRecommendationItemCursor);
        parcel.writeString(this.mSearchSessionId);
        parcel.writeBooleanArray(new boolean[]{this.mHasNextPage});
        parcel.writeInt(this.mSectionOffset);
        parcel.writeInt(this.mItemsOffset);
    }

    public void readFromParcel(Parcel source) {
        this.mRecommendationItemCursor = source.readString();
        this.mSearchSessionId = source.readString();
        this.mHasNextPage = source.createBooleanArray()[0];
        this.mSectionOffset = source.readInt();
        this.mItemsOffset = source.readInt();
    }
}
