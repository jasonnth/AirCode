package com.airbnb.android.core.beta.models.guidebook.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.utils.ParcelableStringMap;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenGuidebookItem implements Parcelable {
    @JsonProperty("bold_subtitle")
    protected String mBoldSubtitle;
    @JsonProperty("cover_photos")
    protected List<Photo> mCoverPhotos;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("localized_name_for_explore_card")
    protected String mLocalizedNameForExploreCard;
    @JsonProperty("localized_type_for_explore")
    protected String mLocalizedTypeForExplore;
    @JsonProperty("non_bold_subtitle")
    protected String mNonBoldSubtitle;
    @JsonProperty("query_params")
    protected ParcelableStringMap mQueryParams;
    @JsonProperty("type")
    protected int mType;

    protected GenGuidebookItem(List<Photo> coverPhotos, ParcelableStringMap queryParams, String boldSubtitle, String nonBoldSubtitle, String localizedNameForExploreCard, String localizedTypeForExplore, int type, long id) {
        this();
        this.mCoverPhotos = coverPhotos;
        this.mQueryParams = queryParams;
        this.mBoldSubtitle = boldSubtitle;
        this.mNonBoldSubtitle = nonBoldSubtitle;
        this.mLocalizedNameForExploreCard = localizedNameForExploreCard;
        this.mLocalizedTypeForExplore = localizedTypeForExplore;
        this.mType = type;
        this.mId = id;
    }

    protected GenGuidebookItem() {
    }

    public List<Photo> getCoverPhotos() {
        return this.mCoverPhotos;
    }

    @JsonProperty("cover_photos")
    public void setCoverPhotos(List<Photo> value) {
        this.mCoverPhotos = value;
    }

    public ParcelableStringMap getQueryParams() {
        return this.mQueryParams;
    }

    @JsonProperty("query_params")
    public void setQueryParams(ParcelableStringMap value) {
        this.mQueryParams = value;
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

    public String getLocalizedNameForExploreCard() {
        return this.mLocalizedNameForExploreCard;
    }

    @JsonProperty("localized_name_for_explore_card")
    public void setLocalizedNameForExploreCard(String value) {
        this.mLocalizedNameForExploreCard = value;
    }

    public String getLocalizedTypeForExplore() {
        return this.mLocalizedTypeForExplore;
    }

    @JsonProperty("localized_type_for_explore")
    public void setLocalizedTypeForExplore(String value) {
        this.mLocalizedTypeForExplore = value;
    }

    public int getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(int value) {
        this.mType = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mCoverPhotos);
        parcel.writeParcelable(this.mQueryParams, 0);
        parcel.writeString(this.mBoldSubtitle);
        parcel.writeString(this.mNonBoldSubtitle);
        parcel.writeString(this.mLocalizedNameForExploreCard);
        parcel.writeString(this.mLocalizedTypeForExplore);
        parcel.writeInt(this.mType);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCoverPhotos = source.createTypedArrayList(Photo.CREATOR);
        this.mQueryParams = (ParcelableStringMap) source.readParcelable(ParcelableStringMap.class.getClassLoader());
        this.mBoldSubtitle = source.readString();
        this.mNonBoldSubtitle = source.readString();
        this.mLocalizedNameForExploreCard = source.readString();
        this.mLocalizedTypeForExplore = source.readString();
        this.mType = source.readInt();
        this.mId = source.readLong();
    }
}
