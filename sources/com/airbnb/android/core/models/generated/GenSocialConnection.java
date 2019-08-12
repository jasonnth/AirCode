package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSocialConnection implements Parcelable {
    @JsonProperty("caption")
    protected String mCaption;
    @JsonProperty("connection_type")
    protected int mConnectionType;
    @JsonProperty("linking_object_id")
    protected long mLinkingObjectId;
    @JsonProperty("linking_user")
    protected User mLinkingUser;
    @JsonProperty("offline_linking_name")
    protected String mOfflineLinkingName;
    @JsonProperty("pic_url_large")
    protected String mPicUrlLarge;
    @JsonProperty("pic_url_small")
    protected String mPicUrlSmall;
    @JsonProperty("populated")
    protected boolean mPopulated;
    @JsonProperty("target_user_id")
    protected long mTargetUserId;

    protected GenSocialConnection(String offlineLinkingName, String caption, String picUrlLarge, String picUrlSmall, User linkingUser, boolean populated, int connectionType, long targetUserId, long linkingObjectId) {
        this();
        this.mOfflineLinkingName = offlineLinkingName;
        this.mCaption = caption;
        this.mPicUrlLarge = picUrlLarge;
        this.mPicUrlSmall = picUrlSmall;
        this.mLinkingUser = linkingUser;
        this.mPopulated = populated;
        this.mConnectionType = connectionType;
        this.mTargetUserId = targetUserId;
        this.mLinkingObjectId = linkingObjectId;
    }

    protected GenSocialConnection() {
    }

    public String getOfflineLinkingName() {
        return this.mOfflineLinkingName;
    }

    @JsonProperty("offline_linking_name")
    public void setOfflineLinkingName(String value) {
        this.mOfflineLinkingName = value;
    }

    public String getCaption() {
        return this.mCaption;
    }

    @JsonProperty("caption")
    public void setCaption(String value) {
        this.mCaption = value;
    }

    public String getPicUrlLarge() {
        return this.mPicUrlLarge;
    }

    public String getPicUrlSmall() {
        return this.mPicUrlSmall;
    }

    public User getLinkingUser() {
        return this.mLinkingUser;
    }

    public boolean isPopulated() {
        return this.mPopulated;
    }

    @JsonProperty("populated")
    public void setPopulated(boolean value) {
        this.mPopulated = value;
    }

    public int getConnectionType() {
        return this.mConnectionType;
    }

    @JsonProperty("connection_type")
    public void setConnectionType(int value) {
        this.mConnectionType = value;
    }

    public long getTargetUserId() {
        return this.mTargetUserId;
    }

    @JsonProperty("target_user_id")
    public void setTargetUserId(long value) {
        this.mTargetUserId = value;
    }

    public long getLinkingObjectId() {
        return this.mLinkingObjectId;
    }

    @JsonProperty("linking_object_id")
    public void setLinkingObjectId(long value) {
        this.mLinkingObjectId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mOfflineLinkingName);
        parcel.writeString(this.mCaption);
        parcel.writeString(this.mPicUrlLarge);
        parcel.writeString(this.mPicUrlSmall);
        parcel.writeParcelable(this.mLinkingUser, 0);
        parcel.writeBooleanArray(new boolean[]{this.mPopulated});
        parcel.writeInt(this.mConnectionType);
        parcel.writeLong(this.mTargetUserId);
        parcel.writeLong(this.mLinkingObjectId);
    }

    public void readFromParcel(Parcel source) {
        this.mOfflineLinkingName = source.readString();
        this.mCaption = source.readString();
        this.mPicUrlLarge = source.readString();
        this.mPicUrlSmall = source.readString();
        this.mLinkingUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mPopulated = source.createBooleanArray()[0];
        this.mConnectionType = source.readInt();
        this.mTargetUserId = source.readLong();
        this.mLinkingObjectId = source.readLong();
    }
}
