package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHelpThreadAmenity implements Parcelable {
    @JsonProperty("critical")
    protected boolean mCritical;
    @JsonProperty("icon")
    protected String mIcon;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;

    protected GenHelpThreadAmenity(String name, String icon, boolean critical, long id) {
        this();
        this.mName = name;
        this.mIcon = icon;
        this.mCritical = critical;
        this.mId = id;
    }

    protected GenHelpThreadAmenity() {
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getIcon() {
        return this.mIcon;
    }

    @JsonProperty("icon")
    public void setIcon(String value) {
        this.mIcon = value;
    }

    public boolean isCritical() {
        return this.mCritical;
    }

    @JsonProperty("critical")
    public void setCritical(boolean value) {
        this.mCritical = value;
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
        parcel.writeString(this.mIcon);
        parcel.writeBooleanArray(new boolean[]{this.mCritical});
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mName = source.readString();
        this.mIcon = source.readString();
        this.mCritical = source.createBooleanArray()[0];
        this.mId = source.readLong();
    }
}
