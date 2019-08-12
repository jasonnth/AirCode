package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalAccountNonce extends PaymentMethodNonce implements Parcelable {
    private static final String ACCOUNT_ADDRESS_KEY = "accountAddress";
    protected static final String API_RESOURCE_KEY = "paypalAccounts";
    private static final String BILLING_ADDRESS_KEY = "billingAddress";
    private static final String CLIENT_METADATA_ID_KEY = "correlationId";
    public static final Creator<PayPalAccountNonce> CREATOR = new Creator<PayPalAccountNonce>() {
        public PayPalAccountNonce createFromParcel(Parcel source) {
            return new PayPalAccountNonce(source);
        }

        public PayPalAccountNonce[] newArray(int size) {
            return new PayPalAccountNonce[size];
        }
    };
    private static final String CREDIT_FINANCING_KEY = "creditFinancingOffered";
    private static final String DETAILS_KEY = "details";
    private static final String EMAIL_KEY = "email";
    private static final String FIRST_NAME_KEY = "firstName";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String PAYER_ID_KEY = "payerId";
    private static final String PAYER_INFO_KEY = "payerInfo";
    private static final String PHONE_KEY = "phone";
    private static final String SHIPPING_ADDRESS_KEY = "shippingAddress";
    protected static final String TYPE = "PayPalAccount";
    private PostalAddress mBillingAddress;
    private String mClientMetadataId;
    private PayPalCreditFinancing mCreditFinancing;
    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mPayerId;
    private String mPhone;
    private PostalAddress mShippingAddress;

    public static PayPalAccountNonce fromJson(String json) throws JSONException {
        PayPalAccountNonce payPalAccountNonce = new PayPalAccountNonce();
        payPalAccountNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, json));
        return payPalAccountNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        JSONObject billingAddress;
        super.fromJson(json);
        JSONObject details = json.getJSONObject("details");
        this.mEmail = Json.optString(details, "email", null);
        this.mClientMetadataId = Json.optString(details, CLIENT_METADATA_ID_KEY, null);
        try {
            if (details.has(CREDIT_FINANCING_KEY)) {
                this.mCreditFinancing = PayPalCreditFinancing.fromJson(details.getJSONObject(CREDIT_FINANCING_KEY));
            }
            JSONObject payerInfo = details.getJSONObject(PAYER_INFO_KEY);
            if (payerInfo.has(ACCOUNT_ADDRESS_KEY)) {
                billingAddress = payerInfo.optJSONObject(ACCOUNT_ADDRESS_KEY);
            } else {
                billingAddress = payerInfo.optJSONObject(BILLING_ADDRESS_KEY);
            }
            JSONObject shippingAddress = payerInfo.optJSONObject(SHIPPING_ADDRESS_KEY);
            this.mBillingAddress = PostalAddress.fromJson(billingAddress);
            this.mShippingAddress = PostalAddress.fromJson(shippingAddress);
            this.mFirstName = Json.optString(payerInfo, FIRST_NAME_KEY, "");
            this.mLastName = Json.optString(payerInfo, LAST_NAME_KEY, "");
            this.mPhone = Json.optString(payerInfo, "phone", "");
            this.mPayerId = Json.optString(payerInfo, PAYER_ID_KEY, "");
            if (this.mEmail == null) {
                this.mEmail = Json.optString(payerInfo, "email", null);
            }
        } catch (JSONException e) {
            this.mBillingAddress = new PostalAddress();
            this.mShippingAddress = new PostalAddress();
        }
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getDescription() {
        if (!TextUtils.equals(super.getDescription(), "PayPal") || TextUtils.isEmpty(getEmail())) {
            return super.getDescription();
        }
        return getEmail();
    }

    public String getTypeLabel() {
        return "PayPal";
    }

    public PostalAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public PostalAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getClientMetadataId() {
        return this.mClientMetadataId;
    }

    public String getPayerId() {
        return this.mPayerId;
    }

    public PayPalCreditFinancing getCreditFinancing() {
        return this.mCreditFinancing;
    }

    public PayPalAccountNonce() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mClientMetadataId);
        dest.writeParcelable(this.mBillingAddress, flags);
        dest.writeParcelable(this.mShippingAddress, flags);
        dest.writeString(this.mFirstName);
        dest.writeString(this.mLastName);
        dest.writeString(this.mEmail);
        dest.writeString(this.mPhone);
        dest.writeString(this.mPayerId);
        dest.writeParcelable(this.mCreditFinancing, flags);
    }

    private PayPalAccountNonce(Parcel in) {
        super(in);
        this.mClientMetadataId = in.readString();
        this.mBillingAddress = (PostalAddress) in.readParcelable(PostalAddress.class.getClassLoader());
        this.mShippingAddress = (PostalAddress) in.readParcelable(PostalAddress.class.getClassLoader());
        this.mFirstName = in.readString();
        this.mLastName = in.readString();
        this.mEmail = in.readString();
        this.mPhone = in.readString();
        this.mPayerId = in.readString();
        this.mCreditFinancing = (PayPalCreditFinancing) in.readParcelable(PayPalCreditFinancing.class.getClassLoader());
    }
}
