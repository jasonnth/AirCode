package com.braintreepayments.api.models;

import com.visa.checkout.VisaPaymentSummary;
import org.json.JSONException;
import org.json.JSONObject;

public class VisaCheckoutBuilder extends PaymentMethodBuilder<VisaCheckoutBuilder> {
    private String mCallId;
    private String mEncryptedKey;
    private String mEncryptedPaymentData;

    public VisaCheckoutBuilder(VisaPaymentSummary visaPaymentSummary) {
        if (visaPaymentSummary != null) {
            this.mCallId = visaPaymentSummary.getCallId();
            this.mEncryptedKey = visaPaymentSummary.getEncKey();
            this.mEncryptedPaymentData = visaPaymentSummary.getEncPaymentData();
        }
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject base, JSONObject paymentMethodNonceJson) throws JSONException {
        paymentMethodNonceJson.put("callId", this.mCallId);
        paymentMethodNonceJson.put("encryptedKey", this.mEncryptedKey);
        paymentMethodNonceJson.put("encryptedPaymentData", this.mEncryptedPaymentData);
        base.put("visaCheckoutCard", paymentMethodNonceJson);
    }

    public String getApiPath() {
        return "visa_checkout_cards";
    }

    public String getResponsePaymentMethodType() {
        return "VisaCheckoutCard";
    }
}
