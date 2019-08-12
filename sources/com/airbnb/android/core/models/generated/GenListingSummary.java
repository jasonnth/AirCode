package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingSummary implements Parcelable {
    @JsonProperty("has_paid_amenities")
    protected boolean mHasPaidAmenities;
    @JsonProperty("hosts")
    protected List<User> mHosts;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("instant_bookable")
    protected boolean mInstantBookable;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("primary_host")
    protected User mPrimaryHost;

    protected GenListingSummary(List<User> hosts, String name, User primaryHost, boolean instantBookable, boolean hasPaidAmenities, long id) {
        this();
        this.mHosts = hosts;
        this.mName = name;
        this.mPrimaryHost = primaryHost;
        this.mInstantBookable = instantBookable;
        this.mHasPaidAmenities = hasPaidAmenities;
        this.mId = id;
    }

    protected GenListingSummary() {
    }

    public List<User> getHosts() {
        return this.mHosts;
    }

    @JsonProperty("hosts")
    public void setHosts(List<User> value) {
        this.mHosts = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public User getPrimaryHost() {
        return this.mPrimaryHost;
    }

    @JsonProperty("primary_host")
    public void setPrimaryHost(User value) {
        this.mPrimaryHost = value;
    }

    public boolean isInstantBookable() {
        return this.mInstantBookable;
    }

    @JsonProperty("instant_bookable")
    public void setInstantBookable(boolean value) {
        this.mInstantBookable = value;
    }

    public boolean hasPaidAmenities() {
        return this.mHasPaidAmenities;
    }

    @JsonProperty("has_paid_amenities")
    public void setHasPaidAmenities(boolean value) {
        this.mHasPaidAmenities = value;
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
        parcel.writeTypedList(this.mHosts);
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mPrimaryHost, 0);
        parcel.writeBooleanArray(new boolean[]{this.mInstantBookable, this.mHasPaidAmenities});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mHosts = source.createTypedArrayList(User.CREATOR);
        this.mName = source.readString();
        this.mPrimaryHost = (User) source.readParcelable(User.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mInstantBookable = bools[0];
        this.mHasPaidAmenities = bools[1];
        this.mId = source.readLong();
    }
}
