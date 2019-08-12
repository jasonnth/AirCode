package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PlaceActivity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenWishListedPlaceActivity implements Parcelable {
    @JsonProperty("down_votes")
    protected List<Long> mDownVotes;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("place_activity")
    protected PlaceActivity mPlaceActivity;
    @JsonProperty("place_activity_id")
    protected long mPlaceActivityId;
    @JsonProperty("up_votes")
    protected List<Long> mUpVotes;

    protected GenWishListedPlaceActivity(List<Long> downVotes, List<Long> upVotes, PlaceActivity placeActivity, long id, long placeActivityId) {
        this();
        this.mDownVotes = downVotes;
        this.mUpVotes = upVotes;
        this.mPlaceActivity = placeActivity;
        this.mId = id;
        this.mPlaceActivityId = placeActivityId;
    }

    protected GenWishListedPlaceActivity() {
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

    public PlaceActivity getPlaceActivity() {
        return this.mPlaceActivity;
    }

    @JsonProperty("place_activity")
    public void setPlaceActivity(PlaceActivity value) {
        this.mPlaceActivity = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getPlaceActivityId() {
        return this.mPlaceActivityId;
    }

    @JsonProperty("place_activity_id")
    public void setPlaceActivityId(long value) {
        this.mPlaceActivityId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mDownVotes);
        parcel.writeValue(this.mUpVotes);
        parcel.writeParcelable(this.mPlaceActivity, 0);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mPlaceActivityId);
    }

    public void readFromParcel(Parcel source) {
        this.mDownVotes = (List) source.readValue(null);
        this.mUpVotes = (List) source.readValue(null);
        this.mPlaceActivity = (PlaceActivity) source.readParcelable(PlaceActivity.class.getClassLoader());
        this.mId = source.readLong();
        this.mPlaceActivityId = source.readLong();
    }
}
