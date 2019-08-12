package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.ThreeDSecureAuthenticationResponse;

public class ThreeDSecure {
    protected static void onActivityResult(BraintreeFragment fragment, int resultCode, Intent data) {
        if (resultCode == -1) {
            ThreeDSecureAuthenticationResponse authenticationResponse = (ThreeDSecureAuthenticationResponse) data.getParcelableExtra("com.braintreepayments.api.EXTRA_THREE_D_SECURE_RESULT");
            if (authenticationResponse.isSuccess()) {
                fragment.postCallback((PaymentMethodNonce) authenticationResponse.getCardNonce());
            } else if (authenticationResponse.getException() != null) {
                fragment.postCallback((Exception) new BraintreeException(authenticationResponse.getException()));
            } else {
                fragment.postCallback((Exception) new ErrorWithResponse(422, authenticationResponse.getErrors()));
            }
        }
    }
}
