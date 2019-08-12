package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenJumioCredential implements Parcelable {
    @JsonProperty("api_secret")
    protected String mApiSecret;
    @JsonProperty("merchant_api_token")
    protected String mApiToken;
    @JsonProperty("merchant_id_scan_reference")
    protected String mMerchantScanReference;

    protected GenJumioCredential(String apiToken, String apiSecret, String merchantScanReference) {
        this();
        this.mApiToken = apiToken;
        this.mApiSecret = apiSecret;
        this.mMerchantScanReference = merchantScanReference;
    }

    protected GenJumioCredential() {
    }

    public String getApiToken() {
        return this.mApiToken;
    }

    @JsonProperty("merchant_api_token")
    public void setApiToken(String value) {
        this.mApiToken = value;
    }

    public String getApiSecret() {
        return this.mApiSecret;
    }

    @JsonProperty("api_secret")
    public void setApiSecret(String value) {
        this.mApiSecret = value;
    }

    public String getMerchantScanReference() {
        return this.mMerchantScanReference;
    }

    @JsonProperty("merchant_id_scan_reference")
    public void setMerchantScanReference(String value) {
        this.mMerchantScanReference = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mApiToken);
        parcel.writeString(this.mApiSecret);
        parcel.writeString(this.mMerchantScanReference);
    }

    public void readFromParcel(Parcel source) {
        this.mApiToken = source.readString();
        this.mApiSecret = source.readString();
        this.mMerchantScanReference = source.readString();
    }
}
