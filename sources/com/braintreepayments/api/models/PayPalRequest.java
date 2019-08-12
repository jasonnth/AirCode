package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class PayPalRequest implements Parcelable {
    public static final Creator<PayPalRequest> CREATOR = new Creator<PayPalRequest>() {
        public PayPalRequest createFromParcel(Parcel in) {
            return new PayPalRequest(in);
        }

        public PayPalRequest[] newArray(int size) {
            return new PayPalRequest[size];
        }
    };
    public static final String INTENT_AUTHORIZE = "authorize";
    public static final String INTENT_ORDER = "order";
    public static final String INTENT_SALE = "sale";
    public static final String LANDING_PAGE_TYPE_BILLING = "billing";
    public static final String LANDING_PAGE_TYPE_LOGIN = "login";
    public static final String USER_ACTION_COMMIT = "commit";
    public static final String USER_ACTION_DEFAULT = "";
    private String mAmount;
    private String mBillingAgreementDescription;
    private String mCurrencyCode;
    private String mDisplayName;
    private String mIntent;
    private String mLandingPageType;
    private String mLocaleCode;
    private boolean mOfferCredit;
    private PostalAddress mShippingAddressOverride;
    private boolean mShippingAddressRequired;
    private String mUserAction;

    public PayPalRequest(String amount) {
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mAmount = amount;
        this.mShippingAddressRequired = false;
        this.mOfferCredit = false;
    }

    public PayPalRequest() {
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mAmount = null;
        this.mShippingAddressRequired = false;
        this.mOfferCredit = false;
    }

    public PayPalRequest currencyCode(String currencyCode) {
        this.mCurrencyCode = currencyCode;
        return this;
    }

    public PayPalRequest shippingAddressRequired(boolean shippingAddressRequired) {
        this.mShippingAddressRequired = shippingAddressRequired;
        return this;
    }

    public PayPalRequest localeCode(String localeCode) {
        this.mLocaleCode = localeCode;
        return this;
    }

    public PayPalRequest displayName(String displayName) {
        this.mDisplayName = displayName;
        return this;
    }

    public PayPalRequest billingAgreementDescription(String description) {
        this.mBillingAgreementDescription = description;
        return this;
    }

    public PayPalRequest shippingAddressOverride(PostalAddress shippingAddressOverride) {
        this.mShippingAddressOverride = shippingAddressOverride;
        return this;
    }

    public PayPalRequest intent(String intent) {
        this.mIntent = intent;
        return this;
    }

    public PayPalRequest landingPageType(String landingPageType) {
        this.mLandingPageType = landingPageType;
        return this;
    }

    public PayPalRequest userAction(String userAction) {
        this.mUserAction = userAction;
        return this;
    }

    public PayPalRequest offerCredit(boolean offerCredit) {
        this.mOfferCredit = offerCredit;
        return this;
    }

    public String getAmount() {
        return this.mAmount;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public String getLocaleCode() {
        return this.mLocaleCode;
    }

    public String getBillingAgreementDescription() {
        return this.mBillingAgreementDescription;
    }

    public boolean isShippingAddressRequired() {
        return this.mShippingAddressRequired;
    }

    public PostalAddress getShippingAddressOverride() {
        return this.mShippingAddressOverride;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean shouldOfferCredit() {
        return this.mOfferCredit;
    }

    public String getIntent() {
        return this.mIntent;
    }

    public String getLandingPageType() {
        return this.mLandingPageType;
    }

    public String getUserAction() {
        return this.mUserAction;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = 1;
        parcel.writeString(this.mAmount);
        parcel.writeString(this.mCurrencyCode);
        parcel.writeString(this.mLocaleCode);
        parcel.writeString(this.mBillingAgreementDescription);
        parcel.writeByte(this.mShippingAddressRequired ? (byte) 1 : 0);
        parcel.writeParcelable(this.mShippingAddressOverride, i);
        parcel.writeString(this.mIntent);
        parcel.writeString(this.mLandingPageType);
        parcel.writeString(this.mUserAction);
        parcel.writeString(this.mDisplayName);
        if (!this.mOfferCredit) {
            b = 0;
        }
        parcel.writeByte(b);
    }

    public PayPalRequest(Parcel in) {
        boolean z = true;
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mAmount = in.readString();
        this.mCurrencyCode = in.readString();
        this.mLocaleCode = in.readString();
        this.mBillingAgreementDescription = in.readString();
        this.mShippingAddressRequired = in.readByte() > 0;
        this.mShippingAddressOverride = (PostalAddress) in.readParcelable(PostalAddress.class.getClassLoader());
        this.mIntent = in.readString();
        this.mLandingPageType = in.readString();
        this.mUserAction = in.readString();
        this.mDisplayName = in.readString();
        if (in.readByte() <= 0) {
            z = false;
        }
        this.mOfferCredit = z;
    }
}
