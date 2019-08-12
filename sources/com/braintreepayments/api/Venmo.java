package com.braintreepayments.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCallback;
import com.braintreepayments.api.internal.AppHelper;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.internal.SignatureVerification;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.VenmoAccountBuilder;
import com.braintreepayments.api.models.VenmoAccountNonce;

public class Venmo {
    public static boolean isVenmoInstalled(Context context) {
        return AppHelper.isIntentAvailable(context, getVenmoIntent()) && SignatureVerification.isSignatureValid(context, "com.venmo", "CN=Andrew Kortina,OU=Engineering,O=Venmo,L=Philadelphia,ST=PA,C=US", "CN=Andrew Kortina,OU=Engineering,O=Venmo,L=Philadelphia,ST=PA,C=US", -129711843);
    }

    private static Intent getVenmoIntent() {
        return new Intent().setComponent(new ComponentName("com.venmo", "com.venmo.controller.SetupMerchantActivity"));
    }

    private static boolean shouldVault(Context context) {
        return BraintreeSharedPreferences.getSharedPreferences(context).getBoolean("com.braintreepayments.api.Venmo.VAULT_VENMO_KEY", false);
    }

    static void onActivityResult(BraintreeFragment fragment, int resultCode, Intent data) {
        if (resultCode == -1) {
            fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.success");
            String nonce = data.getStringExtra("com.braintreepayments.api.EXTRA_PAYMENT_METHOD_NONCE");
            if (!shouldVault(fragment.getApplicationContext()) || !(fragment.getAuthorization() instanceof ClientToken)) {
                String venmoUsername = data.getStringExtra("com.braintreepayments.api.EXTRA_USER_NAME");
                fragment.postCallback((PaymentMethodNonce) new VenmoAccountNonce(nonce, venmoUsername, venmoUsername));
                return;
            }
            vault(fragment, nonce);
        } else if (resultCode == 0) {
            fragment.sendAnalyticsEvent("pay-with-venmo.app-switch.canceled");
        }
    }

    private static void vault(final BraintreeFragment fragment, String nonce) {
        TokenizationClient.tokenize(fragment, new VenmoAccountBuilder().nonce(nonce), new PaymentMethodNonceCallback() {
            public void success(PaymentMethodNonce paymentMethodNonce) {
                fragment.postCallback(paymentMethodNonce);
                fragment.sendAnalyticsEvent("pay-with-venmo.vault.success");
            }

            public void failure(Exception exception) {
                fragment.postCallback(exception);
                fragment.sendAnalyticsEvent("pay-with-venmo.vault.failed");
            }
        });
    }
}
