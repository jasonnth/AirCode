package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PaymentMethodNonce implements Parcelable {
    private static final String DESCRIPTION_KEY = "description";
    private static final String PAYMENT_METHOD_DEFAULT_KEY = "default";
    private static final String PAYMENT_METHOD_NONCE_COLLECTION_KEY = "paymentMethods";
    private static final String PAYMENT_METHOD_NONCE_KEY = "nonce";
    private static final String PAYMENT_METHOD_TYPE_KEY = "type";
    protected boolean mDefault;
    protected String mDescription;
    protected String mNonce;

    public abstract String getTypeLabel();

    protected static JSONObject getJsonObjectForType(String apiResourceKey, String response) throws JSONException {
        return new JSONObject(response).getJSONArray(apiResourceKey).getJSONObject(0);
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        this.mNonce = json.getString(PAYMENT_METHOD_NONCE_KEY);
        this.mDescription = json.getString("description");
        this.mDefault = json.optBoolean(PAYMENT_METHOD_DEFAULT_KEY, false);
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isDefault() {
        return this.mDefault;
    }

    public static List<PaymentMethodNonce> parsePaymentMethodNonces(String jsonBody) throws JSONException {
        JSONArray paymentMethods = new JSONObject(jsonBody).getJSONArray(PAYMENT_METHOD_NONCE_COLLECTION_KEY);
        if (paymentMethods == null) {
            return Collections.emptyList();
        }
        List<PaymentMethodNonce> paymentMethodsNonces = new ArrayList<>();
        for (int i = 0; i < paymentMethods.length(); i++) {
            JSONObject json = paymentMethods.getJSONObject(i);
            PaymentMethodNonce paymentMethodNonce = parsePaymentMethodNonces(json, json.getString("type"));
            if (paymentMethodNonce != null) {
                paymentMethodsNonces.add(paymentMethodNonce);
            }
        }
        return paymentMethodsNonces;
    }

    public static PaymentMethodNonce parsePaymentMethodNonces(String json, String type) throws JSONException {
        return parsePaymentMethodNonces(new JSONObject(json), type);
    }

    public static PaymentMethodNonce parsePaymentMethodNonces(JSONObject json, String type) throws JSONException {
        char c = 65535;
        switch (type.hashCode()) {
            case -1807185524:
                if (type.equals("VenmoAccount")) {
                    c = 3;
                    break;
                }
                break;
            case -1460015479:
                if (type.equals("AndroidPayCard")) {
                    c = 2;
                    break;
                }
                break;
            case -650599305:
                if (type.equals("VisaCheckoutCard")) {
                    c = 4;
                    break;
                }
                break;
            case 1212590010:
                if (type.equals("PayPalAccount")) {
                    c = 1;
                    break;
                }
                break;
            case 1428640201:
                if (type.equals("CreditCard")) {
                    c = 0;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (json.has("creditCards")) {
                    return CardNonce.fromJson(json.toString());
                }
                CardNonce cardNonce = new CardNonce();
                cardNonce.fromJson(json);
                return cardNonce;
            case 1:
                if (json.has("paypalAccounts")) {
                    return PayPalAccountNonce.fromJson(json.toString());
                }
                PayPalAccountNonce payPalAccountNonce = new PayPalAccountNonce();
                payPalAccountNonce.fromJson(json);
                return payPalAccountNonce;
            case 2:
                if (json.has("androidPayCards")) {
                    return AndroidPayCardNonce.fromJson(json.toString());
                }
                AndroidPayCardNonce androidPayCardNonce = new AndroidPayCardNonce();
                androidPayCardNonce.fromJson(json);
                return androidPayCardNonce;
            case 3:
                if (json.has("venmoAccounts")) {
                    return VenmoAccountNonce.fromJson(json.toString());
                }
                VenmoAccountNonce venmoAccountNonce = new VenmoAccountNonce();
                venmoAccountNonce.fromJson(json);
                return venmoAccountNonce;
            case 4:
                if (json.has("visaCheckoutCards")) {
                    return VisaCheckoutNonce.fromJson(json.toString());
                }
                VisaCheckoutNonce visaCheckoutNonce = new VisaCheckoutNonce();
                visaCheckoutNonce.fromJson(json);
                return visaCheckoutNonce;
            default:
                return null;
        }
    }

    public PaymentMethodNonce() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mNonce);
        dest.writeString(this.mDescription);
        dest.writeByte(this.mDefault ? (byte) 1 : 0);
    }

    protected PaymentMethodNonce(Parcel in) {
        this.mNonce = in.readString();
        this.mDescription = in.readString();
        this.mDefault = in.readByte() > 0;
    }
}
