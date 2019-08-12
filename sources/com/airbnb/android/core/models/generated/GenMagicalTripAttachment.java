package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.MagicalTripAttachmentDetails;
import com.airbnb.android.core.models.TripRole;
import com.airbnb.android.core.models.TripStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenMagicalTripAttachment implements Parcelable {
    @JsonProperty("details")
    protected MagicalTripAttachmentDetails mDetails;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("roles")
    protected List<TripRole> mRoles;
    @JsonProperty("status")
    protected TripStatus mStatus;
    @JsonProperty("type")
    protected String mType;

    protected GenMagicalTripAttachment(List<TripRole> roles, MagicalTripAttachmentDetails details, String type, TripStatus status, long id) {
        this();
        this.mRoles = roles;
        this.mDetails = details;
        this.mType = type;
        this.mStatus = status;
        this.mId = id;
    }

    protected GenMagicalTripAttachment() {
    }

    public List<TripRole> getRoles() {
        return this.mRoles;
    }

    @JsonProperty("roles")
    public void setRoles(List<TripRole> value) {
        this.mRoles = value;
    }

    public MagicalTripAttachmentDetails getDetails() {
        return this.mDetails;
    }

    @JsonProperty("details")
    public void setDetails(MagicalTripAttachmentDetails value) {
        this.mDetails = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public TripStatus getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(TripStatus value) {
        this.mStatus = value;
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
        parcel.writeTypedList(this.mRoles);
        parcel.writeParcelable(this.mDetails, 0);
        parcel.writeString(this.mType);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mRoles = source.createTypedArrayList(TripRole.CREATOR);
        this.mDetails = (MagicalTripAttachmentDetails) source.readParcelable(MagicalTripAttachmentDetails.class.getClassLoader());
        this.mType = source.readString();
        this.mStatus = (TripStatus) source.readParcelable(TripStatus.class.getClassLoader());
        this.mId = source.readLong();
    }
}
