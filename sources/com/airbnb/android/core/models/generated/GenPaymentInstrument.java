package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.AlipayDetails;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaymentInstrument implements Parcelable {
    @JsonProperty("alipay_details")
    protected AlipayDetails mAlipayDetails;
    @JsonProperty("detail_text")
    protected String mDetailText;
    @JsonProperty("full_name")
    protected String mFullName;
    @JsonProperty("has_error")
    protected boolean mHasError;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("is_deleted")
    protected boolean mIsDeleted;
    @JsonProperty("is_usable")
    protected boolean mIsUsable;
    @JsonProperty("is_verified")
    protected boolean mIsVerified;
    @JsonProperty("payin_enabled")
    protected boolean mPayinEnabled;
    @JsonProperty("payin_instrument_created_at")
    protected String mPayinInstrumentCreatedAt;
    @JsonProperty("payin_instrument_updated_at")
    protected String mPayinInstrumentUpdatedAt;
    @JsonProperty("payoneer_account_redirect_url")
    protected String mPayoneerAccountRedirectUrl;
    @JsonProperty("payout_enabled")
    protected boolean mPayoutEnabled;
    @JsonProperty("payout_instrument_created_at")
    protected String mPayoutInstrumentCreatedAt;
    @JsonProperty("payout_instrument_updated_at")
    protected String mPayoutInstrumentUpdatedAt;
    @JsonProperty("reference_identifier")
    protected String mReferenceIdentifier;
    @JsonProperty("short_description")
    protected String mShortDescription;
    @JsonProperty("target_currency")
    protected String mTargetCurrency;
    @JsonProperty("title_text")
    protected String mTitleText;
    @JsonProperty("type")
    protected C6200PaymentInstrumentType mType;
    @JsonProperty("user")
    protected User mUser;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenPaymentInstrument(AlipayDetails alipayDetails, C6200PaymentInstrumentType type, String detailText, String fullName, String payinInstrumentCreatedAt, String payinInstrumentUpdatedAt, String payoutInstrumentCreatedAt, String payoutInstrumentUpdatedAt, String referenceIdentifier, String shortDescription, String titleText, String targetCurrency, String payoneerAccountRedirectUrl, User user, boolean hasError, boolean isDeleted, boolean isUsable, boolean isVerified, boolean payinEnabled, boolean payoutEnabled, long id, long userId) {
        this();
        this.mAlipayDetails = alipayDetails;
        this.mType = type;
        this.mDetailText = detailText;
        this.mFullName = fullName;
        this.mPayinInstrumentCreatedAt = payinInstrumentCreatedAt;
        this.mPayinInstrumentUpdatedAt = payinInstrumentUpdatedAt;
        this.mPayoutInstrumentCreatedAt = payoutInstrumentCreatedAt;
        this.mPayoutInstrumentUpdatedAt = payoutInstrumentUpdatedAt;
        this.mReferenceIdentifier = referenceIdentifier;
        this.mShortDescription = shortDescription;
        this.mTitleText = titleText;
        this.mTargetCurrency = targetCurrency;
        this.mPayoneerAccountRedirectUrl = payoneerAccountRedirectUrl;
        this.mUser = user;
        this.mHasError = hasError;
        this.mIsDeleted = isDeleted;
        this.mIsUsable = isUsable;
        this.mIsVerified = isVerified;
        this.mPayinEnabled = payinEnabled;
        this.mPayoutEnabled = payoutEnabled;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenPaymentInstrument() {
    }

    public AlipayDetails getAlipayDetails() {
        return this.mAlipayDetails;
    }

    @JsonProperty("alipay_details")
    public void setAlipayDetails(AlipayDetails value) {
        this.mAlipayDetails = value;
    }

    public C6200PaymentInstrumentType getType() {
        return this.mType;
    }

    public String getDetailText() {
        return this.mDetailText;
    }

    @JsonProperty("detail_text")
    public void setDetailText(String value) {
        this.mDetailText = value;
    }

    public String getFullName() {
        return this.mFullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String value) {
        this.mFullName = value;
    }

    public String getPayinInstrumentCreatedAt() {
        return this.mPayinInstrumentCreatedAt;
    }

    @JsonProperty("payin_instrument_created_at")
    public void setPayinInstrumentCreatedAt(String value) {
        this.mPayinInstrumentCreatedAt = value;
    }

    public String getPayinInstrumentUpdatedAt() {
        return this.mPayinInstrumentUpdatedAt;
    }

    @JsonProperty("payin_instrument_updated_at")
    public void setPayinInstrumentUpdatedAt(String value) {
        this.mPayinInstrumentUpdatedAt = value;
    }

    public String getPayoutInstrumentCreatedAt() {
        return this.mPayoutInstrumentCreatedAt;
    }

    @JsonProperty("payout_instrument_created_at")
    public void setPayoutInstrumentCreatedAt(String value) {
        this.mPayoutInstrumentCreatedAt = value;
    }

    public String getPayoutInstrumentUpdatedAt() {
        return this.mPayoutInstrumentUpdatedAt;
    }

    @JsonProperty("payout_instrument_updated_at")
    public void setPayoutInstrumentUpdatedAt(String value) {
        this.mPayoutInstrumentUpdatedAt = value;
    }

    public String getReferenceIdentifier() {
        return this.mReferenceIdentifier;
    }

    @JsonProperty("reference_identifier")
    public void setReferenceIdentifier(String value) {
        this.mReferenceIdentifier = value;
    }

    public String getShortDescription() {
        return this.mShortDescription;
    }

    @JsonProperty("short_description")
    public void setShortDescription(String value) {
        this.mShortDescription = value;
    }

    public String getTitleText() {
        return this.mTitleText;
    }

    @JsonProperty("title_text")
    public void setTitleText(String value) {
        this.mTitleText = value;
    }

    public String getTargetCurrency() {
        return this.mTargetCurrency;
    }

    @JsonProperty("target_currency")
    public void setTargetCurrency(String value) {
        this.mTargetCurrency = value;
    }

    public String getPayoneerAccountRedirectUrl() {
        return this.mPayoneerAccountRedirectUrl;
    }

    @JsonProperty("payoneer_account_redirect_url")
    public void setPayoneerAccountRedirectUrl(String value) {
        this.mPayoneerAccountRedirectUrl = value;
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public boolean hasError() {
        return this.mHasError;
    }

    @JsonProperty("has_error")
    public void setHasError(boolean value) {
        this.mHasError = value;
    }

    public boolean isDeleted() {
        return this.mIsDeleted;
    }

    @JsonProperty("is_deleted")
    public void setIsDeleted(boolean value) {
        this.mIsDeleted = value;
    }

    public boolean isUsable() {
        return this.mIsUsable;
    }

    @JsonProperty("is_usable")
    public void setIsUsable(boolean value) {
        this.mIsUsable = value;
    }

    public boolean isVerified() {
        return this.mIsVerified;
    }

    @JsonProperty("is_verified")
    public void setIsVerified(boolean value) {
        this.mIsVerified = value;
    }

    public boolean isPayinEnabled() {
        return this.mPayinEnabled;
    }

    @JsonProperty("payin_enabled")
    public void setPayinEnabled(boolean value) {
        this.mPayinEnabled = value;
    }

    public boolean isPayoutEnabled() {
        return this.mPayoutEnabled;
    }

    @JsonProperty("payout_enabled")
    public void setPayoutEnabled(boolean value) {
        this.mPayoutEnabled = value;
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
        parcel.writeParcelable(this.mAlipayDetails, 0);
        parcel.writeParcelable(this.mType, 0);
        parcel.writeString(this.mDetailText);
        parcel.writeString(this.mFullName);
        parcel.writeString(this.mPayinInstrumentCreatedAt);
        parcel.writeString(this.mPayinInstrumentUpdatedAt);
        parcel.writeString(this.mPayoutInstrumentCreatedAt);
        parcel.writeString(this.mPayoutInstrumentUpdatedAt);
        parcel.writeString(this.mReferenceIdentifier);
        parcel.writeString(this.mShortDescription);
        parcel.writeString(this.mTitleText);
        parcel.writeString(this.mTargetCurrency);
        parcel.writeString(this.mPayoneerAccountRedirectUrl);
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeBooleanArray(new boolean[]{this.mHasError, this.mIsDeleted, this.mIsUsable, this.mIsVerified, this.mPayinEnabled, this.mPayoutEnabled});
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mAlipayDetails = (AlipayDetails) source.readParcelable(AlipayDetails.class.getClassLoader());
        this.mType = (C6200PaymentInstrumentType) source.readParcelable(C6200PaymentInstrumentType.class.getClassLoader());
        this.mDetailText = source.readString();
        this.mFullName = source.readString();
        this.mPayinInstrumentCreatedAt = source.readString();
        this.mPayinInstrumentUpdatedAt = source.readString();
        this.mPayoutInstrumentCreatedAt = source.readString();
        this.mPayoutInstrumentUpdatedAt = source.readString();
        this.mReferenceIdentifier = source.readString();
        this.mShortDescription = source.readString();
        this.mTitleText = source.readString();
        this.mTargetCurrency = source.readString();
        this.mPayoneerAccountRedirectUrl = source.readString();
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mHasError = bools[0];
        this.mIsDeleted = bools[1];
        this.mIsUsable = bools[2];
        this.mIsVerified = bools[3];
        this.mPayinEnabled = bools[4];
        this.mPayoutEnabled = bools[5];
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
