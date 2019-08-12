package com.braintreepayments.api;

import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import org.json.JSONException;

class TokenizationClient {
    static void tokenize(final BraintreeFragment fragment, final PaymentMethodBuilder paymentMethodBuilder, final PaymentMethodNonceCallback callback) {
        paymentMethodBuilder.setSessionId(fragment.getSessionId());
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                fragment.getHttpClient().post(TokenizationClient.versionedPath("payment_methods/" + paymentMethodBuilder.getApiPath()), paymentMethodBuilder.build(), new HttpResponseCallback() {
                    public void success(String responseBody) {
                        try {
                            callback.success(PaymentMethodNonce.parsePaymentMethodNonces(responseBody, paymentMethodBuilder.getResponsePaymentMethodType()));
                        } catch (JSONException e) {
                            callback.failure(e);
                        }
                    }

                    public void failure(Exception exception) {
                        callback.failure(exception);
                    }
                });
            }
        });
    }

    static String versionedPath(String path) {
        return "/v1/" + path;
    }
}
