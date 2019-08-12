package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenNotificationPreference implements Parcelable {
    @JsonProperty("applies_to_push")
    protected boolean mAppliesToPush;
    @JsonProperty("applies_to_sms")
    protected boolean mAppliesToSms;
    @JsonProperty("group")
    protected String mGroup;
    @JsonProperty("id")
    protected String mId;
    @JsonProperty("push_enabled")
    protected boolean mPushEnabled;
    @JsonProperty("sms_enabled")
    protected boolean mSmsEnabled;
    @JsonProperty("user_id")
    protected int mUserId;

    protected GenNotificationPreference(String group, String id, boolean pushEnabled, boolean smsEnabled, boolean appliesToPush, boolean appliesToSms, int userId) {
        this();
        this.mGroup = group;
        this.mId = id;
        this.mPushEnabled = pushEnabled;
        this.mSmsEnabled = smsEnabled;
        this.mAppliesToPush = appliesToPush;
        this.mAppliesToSms = appliesToSms;
        this.mUserId = userId;
    }

    protected GenNotificationPreference() {
    }

    public String getGroup() {
        return this.mGroup;
    }

    @JsonProperty("group")
    public void setGroup(String value) {
        this.mGroup = value;
    }

    public String getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(String value) {
        this.mId = value;
    }

    public boolean isPushEnabled() {
        return this.mPushEnabled;
    }

    @JsonProperty("push_enabled")
    public void setPushEnabled(boolean value) {
        this.mPushEnabled = value;
    }

    public boolean isSmsEnabled() {
        return this.mSmsEnabled;
    }

    @JsonProperty("sms_enabled")
    public void setSmsEnabled(boolean value) {
        this.mSmsEnabled = value;
    }

    public boolean isAppliesToPush() {
        return this.mAppliesToPush;
    }

    @JsonProperty("applies_to_push")
    public void setAppliesToPush(boolean value) {
        this.mAppliesToPush = value;
    }

    public boolean isAppliesToSms() {
        return this.mAppliesToSms;
    }

    @JsonProperty("applies_to_sms")
    public void setAppliesToSms(boolean value) {
        this.mAppliesToSms = value;
    }

    public int getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(int value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mGroup);
        parcel.writeString(this.mId);
        parcel.writeBooleanArray(new boolean[]{this.mPushEnabled, this.mSmsEnabled, this.mAppliesToPush, this.mAppliesToSms});
        parcel.writeInt(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mGroup = source.readString();
        this.mId = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mPushEnabled = bools[0];
        this.mSmsEnabled = bools[1];
        this.mAppliesToPush = bools[2];
        this.mAppliesToSms = bools[3];
        this.mUserId = source.readInt();
    }
}
