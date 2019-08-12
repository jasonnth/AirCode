package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.exception.InvalidEncryptionDataException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.JSONException;

public abstract class Request<T extends Request<T>> implements Parcelable {
    private String mCancelUrl;
    private String mClientId;
    private String mClientMetadataId;
    private String mEnvironment;
    private String mSuccessUrl;

    public abstract Recipe getBrowserSwitchRecipe(OtcConfiguration otcConfiguration);

    public abstract String getBrowserSwitchUrl(Context context, OtcConfiguration otcConfiguration) throws CertificateException, UnsupportedEncodingException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, JSONException, BadPaddingException, InvalidEncryptionDataException, InvalidKeyException;

    public abstract Recipe getRecipeToExecute(Context context, OtcConfiguration otcConfiguration);

    public abstract Result parseBrowserResponse(ContextInspector contextInspector, Uri uri);

    public abstract void trackFpti(Context context, TrackingPoint trackingPoint, Protocol protocol);

    public abstract boolean validateV1V2Response(ContextInspector contextInspector, Bundle bundle);

    public T environment(String environment) {
        this.mEnvironment = environment;
        return this;
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public T clientMetadataId(String clientMetadataId) {
        this.mClientMetadataId = clientMetadataId;
        return this;
    }

    public String getClientMetadataId() {
        return this.mClientMetadataId;
    }

    public T clientId(String clientId) {
        this.mClientId = clientId;
        return this;
    }

    public String getClientId() {
        return this.mClientId;
    }

    public T cancelUrl(String scheme, String host) {
        this.mCancelUrl = scheme + "://" + redirectURLHostAndPath() + host;
        return this;
    }

    public String getCancelUrl() {
        return this.mCancelUrl;
    }

    public T successUrl(String scheme, String host) {
        this.mSuccessUrl = scheme + "://" + redirectURLHostAndPath() + host;
        return this;
    }

    public String getSuccessUrl() {
        return this.mSuccessUrl;
    }

    private static String redirectURLHostAndPath() {
        return "onetouch/v1/";
    }

    protected Request() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mEnvironment);
        dest.writeString(this.mClientId);
        dest.writeString(this.mClientMetadataId);
        dest.writeString(this.mCancelUrl);
        dest.writeString(this.mSuccessUrl);
    }

    protected Request(Parcel source) {
        this.mEnvironment = source.readString();
        this.mClientId = source.readString();
        this.mClientMetadataId = source.readString();
        this.mCancelUrl = source.readString();
        this.mSuccessUrl = source.readString();
    }
}
