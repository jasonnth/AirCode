package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class VisaCheckoutNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "visaCheckoutCards";
    private static final String BILLING_ADDRESS_KEY = "billingAddress";
    private static final String CALL_ID_KEY = "callId";
    private static final String CARD_DETAILS_KEY = "details";
    private static final String CARD_TYPE_KEY = "cardType";
    public static final Creator<VisaCheckoutNonce> CREATOR = new Creator<VisaCheckoutNonce>() {
        public VisaCheckoutNonce createFromParcel(Parcel in) {
            return new VisaCheckoutNonce(in);
        }

        public VisaCheckoutNonce[] newArray(int size) {
            return new VisaCheckoutNonce[size];
        }
    };
    private static final String LAST_TWO_KEY = "lastTwo";
    private static final String SHIPPING_ADDRESS_KEY = "shippingAddress";
    protected static final String TYPE = "VisaCheckoutCard";
    private static final String USER_DATA_KEY = "userData";
    private VisaCheckoutAddress mBillingAddress;
    private String mCallId;
    private String mCardType;
    private String mLastTwo;
    private VisaCheckoutAddress mShippingAddress;
    private VisaCheckoutUserData mUserData;

    public static VisaCheckoutNonce fromJson(String json) throws JSONException {
        VisaCheckoutNonce visaCheckoutNonce = new VisaCheckoutNonce();
        visaCheckoutNonce.fromJson(PaymentMethodNonce.getJsonObjectForType(API_RESOURCE_KEY, json));
        return visaCheckoutNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        super.fromJson(json);
        JSONObject details = json.getJSONObject("details");
        this.mLastTwo = details.getString(LAST_TWO_KEY);
        this.mCardType = details.getString(CARD_TYPE_KEY);
        this.mBillingAddress = VisaCheckoutAddress.fromJson(json.getJSONObject(BILLING_ADDRESS_KEY));
        this.mShippingAddress = VisaCheckoutAddress.fromJson(json.getJSONObject(SHIPPING_ADDRESS_KEY));
        this.mUserData = VisaCheckoutUserData.fromJson(json.getJSONObject(USER_DATA_KEY));
        this.mCallId = Json.optString(json, CALL_ID_KEY, "");
    }

    public String getLastTwo() {
        return this.mLastTwo;
    }

    public String getCardType() {
        return this.mCardType;
    }

    public VisaCheckoutAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public VisaCheckoutAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public VisaCheckoutUserData getUserData() {
        return this.mUserData;
    }

    public String getCallId() {
        return this.mCallId;
    }

    public String getTypeLabel() {
        return "Visa Checkout";
    }

    public VisaCheckoutNonce() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mLastTwo);
        dest.writeString(this.mCardType);
        dest.writeParcelable(this.mBillingAddress, flags);
        dest.writeParcelable(this.mShippingAddress, flags);
        dest.writeParcelable(this.mUserData, flags);
        dest.writeString(this.mCallId);
    }

    protected VisaCheckoutNonce(Parcel in) {
        super(in);
        this.mLastTwo = in.readString();
        this.mCardType = in.readString();
        this.mBillingAddress = (VisaCheckoutAddress) in.readParcelable(VisaCheckoutAddress.class.getClassLoader());
        this.mShippingAddress = (VisaCheckoutAddress) in.readParcelable(VisaCheckoutAddress.class.getClassLoader());
        this.mUserData = (VisaCheckoutUserData) in.readParcelable(VisaCheckoutUserData.class.getClassLoader());
        this.mCallId = in.readString();
    }
}
