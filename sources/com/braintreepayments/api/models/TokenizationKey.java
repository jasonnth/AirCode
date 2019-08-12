package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

public class TokenizationKey extends Authorization implements Parcelable {
    public static final Creator<TokenizationKey> CREATOR = new Creator<TokenizationKey>() {
        public TokenizationKey createFromParcel(Parcel source) {
            return new TokenizationKey(source);
        }

        public TokenizationKey[] newArray(int size) {
            return new TokenizationKey[size];
        }
    };
    protected static final String MATCHER = "^[a-zA-Z0-9]+_[a-zA-Z0-9]+_[a-zA-Z0-9_]+$";
    private final String mEnvironment;
    private final String mMerchantId;
    private final String mUrl;

    private enum BraintreeEnvironment {
        DEVELOPMENT("development", "http://10.0.2.2:3000/"),
        SANDBOX("sandbox", "https://api.sandbox.braintreegateway.com/"),
        PRODUCTION("production", "https://api.braintreegateway.com/");
        
        private String mEnvironment;
        private String mUrl;

        private BraintreeEnvironment(String environment, String url) {
            this.mEnvironment = environment;
            this.mUrl = url;
        }

        static String getUrl(String environment) throws InvalidArgumentException {
            BraintreeEnvironment[] values;
            for (BraintreeEnvironment braintreeEnvironment : values()) {
                if (braintreeEnvironment.mEnvironment.equals(environment)) {
                    return braintreeEnvironment.mUrl;
                }
            }
            throw new InvalidArgumentException("Tokenization Key contained invalid environment");
        }
    }

    TokenizationKey(String tokenizationKey) throws InvalidArgumentException {
        super(tokenizationKey);
        String[] tokenizationKeyParts = tokenizationKey.split("_", 3);
        this.mEnvironment = tokenizationKeyParts[0];
        this.mMerchantId = tokenizationKeyParts[2];
        this.mUrl = BraintreeEnvironment.getUrl(this.mEnvironment) + "merchants/" + this.mMerchantId + "/client_api/";
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getConfigUrl() {
        return this.mUrl + "v1/configuration";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mEnvironment);
        dest.writeString(this.mMerchantId);
        dest.writeString(this.mUrl);
    }

    protected TokenizationKey(Parcel in) {
        super(in);
        this.mEnvironment = in.readString();
        this.mMerchantId = in.readString();
        this.mUrl = in.readString();
    }
}
