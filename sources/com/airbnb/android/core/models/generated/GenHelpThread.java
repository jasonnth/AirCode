package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHelpThread implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("issues")
    protected List<HelpThreadIssue> mIssues;
    @JsonProperty("locked")
    protected boolean mLocked;
    @JsonProperty("speaker")
    protected User mSpeaker;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenHelpThread(AirDateTime createdAt, AirDateTime updatedAt, List<HelpThreadIssue> issues, User speaker, boolean locked, long id, long userId) {
        this();
        this.mCreatedAt = createdAt;
        this.mUpdatedAt = updatedAt;
        this.mIssues = issues;
        this.mSpeaker = speaker;
        this.mLocked = locked;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenHelpThread() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public List<HelpThreadIssue> getIssues() {
        return this.mIssues;
    }

    @JsonProperty("issues")
    public void setIssues(List<HelpThreadIssue> value) {
        this.mIssues = value;
    }

    public User getSpeaker() {
        return this.mSpeaker;
    }

    @JsonProperty("speaker")
    public void setSpeaker(User value) {
        this.mSpeaker = value;
    }

    public boolean isLocked() {
        return this.mLocked;
    }

    @JsonProperty("locked")
    public void setLocked(boolean value) {
        this.mLocked = value;
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
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeTypedList(this.mIssues);
        parcel.writeParcelable(this.mSpeaker, 0);
        parcel.writeBooleanArray(new boolean[]{this.mLocked});
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mIssues = source.createTypedArrayList(HelpThreadIssue.CREATOR);
        this.mSpeaker = (User) source.readParcelable(User.class.getClassLoader());
        this.mLocked = source.createBooleanArray()[0];
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
