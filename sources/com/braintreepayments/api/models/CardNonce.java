package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class CardNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "creditCards";
    private static final String CARD_DETAILS_KEY = "details";
    private static final String CARD_TYPE_KEY = "cardType";
    public static final Creator<CardNonce> CREATOR = new Creator<CardNonce>() {
        public CardNonce createFromParcel(Parcel source) {
            return new CardNonce(source);
        }

        public CardNonce[] newArray(int size) {
            return new CardNonce[size];
        }
    };
    private static final String LAST_TWO_KEY = "lastTwo";
    private static final String THREE_D_SECURE_INFO_KEY = "threeDSecureInfo";
    protected static final String TYPE = "CreditCard";
    private String mCardType;
    private String mLastTwo;
    private ThreeDSecureInfo mThreeDSecureInfo;

    public static CardNonce fromJson(String json) throws JSONException {
        CardNonce cardNonce = new CardNonce();
        cardNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, json));
        return cardNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        super.fromJson(json);
        this.mThreeDSecureInfo = ThreeDSecureInfo.fromJson(json.optJSONObject(THREE_D_SECURE_INFO_KEY));
        JSONObject details = json.getJSONObject("details");
        this.mLastTwo = details.getString(LAST_TWO_KEY);
        this.mCardType = details.getString(CARD_TYPE_KEY);
    }

    public String getTypeLabel() {
        return this.mCardType;
    }

    public String getCardType() {
        return this.mCardType;
    }

    public String getLastTwo() {
        return this.mLastTwo;
    }

    public ThreeDSecureInfo getThreeDSecureInfo() {
        return this.mThreeDSecureInfo;
    }

    public CardNonce() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.mThreeDSecureInfo, flags);
        dest.writeString(this.mCardType);
        dest.writeString(this.mLastTwo);
    }

    protected CardNonce(Parcel in) {
        super(in);
        this.mThreeDSecureInfo = (ThreeDSecureInfo) in.readParcelable(ThreeDSecureInfo.class.getClassLoader());
        this.mCardType = in.readString();
        this.mLastTwo = in.readString();
    }
}
