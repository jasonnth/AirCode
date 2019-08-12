package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReservationUser implements Parcelable {
    @JsonProperty("email")
    protected String mEmail;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("role")
    protected int mRole;
    @JsonProperty("trip_member_type")
    protected String mTripMemberType;

    protected GenReservationUser(String name, String email, String tripMemberType, int role, long id) {
        this();
        this.mName = name;
        this.mEmail = email;
        this.mTripMemberType = tripMemberType;
        this.mRole = role;
        this.mId = id;
    }

    protected GenReservationUser() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getEmail() {
        return this.mEmail;
    }

    @JsonProperty("email")
    public void setEmail(String value) {
        this.mEmail = value;
    }

    public String getTripMemberType() {
        return this.mTripMemberType;
    }

    @JsonProperty("trip_member_type")
    public void setTripMemberType(String value) {
        this.mTripMemberType = value;
    }

    public int getRole() {
        return this.mRole;
    }

    @JsonProperty("role")
    public void setRole(int value) {
        this.mRole = value;
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
        parcel.writeString(this.mName);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mTripMemberType);
        parcel.writeInt(this.mRole);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mEmail = source.readString();
        this.mTripMemberType = source.readString();
        this.mRole = source.readInt();
        this.mId = source.readLong();
    }
}
