package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.ActionNotificationType;
import com.airbnb.android.core.models.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenDashboardAlert implements Parcelable {
    @JsonProperty("action_notification_type")
    protected ActionNotificationType mActionNotificationType;
    @JsonProperty("alert_type")
    protected int mAlertType;
    @JsonProperty("alert_type_formatted")
    protected String mAlertTypeFormatted;
    @JsonProperty("body_text")
    protected String mBodyText;
    @JsonProperty("content_id")
    protected int mContentId;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("deeplink_url")
    protected String mDeeplinkUrl;
    @JsonProperty("event_id")
    protected int mEventId;
    @JsonProperty("expires_at")
    protected AirDateTime mExpiresAt;
    @JsonProperty("group_id")
    protected int mGroupId;
    @JsonProperty("header_text")
    protected String mHeaderText;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("object_id")
    protected long mObjectId;
    @JsonProperty("object_type")
    protected String mObjectType;
    @JsonProperty("payment_id")
    protected long mPaymentId;
    @JsonProperty("reservation")
    protected Reservation mReservation;
    @JsonProperty("title_text")
    protected String mTitleText;
    @JsonProperty("url")
    protected String mUrl;
    @JsonProperty("user_id")
    protected long mUserId;
    @JsonProperty("viewed")
    protected boolean mViewed;

    protected GenDashboardAlert(ActionNotificationType actionNotificationType, AirDateTime createdAt, AirDateTime expiresAt, Reservation reservation, String objectType, String alertTypeFormatted, String imageUrl, String titleText, String bodyText, String url, String deeplinkUrl, String headerText, boolean viewed, int alertType, int groupId, int contentId, int eventId, long id, long objectId, long userId, long paymentId) {
        this();
        this.mActionNotificationType = actionNotificationType;
        this.mCreatedAt = createdAt;
        this.mExpiresAt = expiresAt;
        this.mReservation = reservation;
        this.mObjectType = objectType;
        this.mAlertTypeFormatted = alertTypeFormatted;
        this.mImageUrl = imageUrl;
        this.mTitleText = titleText;
        this.mBodyText = bodyText;
        this.mUrl = url;
        this.mDeeplinkUrl = deeplinkUrl;
        this.mHeaderText = headerText;
        this.mViewed = viewed;
        this.mAlertType = alertType;
        this.mGroupId = groupId;
        this.mContentId = contentId;
        this.mEventId = eventId;
        this.mId = id;
        this.mObjectId = objectId;
        this.mUserId = userId;
        this.mPaymentId = paymentId;
    }

    protected GenDashboardAlert() {
    }

    public ActionNotificationType getActionNotificationType() {
        return this.mActionNotificationType;
    }

    @JsonProperty("action_notification_type")
    public void setActionNotificationType(ActionNotificationType value) {
        this.mActionNotificationType = value;
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public AirDateTime getExpiresAt() {
        return this.mExpiresAt;
    }

    @JsonProperty("expires_at")
    public void setExpiresAt(AirDateTime value) {
        this.mExpiresAt = value;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }

    @JsonProperty("reservation")
    public void setReservation(Reservation value) {
        this.mReservation = value;
    }

    public String getObjectType() {
        return this.mObjectType;
    }

    @JsonProperty("object_type")
    public void setObjectType(String value) {
        this.mObjectType = value;
    }

    public String getAlertTypeFormatted() {
        return this.mAlertTypeFormatted;
    }

    @JsonProperty("alert_type_formatted")
    public void setAlertTypeFormatted(String value) {
        this.mAlertTypeFormatted = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    @JsonProperty("title_text")
    public void setTitleText(String value) {
        this.mTitleText = value;
    }

    public String getBodyText() {
        return this.mBodyText;
    }

    @JsonProperty("body_text")
    public void setBodyText(String value) {
        this.mBodyText = value;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public String getDeeplinkUrl() {
        return this.mDeeplinkUrl;
    }

    @JsonProperty("deeplink_url")
    public void setDeeplinkUrl(String value) {
        this.mDeeplinkUrl = value;
    }

    public String getHeaderText() {
        return this.mHeaderText;
    }

    @JsonProperty("header_text")
    public void setHeaderText(String value) {
        this.mHeaderText = value;
    }

    public boolean isViewed() {
        return this.mViewed;
    }

    @JsonProperty("viewed")
    public void setViewed(boolean value) {
        this.mViewed = value;
    }

    public int getAlertType() {
        return this.mAlertType;
    }

    @JsonProperty("alert_type")
    public void setAlertType(int value) {
        this.mAlertType = value;
    }

    public int getGroupId() {
        return this.mGroupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(int value) {
        this.mGroupId = value;
    }

    public int getContentId() {
        return this.mContentId;
    }

    @JsonProperty("content_id")
    public void setContentId(int value) {
        this.mContentId = value;
    }

    public int getEventId() {
        return this.mEventId;
    }

    @JsonProperty("event_id")
    public void setEventId(int value) {
        this.mEventId = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getObjectId() {
        return this.mObjectId;
    }

    @JsonProperty("object_id")
    public void setObjectId(long value) {
        this.mObjectId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public long getPaymentId() {
        return this.mPaymentId;
    }

    @JsonProperty("payment_id")
    public void setPaymentId(long value) {
        this.mPaymentId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mActionNotificationType, 0);
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeParcelable(this.mExpiresAt, 0);
        parcel.writeParcelable(this.mReservation, 0);
        parcel.writeString(this.mObjectType);
        parcel.writeString(this.mAlertTypeFormatted);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mTitleText);
        parcel.writeString(this.mBodyText);
        parcel.writeString(this.mUrl);
        parcel.writeString(this.mDeeplinkUrl);
        parcel.writeString(this.mHeaderText);
        parcel.writeBooleanArray(new boolean[]{this.mViewed});
        parcel.writeInt(this.mAlertType);
        parcel.writeInt(this.mGroupId);
        parcel.writeInt(this.mContentId);
        parcel.writeInt(this.mEventId);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mObjectId);
        parcel.writeLong(this.mUserId);
        parcel.writeLong(this.mPaymentId);
    }

    public void readFromParcel(Parcel source) {
        this.mActionNotificationType = (ActionNotificationType) source.readParcelable(ActionNotificationType.class.getClassLoader());
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
        this.mObjectType = source.readString();
        this.mAlertTypeFormatted = source.readString();
        this.mImageUrl = source.readString();
        this.mTitleText = source.readString();
        this.mBodyText = source.readString();
        this.mUrl = source.readString();
        this.mDeeplinkUrl = source.readString();
        this.mHeaderText = source.readString();
        this.mViewed = source.createBooleanArray()[0];
        this.mAlertType = source.readInt();
        this.mGroupId = source.readInt();
        this.mContentId = source.readInt();
        this.mEventId = source.readInt();
        this.mId = source.readLong();
        this.mObjectId = source.readLong();
        this.mUserId = source.readLong();
        this.mPaymentId = source.readLong();
    }
}
