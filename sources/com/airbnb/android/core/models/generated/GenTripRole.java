package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenTripRole implements Parcelable {
    @JsonProperty("role")
    protected String mRole;
    @JsonProperty("user_ids")
    protected List<Long> mUserIds;

    protected GenTripRole(List<Long> userIds, String role) {
        this();
        this.mUserIds = userIds;
        this.mRole = role;
    }

    protected GenTripRole() {
    }

    public List<Long> getUserIds() {
        return this.mUserIds;
    }

    @JsonProperty("user_ids")
    public void setUserIds(List<Long> value) {
        this.mUserIds = value;
    }

    public String getRole() {
        return this.mRole;
    }

    @JsonProperty("role")
    public void setRole(String value) {
        this.mRole = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mUserIds);
        parcel.writeString(this.mRole);
    }

    public void readFromParcel(Parcel source) {
        this.mUserIds = (List) source.readValue(null);
        this.mRole = source.readString();
    }
}
