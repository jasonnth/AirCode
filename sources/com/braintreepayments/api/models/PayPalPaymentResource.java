package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalPaymentResource {
    private String mRedirectUrl;

    public PayPalPaymentResource redirectUrl(String redirectUrl) {
        this.mRedirectUrl = redirectUrl;
        return this;
    }

    public String getRedirectUrl() {
        return this.mRedirectUrl;
    }

    public static PayPalPaymentResource fromJson(String jsonString) throws JSONException {
        JSONObject json = new JSONObject(jsonString);
        PayPalPaymentResource payPalPaymentResource = new PayPalPaymentResource();
        JSONObject redirectJson = json.optJSONObject("paymentResource");
        if (redirectJson != null) {
            payPalPaymentResource.redirectUrl(Json.optString(redirectJson, "redirectUrl", ""));
        } else {
            payPalPaymentResource.redirectUrl(Json.optString(json.optJSONObject("agreementSetup"), "approvalUrl", ""));
        }
        return payPalPaymentResource;
    }
}
