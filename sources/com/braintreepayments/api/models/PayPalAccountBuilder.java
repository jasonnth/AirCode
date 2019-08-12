package com.braintreepayments.api.models;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalAccountBuilder extends PaymentMethodBuilder<PayPalAccountBuilder> {
    private String mClientMetadataId;
    private String mIntent;
    private JSONObject mOneTouchCoreData = new JSONObject();

    public PayPalAccountBuilder clientMetadataId(String clientMetadataId) {
        this.mClientMetadataId = clientMetadataId;
        return this;
    }

    public PayPalAccountBuilder oneTouchCoreData(JSONObject otcData) {
        if (otcData != null) {
            this.mOneTouchCoreData = otcData;
        }
        return this;
    }

    public PayPalAccountBuilder intent(String intent) {
        this.mIntent = intent;
        return this;
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject base, JSONObject paymentMethodNonceJson) throws JSONException {
        paymentMethodNonceJson.put("correlationId", this.mClientMetadataId);
        paymentMethodNonceJson.put("intent", this.mIntent);
        Iterator<String> otcKeyIterator = this.mOneTouchCoreData.keys();
        while (otcKeyIterator.hasNext()) {
            String otcKey = (String) otcKeyIterator.next();
            paymentMethodNonceJson.put(otcKey, this.mOneTouchCoreData.get(otcKey));
        }
        base.put("paypalAccount", paymentMethodNonceJson);
    }

    public String getApiPath() {
        return "paypal_accounts";
    }

    public String getResponsePaymentMethodType() {
        return "PayPalAccount";
    }
}
