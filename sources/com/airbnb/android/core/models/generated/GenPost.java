package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.AttachmentImage;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SpecialOffer;
import com.airbnb.android.core.models.UserFlag;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPost implements Parcelable {
    @JsonProperty("attachment_fallback_url")
    protected String mAttachmentFallbackUrl;
    @JsonProperty("attachment_images")
    protected List<AttachmentImage> mAttachmentImages;
    @JsonProperty("attachment_type")
    protected String mAttachmentType;
    @JsonProperty("checkin_date")
    protected AirDate mCheckinDate;
    @JsonProperty("checkout_date")
    protected AirDate mCheckoutDate;
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("guest_details")
    protected GuestDetails mGuestDetails;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("inline_status_text")
    protected String mInlineStatusText;
    @JsonProperty("is_korean_strict_booking")
    protected boolean mIsKoreanStrictBooking;
    @JsonProperty("link_id")
    protected long mLinkId;
    @JsonProperty("message")
    protected String mMessage;
    @JsonProperty("number_of_guests")
    protected int mNumberOfGuests;
    @JsonProperty("sent_from_mobile")
    protected boolean mSentFromMobile;
    @JsonProperty("special_offer")
    protected SpecialOffer mSpecialOffer;
    @JsonProperty("status")
    protected ReservationStatus mStatus;
    @JsonProperty("translated_message")
    protected String mTranslatedMessage;
    @JsonProperty("translated_version_available")
    protected Boolean mTranslatedVersionAvailable;
    @JsonProperty("user_flag")
    protected UserFlag mUserFlag;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenPost(AirDate checkinDate, AirDate checkoutDate, AirDateTime createdAt, Boolean translatedVersionAvailable, GuestDetails guestDetails, List<AttachmentImage> attachmentImages, ReservationStatus status, SpecialOffer specialOffer, String message, String translatedMessage, String inlineStatusText, String attachmentType, String AttachmentFallbackUrl, UserFlag userFlag, boolean sentFromMobile, boolean isKoreanStrictBooking, int numberOfGuests, long id, long linkId, long userId) {
        this();
        this.mCheckinDate = checkinDate;
        this.mCheckoutDate = checkoutDate;
        this.mCreatedAt = createdAt;
        this.mTranslatedVersionAvailable = translatedVersionAvailable;
        this.mGuestDetails = guestDetails;
        this.mAttachmentImages = attachmentImages;
        this.mStatus = status;
        this.mSpecialOffer = specialOffer;
        this.mMessage = message;
        this.mTranslatedMessage = translatedMessage;
        this.mInlineStatusText = inlineStatusText;
        this.mAttachmentType = attachmentType;
        this.mAttachmentFallbackUrl = AttachmentFallbackUrl;
        this.mUserFlag = userFlag;
        this.mSentFromMobile = sentFromMobile;
        this.mIsKoreanStrictBooking = isKoreanStrictBooking;
        this.mNumberOfGuests = numberOfGuests;
        this.mId = id;
        this.mLinkId = linkId;
        this.mUserId = userId;
    }

    protected GenPost() {
    }

    public AirDate getCheckinDate() {
        return this.mCheckinDate;
    }

    @JsonProperty("checkin_date")
    public void setCheckinDate(AirDate value) {
        this.mCheckinDate = value;
    }

    public AirDate getCheckoutDate() {
        return this.mCheckoutDate;
    }

    @JsonProperty("checkout_date")
    public void setCheckoutDate(AirDate value) {
        this.mCheckoutDate = value;
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public Boolean isTranslatedVersionAvailable() {
        return this.mTranslatedVersionAvailable;
    }

    public GuestDetails getGuestDetails() {
        return this.mGuestDetails;
    }

    @JsonProperty("guest_details")
    public void setGuestDetails(GuestDetails value) {
        this.mGuestDetails = value;
    }

    public List<AttachmentImage> getAttachmentImages() {
        return this.mAttachmentImages;
    }

    @JsonProperty("attachment_images")
    public void setAttachmentImages(List<AttachmentImage> value) {
        this.mAttachmentImages = value;
    }

    public ReservationStatus getStatus() {
        return this.mStatus;
    }

    @JsonProperty("status")
    public void setStatus(ReservationStatus value) {
        this.mStatus = value;
    }

    public SpecialOffer getSpecialOffer() {
        return this.mSpecialOffer;
    }

    public String getMessage() {
        return this.mMessage;
    }

    @JsonProperty("message")
    public void setMessage(String value) {
        this.mMessage = value;
    }

    public String getTranslatedMessage() {
        return this.mTranslatedMessage;
    }

    public String getInlineStatusText() {
        return this.mInlineStatusText;
    }

    @JsonProperty("inline_status_text")
    public void setInlineStatusText(String value) {
        this.mInlineStatusText = value;
    }

    public String getAttachmentType() {
        return this.mAttachmentType;
    }

    @JsonProperty("attachment_type")
    public void setAttachmentType(String value) {
        this.mAttachmentType = value;
    }

    public String getAttachmentFallbackUrl() {
        return this.mAttachmentFallbackUrl;
    }

    @JsonProperty("attachment_fallback_url")
    public void setAttachmentFallbackUrl(String value) {
        this.mAttachmentFallbackUrl = value;
    }

    public UserFlag getUserFlag() {
        return this.mUserFlag;
    }

    @JsonProperty("user_flag")
    public void setUserFlag(UserFlag value) {
        this.mUserFlag = value;
    }

    public boolean isSentFromMobile() {
        return this.mSentFromMobile;
    }

    @JsonProperty("sent_from_mobile")
    public void setSentFromMobile(boolean value) {
        this.mSentFromMobile = value;
    }

    public boolean isKoreanStrictBooking() {
        return this.mIsKoreanStrictBooking;
    }

    @JsonProperty("is_korean_strict_booking")
    public void setIsKoreanStrictBooking(boolean value) {
        this.mIsKoreanStrictBooking = value;
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

    public long getLinkId() {
        return this.mLinkId;
    }

    @JsonProperty("link_id")
    public void setLinkId(long value) {
        this.mLinkId = value;
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
        parcel.writeParcelable(this.mCheckinDate, 0);
        parcel.writeParcelable(this.mCheckoutDate, 0);
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeValue(this.mTranslatedVersionAvailable);
        parcel.writeParcelable(this.mGuestDetails, 0);
        parcel.writeTypedList(this.mAttachmentImages);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeParcelable(this.mSpecialOffer, 0);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mTranslatedMessage);
        parcel.writeString(this.mInlineStatusText);
        parcel.writeString(this.mAttachmentType);
        parcel.writeString(this.mAttachmentFallbackUrl);
        parcel.writeParcelable(this.mUserFlag, 0);
        parcel.writeBooleanArray(new boolean[]{this.mSentFromMobile, this.mIsKoreanStrictBooking});
        parcel.writeInt(this.mNumberOfGuests);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mLinkId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mCheckinDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckoutDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mTranslatedVersionAvailable = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.mGuestDetails = (GuestDetails) source.readParcelable(GuestDetails.class.getClassLoader());
        this.mAttachmentImages = source.createTypedArrayList(AttachmentImage.CREATOR);
        this.mStatus = (ReservationStatus) source.readParcelable(ReservationStatus.class.getClassLoader());
        this.mSpecialOffer = (SpecialOffer) source.readParcelable(SpecialOffer.class.getClassLoader());
        this.mMessage = source.readString();
        this.mTranslatedMessage = source.readString();
        this.mInlineStatusText = source.readString();
        this.mAttachmentType = source.readString();
        this.mAttachmentFallbackUrl = source.readString();
        this.mUserFlag = (UserFlag) source.readParcelable(UserFlag.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mSentFromMobile = bools[0];
        this.mIsKoreanStrictBooking = bools[1];
        this.mNumberOfGuests = source.readInt();
        this.mId = source.readLong();
        this.mLinkId = source.readLong();
        this.mUserId = source.readLong();
    }
}
