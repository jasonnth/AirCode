package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientToken extends Authorization {
    private static final String AUTHORIZATION_FINGERPRINT_KEY = "authorizationFingerprint";
    protected static final String BASE_64_MATCHER = "([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)";
    private static final String CONFIG_URL_KEY = "configUrl";
    public static final Creator<ClientToken> CREATOR = new Creator<ClientToken>() {
        public ClientToken createFromParcel(Parcel source) {
            return new ClientToken(source);
        }

        public ClientToken[] newArray(int size) {
            return new ClientToken[size];
        }
    };
    private String mAuthorizationFingerprint;
    private String mConfigUrl;

    ClientToken(String clientTokenString) throws InvalidArgumentException {
        super(clientTokenString);
        try {
            if (clientTokenString.matches(BASE_64_MATCHER)) {
                clientTokenString = new String(Base64.decode(clientTokenString, 0));
            }
            JSONObject jsonObject = new JSONObject(clientTokenString);
            this.mConfigUrl = jsonObject.getString(CONFIG_URL_KEY);
            this.mAuthorizationFingerprint = jsonObject.getString(AUTHORIZATION_FINGERPRINT_KEY);
        } catch (NullPointerException | JSONException e) {
            throw new InvalidArgumentException("Client token was invalid");
        }
    }

    public String getConfigUrl() {
        return this.mConfigUrl;
    }

    public String getAuthorizationFingerprint() {
        return this.mAuthorizationFingerprint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mConfigUrl);
        dest.writeString(this.mAuthorizationFingerprint);
    }

    protected ClientToken(Parcel in) {
        super(in);
        this.mConfigUrl = in.readString();
        this.mAuthorizationFingerprint = in.readString();
    }
}
