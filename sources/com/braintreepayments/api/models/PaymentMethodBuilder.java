package com.braintreepayments.api.models;

import android.os.Parcel;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PaymentMethodBuilder<T> {
    protected static final String OPTIONS_KEY = "options";
    private static final String VALIDATE_KEY = "validate";
    private String mIntegration = getDefaultIntegration();
    private String mSessionId;
    private String mSource = getDefaultSource();
    private boolean mValidate;
    private boolean mValidateSet;

    /* access modifiers changed from: protected */
    public abstract void build(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException;

    public abstract String getApiPath();

    public abstract String getResponsePaymentMethodType();

    public PaymentMethodBuilder() {
    }

    public T integration(String integration) {
        this.mIntegration = integration;
        return this;
    }

    public T source(String source) {
        this.mSource = source;
        return this;
    }

    public T validate(boolean validate) {
        this.mValidate = validate;
        this.mValidateSet = true;
        return this;
    }

    public T setSessionId(String sessionId) {
        this.mSessionId = sessionId;
        return this;
    }

    public String build() {
        JSONObject json = new JSONObject();
        JSONObject optionsJson = new JSONObject();
        JSONObject paymentMethodNonceJson = new JSONObject();
        try {
            json.put("_meta", new MetadataBuilder().sessionId(this.mSessionId).source(this.mSource).integration(this.mIntegration).build());
            if (this.mValidateSet) {
                optionsJson.put(VALIDATE_KEY, this.mValidate);
                paymentMethodNonceJson.put(OPTIONS_KEY, optionsJson);
            }
            build(json, paymentMethodNonceJson);
        } catch (JSONException e) {
        }
        return json.toString();
    }

    protected PaymentMethodBuilder(Parcel in) {
        boolean z;
        boolean z2 = true;
        this.mIntegration = in.readString();
        this.mSource = in.readString();
        if (in.readByte() > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mValidate = z;
        if (in.readByte() <= 0) {
            z2 = false;
        }
        this.mValidateSet = z2;
        this.mSessionId = in.readString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b;
        byte b2 = 1;
        dest.writeString(this.mIntegration);
        dest.writeString(this.mSource);
        if (this.mValidate) {
            b = 1;
        } else {
            b = 0;
        }
        dest.writeByte(b);
        if (!this.mValidateSet) {
            b2 = 0;
        }
        dest.writeByte(b2);
        dest.writeString(this.mSessionId);
    }

    /* access modifiers changed from: protected */
    public String getDefaultSource() {
        return "form";
    }

    /* access modifiers changed from: protected */
    public String getDefaultIntegration() {
        return "custom";
    }
}
