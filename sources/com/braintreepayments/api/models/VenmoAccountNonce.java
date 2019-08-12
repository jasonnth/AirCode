package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class VenmoAccountNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "venmoAccounts";
    public static final Creator<VenmoAccountNonce> CREATOR = new Creator<VenmoAccountNonce>() {
        public VenmoAccountNonce createFromParcel(Parcel in) {
            return new VenmoAccountNonce(in);
        }

        public VenmoAccountNonce[] newArray(int size) {
            return new VenmoAccountNonce[size];
        }
    };
    protected static final String TYPE = "VenmoAccount";
    private static final String VENMO_DETAILS_KEY = "details";
    private static final String VENMO_USERNAME_KEY = "username";
    private String mUsername;

    public VenmoAccountNonce(String nonce, String description, String username) {
        this.mNonce = nonce;
        this.mDescription = description;
        this.mUsername = username;
    }

    public static VenmoAccountNonce fromJson(String json) throws JSONException {
        VenmoAccountNonce venmoAccountNonce = new VenmoAccountNonce();
        venmoAccountNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, json));
        return venmoAccountNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        super.fromJson(json);
        this.mUsername = json.getJSONObject("details").getString(VENMO_USERNAME_KEY);
        this.mDescription = this.mUsername;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public String getTypeLabel() {
        return "Venmo";
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mUsername);
    }

    public VenmoAccountNonce() {
    }

    protected VenmoAccountNonce(Parcel in) {
        super(in);
        this.mUsername = in.readString();
    }
}
