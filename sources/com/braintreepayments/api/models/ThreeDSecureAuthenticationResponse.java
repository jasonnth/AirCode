package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureAuthenticationResponse implements Parcelable {
    public static final Creator<ThreeDSecureAuthenticationResponse> CREATOR = new Creator<ThreeDSecureAuthenticationResponse>() {
        public ThreeDSecureAuthenticationResponse createFromParcel(Parcel source) {
            return new ThreeDSecureAuthenticationResponse(source);
        }

        public ThreeDSecureAuthenticationResponse[] newArray(int size) {
            return new ThreeDSecureAuthenticationResponse[size];
        }
    };
    private static final String PAYMENT_METHOD_KEY = "paymentMethod";
    private static final String SUCCESS_KEY = "success";
    private CardNonce mCardNonce;
    private String mErrors;
    private String mException;
    private boolean mSuccess;

    public static ThreeDSecureAuthenticationResponse fromJson(String jsonString) {
        ThreeDSecureAuthenticationResponse authenticationResponse = new ThreeDSecureAuthenticationResponse();
        try {
            JSONObject json = new JSONObject(jsonString);
            JSONObject cardJson = json.optJSONObject(PAYMENT_METHOD_KEY);
            if (cardJson != null) {
                CardNonce cardNonce = new CardNonce();
                cardNonce.fromJson(cardJson);
                authenticationResponse.mCardNonce = cardNonce;
            }
            authenticationResponse.mSuccess = json.getBoolean("success");
            if (!authenticationResponse.mSuccess) {
                authenticationResponse.mErrors = jsonString;
            }
        } catch (JSONException e) {
            authenticationResponse.mSuccess = false;
        }
        return authenticationResponse;
    }

    public static ThreeDSecureAuthenticationResponse fromException(String exception) {
        ThreeDSecureAuthenticationResponse authenticationResponse = new ThreeDSecureAuthenticationResponse();
        authenticationResponse.mSuccess = false;
        authenticationResponse.mException = exception;
        return authenticationResponse;
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public CardNonce getCardNonce() {
        return this.mCardNonce;
    }

    public String getErrors() {
        return this.mErrors;
    }

    public String getException() {
        return this.mException;
    }

    public ThreeDSecureAuthenticationResponse() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.mSuccess ? (byte) 1 : 0);
        dest.writeParcelable(this.mCardNonce, flags);
        dest.writeString(this.mErrors);
        dest.writeString(this.mException);
    }

    private ThreeDSecureAuthenticationResponse(Parcel in) {
        this.mSuccess = in.readByte() != 0;
        this.mCardNonce = (CardNonce) in.readParcelable(CardNonce.class.getClassLoader());
        this.mErrors = in.readString();
        this.mException = in.readString();
    }
}
