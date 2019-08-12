package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.VisaCheckoutBuilder;
import com.braintreepayments.api.models.VisaCheckoutNonce;
import com.visa.checkout.VisaPaymentSummary;

public class VisaCheckout {
    static void onActivityResult(BraintreeFragment fragment, int resultCode, Intent data) {
        if (resultCode == 0) {
            fragment.sendAnalyticsEvent("visacheckout.result.cancelled");
        } else if (resultCode != -1 || data == null) {
            fragment.postCallback((Exception) new BraintreeException("Visa Checkout responded with an invalid resultCode: " + resultCode));
            fragment.sendAnalyticsEvent("visacheckout.result.failed");
        } else {
            tokenize(fragment, data.getParcelableExtra("payment_summary"));
            fragment.sendAnalyticsEvent("visacheckout.result.succeeded");
        }
    }

    static void tokenize(final BraintreeFragment fragment, VisaPaymentSummary visaPaymentSummary) {
        TokenizationClient.tokenize(fragment, new VisaCheckoutBuilder(visaPaymentSummary), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                VisaCheckoutNonce visaCheckoutNonce = (VisaCheckoutNonce) paymentMethodNonce;
                fragment.postCallback(paymentMethodNonce);
                fragment.sendAnalyticsEvent("visacheckout.tokenize.succeeded");
            }

            public void failure(Exception exception) {
                fragment.postCallback(exception);
                fragment.sendAnalyticsEvent("visacheckout.tokenize.failed");
            }
        });
    }
}
