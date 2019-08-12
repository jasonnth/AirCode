package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.beta.models.guidebook.GuidebookPlace;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenWishListedPlace implements Parcelable {
    @JsonProperty("down_votes")
    protected List<Long> mDownVotes;
    @JsonProperty("guidebook_place")
    protected GuidebookPlace mGuidebookPlace;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("place_id")
    protected long mPlaceId;
    @JsonProperty("up_votes")
    protected List<Long> mUpVotes;

    protected GenWishListedPlace(GuidebookPlace guidebookPlace, List<Long> downVotes, List<Long> upVotes, long id, long placeId) {
        this();
        this.mGuidebookPlace = guidebookPlace;
        this.mDownVotes = downVotes;
        this.mUpVotes = upVotes;
        this.mId = id;
        this.mPlaceId = placeId;
    }

    protected GenWishListedPlace() {
    }

    public GuidebookPlace getGuidebookPlace() {
        return this.mGuidebookPlace;
    }

    @JsonProperty("guidebook_place")
    public void setGuidebookPlace(GuidebookPlace value) {
        this.mGuidebookPlace = value;
    }

    public List<Long> getDownVotes() {
        return this.mDownVotes;
    }

    @JsonProperty("down_votes")
    public void setDownVotes(List<Long> value) {
        this.mDownVotes = value;
    }

    public List<Long> getUpVotes() {
        return this.mUpVotes;
    }

    @JsonProperty("up_votes")
    public void setUpVotes(List<Long> value) {
        this.mUpVotes = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getPlaceId() {
        return this.mPlaceId;
    }

    @JsonProperty("place_id")
    public void setPlaceId(long value) {
        this.mPlaceId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mGuidebookPlace, 0);
        parcel.writeValue(this.mDownVotes);
        parcel.writeValue(this.mUpVotes);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mPlaceId);
    }

    public void readFromParcel(Parcel source) {
        this.mGuidebookPlace = (GuidebookPlace) source.readParcelable(GuidebookPlace.class.getClassLoader());
        this.mDownVotes = (List) source.readValue(null);
        this.mUpVotes = (List) source.readValue(null);
        this.mId = source.readLong();
        this.mPlaceId = source.readLong();
    }
}
