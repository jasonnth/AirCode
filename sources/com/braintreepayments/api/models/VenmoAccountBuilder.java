package com.braintreepayments.api.models;

import org.json.JSONException;
import org.json.JSONObject;

public class VenmoAccountBuilder extends PaymentMethodBuilder<VenmoAccountBuilder> {
    private final String NONCE_KEY = "nonce";
    private final String VENMO_ACCOUNT_KEY = "venmoAccount";
    private String mNonce;

    public VenmoAccountBuilder nonce(String nonce) {
        this.mNonce = nonce;
        return this;
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject base, JSONObject paymentMethodNonceJson) throws JSONException {
        paymentMethodNonceJson.put("nonce", this.mNonce);
        base.put("venmoAccount", paymentMethodNonceJson);
    }

    public String getApiPath() {
        return "venmo_accounts";
    }

    public String getResponsePaymentMethodType() {
        return "VenmoAccount";
    }
}
