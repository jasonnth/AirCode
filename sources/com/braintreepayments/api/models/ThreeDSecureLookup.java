package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureLookup implements Parcelable {
    private static final String ACS_URL_KEY = "acsUrl";
    private static final String CARD_NONCE_KEY = "paymentMethod";
    public static final Creator<ThreeDSecureLookup> CREATOR = new Creator<ThreeDSecureLookup>() {
        public ThreeDSecureLookup createFromParcel(Parcel source) {
            return new ThreeDSecureLookup(source);
        }

        public ThreeDSecureLookup[] newArray(int size) {
            return new ThreeDSecureLookup[size];
        }
    };
    private static final String LOOKUP_KEY = "lookup";
    private static final String MD_KEY = "md";
    private static final String PA_REQ_KEY = "pareq";
    private static final String TERM_URL_KEY = "termUrl";
    private String mAcsUrl;
    private CardNonce mCardNonce;
    private String mMd;
    private String mPareq;
    private String mTermUrl;

    public static ThreeDSecureLookup fromJson(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        ThreeDSecureLookup lookup = new ThreeDSecureLookup();
        CardNonce cardNonce = new CardNonce();
        cardNonce.fromJson(json.getJSONObject(CARD_NONCE_KEY));
        lookup.mCardNonce = cardNonce;
        JSONObject lookupJson = json.getJSONObject(LOOKUP_KEY);
        if (lookupJson.isNull(ACS_URL_KEY)) {
            lookup.mAcsUrl = null;
        } else {
            lookup.mAcsUrl = lookupJson.getString(ACS_URL_KEY);
        }
        lookup.mMd = lookupJson.getString(MD_KEY);
        lookup.mTermUrl = lookupJson.getString(TERM_URL_KEY);
        lookup.mPareq = lookupJson.getString(PA_REQ_KEY);
        return lookup;
    }

    public CardNonce getCardNonce() {
        return this.mCardNonce;
    }

    public String getAcsUrl() {
        return this.mAcsUrl;
    }

    public String getMd() {
        return this.mMd;
    }

    public String getTermUrl() {
        return this.mTermUrl;
    }

    public String getPareq() {
        return this.mPareq;
    }

    public ThreeDSecureLookup() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mCardNonce, flags);
        dest.writeString(this.mAcsUrl);
        dest.writeString(this.mMd);
        dest.writeString(this.mTermUrl);
        dest.writeString(this.mPareq);
    }

    private ThreeDSecureLookup(Parcel in) {
        this.mCardNonce = (CardNonce) in.readParcelable(CardNonce.class.getClassLoader());
        this.mAcsUrl = in.readString();
        this.mMd = in.readString();
        this.mTermUrl = in.readString();
        this.mPareq = in.readString();
    }
}
