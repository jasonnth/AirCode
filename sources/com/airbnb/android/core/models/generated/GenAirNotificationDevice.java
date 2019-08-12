package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenAirNotificationDevice implements Parcelable {
    @JsonProperty("device_type")
    protected String mDeviceType;
    @JsonProperty("enabled")
    protected boolean mEnabled;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("token")
    protected String mToken;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenAirNotificationDevice(String deviceType, String token, boolean enabled, long id, long userId) {
        this();
        this.mDeviceType = deviceType;
        this.mToken = token;
        this.mEnabled = enabled;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenAirNotificationDevice() {
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    @JsonProperty("device_type")
    public void setDeviceType(String value) {
        this.mDeviceType = value;
    }

    public String getToken() {
        return this.mToken;
    }

    @JsonProperty("token")
    public void setToken(String value) {
        this.mToken = value;
    }

    public boolean isEnabled() {
        return this.mEnabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(boolean value) {
        this.mEnabled = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mDeviceType);
        parcel.writeString(this.mToken);
        parcel.writeBooleanArray(new boolean[]{this.mEnabled});
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mDeviceType = source.readString();
        this.mToken = source.readString();
        this.mEnabled = source.createBooleanArray()[0];
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
