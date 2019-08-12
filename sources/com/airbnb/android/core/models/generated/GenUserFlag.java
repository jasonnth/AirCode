package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenUserFlag implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("flaggable_id")
    protected long mFlaggableId;
    @JsonProperty("flagging_user")
    protected User mFlaggingUser;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("number_of_guests")
    protected int mNumberOfGuests;
    @JsonProperty("other_note")
    protected String mOtherNote;
    @JsonProperty("redacted")
    protected boolean mRedacted;

    protected GenUserFlag(AirDateTime createdAt, String name, String otherNote, User flaggingUser, boolean redacted, int numberOfGuests, long id, long flaggableId) {
        this();
        this.mCreatedAt = createdAt;
        this.mName = name;
        this.mOtherNote = otherNote;
        this.mFlaggingUser = flaggingUser;
        this.mRedacted = redacted;
        this.mNumberOfGuests = numberOfGuests;
        this.mId = id;
        this.mFlaggableId = flaggableId;
    }

    protected GenUserFlag() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getOtherNote() {
        return this.mOtherNote;
    }

    @JsonProperty("other_note")
    public void setOtherNote(String value) {
        this.mOtherNote = value;
    }

    public User getFlaggingUser() {
        return this.mFlaggingUser;
    }

    @JsonProperty("flagging_user")
    public void setFlaggingUser(User value) {
        this.mFlaggingUser = value;
    }

    public boolean isRedacted() {
        return this.mRedacted;
    }

    @JsonProperty("redacted")
    public void setRedacted(boolean value) {
        this.mRedacted = value;
    }

    public int getNumberOfGuests() {
        return this.mNumberOfGuests;
    }

    @JsonProperty("number_of_guests")
    public void setNumberOfGuests(int value) {
        this.mNumberOfGuests = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getFlaggableId() {
        return this.mFlaggableId;
    }

    @JsonProperty("flaggable_id")
    public void setFlaggableId(long value) {
        this.mFlaggableId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeString(this.mName);
        parcel.writeString(this.mOtherNote);
        parcel.writeParcelable(this.mFlaggingUser, 0);
        parcel.writeBooleanArray(new boolean[]{this.mRedacted});
        parcel.writeInt(this.mNumberOfGuests);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mFlaggableId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mName = source.readString();
        this.mOtherNote = source.readString();
        this.mFlaggingUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mRedacted = source.createBooleanArray()[0];
        this.mNumberOfGuests = source.readInt();
        this.mId = source.readLong();
        this.mFlaggableId = source.readLong();
    }
}
