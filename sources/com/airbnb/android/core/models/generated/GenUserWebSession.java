package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserWebSession implements Parcelable {
    @JsonProperty("cookie_name")
    protected String mCookieName;
    @JsonProperty("session_id")
    protected String mSessionId;
    @JsonProperty("session_type")
    protected String mSessionType;
    @JsonProperty("user_id")
    protected int mUserId;

    protected GenUserWebSession(String sessionType, String cookieName, String sessionId, int userId) {
        this();
        this.mSessionType = sessionType;
        this.mCookieName = cookieName;
        this.mSessionId = sessionId;
        this.mUserId = userId;
    }

    protected GenUserWebSession() {
    }

    public String getSessionType() {
        return this.mSessionType;
    }

    @JsonProperty("session_type")
    public void setSessionType(String value) {
        this.mSessionType = value;
    }

    public String getCookieName() {
        return this.mCookieName;
    }

    @JsonProperty("cookie_name")
    public void setCookieName(String value) {
        this.mCookieName = value;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    @JsonProperty("session_id")
    public void setSessionId(String value) {
        this.mSessionId = value;
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
        parcel.writeString(this.mSessionType);
        parcel.writeString(this.mCookieName);
        parcel.writeString(this.mSessionId);
        parcel.writeInt(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mSessionType = source.readString();
        this.mCookieName = source.readString();
        this.mSessionId = source.readString();
        this.mUserId = source.readInt();
    }
}
