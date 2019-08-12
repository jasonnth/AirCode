package com.airbnb.android.core.beta.models.guidebook.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.Photo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGuidebookPlace implements Parcelable {
    @JsonProperty("bold_subtitle")
    protected String mBoldSubtitle;
    @JsonProperty("category_tag_id")
    protected long mCategoryTagId;
    @JsonProperty("cover_photos")
    protected List<Photo> mCoverPhotos;
    @JsonProperty("non_bold_subtitle")
    protected String mNonBoldSubtitle;
    @JsonProperty("primary_place")
    protected Place mPrimaryPlace;
    @JsonProperty("top_category")
    protected String mTopCategory;

    protected GenGuidebookPlace(List<Photo> coverPhotos, Place primaryPlace, String boldSubtitle, String nonBoldSubtitle, String topCategory, long categoryTagId) {
        this();
        this.mCoverPhotos = coverPhotos;
        this.mPrimaryPlace = primaryPlace;
        this.mBoldSubtitle = boldSubtitle;
        this.mNonBoldSubtitle = nonBoldSubtitle;
        this.mTopCategory = topCategory;
        this.mCategoryTagId = categoryTagId;
    }

    protected GenGuidebookPlace() {
    }

    public List<Photo> getCoverPhotos() {
        return this.mCoverPhotos;
    }

    @JsonProperty("cover_photos")
    public void setCoverPhotos(List<Photo> value) {
        this.mCoverPhotos = value;
    }

    public Place getPrimaryPlace() {
        return this.mPrimaryPlace;
    }

    @JsonProperty("primary_place")
    public void setPrimaryPlace(Place value) {
        this.mPrimaryPlace = value;
    }

    public String getBoldSubtitle() {
        return this.mBoldSubtitle;
    }

    @JsonProperty("bold_subtitle")
    public void setBoldSubtitle(String value) {
        this.mBoldSubtitle = value;
    }

    public String getNonBoldSubtitle() {
        return this.mNonBoldSubtitle;
    }

    @JsonProperty("non_bold_subtitle")
    public void setNonBoldSubtitle(String value) {
        this.mNonBoldSubtitle = value;
    }

    public String getTopCategory() {
        return this.mTopCategory;
    }

    @JsonProperty("top_category")
    public void setTopCategory(String value) {
        this.mTopCategory = value;
    }

    public long getCategoryTagId() {
        return this.mCategoryTagId;
    }

    @JsonProperty("category_tag_id")
    public void setCategoryTagId(long value) {
        this.mCategoryTagId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mCoverPhotos);
        parcel.writeParcelable(this.mPrimaryPlace, 0);
        parcel.writeString(this.mBoldSubtitle);
        parcel.writeString(this.mNonBoldSubtitle);
        parcel.writeString(this.mTopCategory);
        parcel.writeLong(this.mCategoryTagId);
    }

    public void readFromParcel(Parcel source) {
        this.mCoverPhotos = source.createTypedArrayList(Photo.CREATOR);
        this.mPrimaryPlace = (Place) source.readParcelable(Place.class.getClassLoader());
        this.mBoldSubtitle = source.readString();
        this.mNonBoldSubtitle = source.readString();
        this.mTopCategory = source.readString();
        this.mCategoryTagId = source.readLong();
    }
}
