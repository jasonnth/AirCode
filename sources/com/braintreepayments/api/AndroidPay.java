package com.braintreepayments.api;

import android.content.Intent;
import com.braintreepayments.api.exceptions.AndroidPayException;
import com.braintreepayments.api.exceptions.ErrorWithResponse;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.AndroidPayConfiguration;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.MetadataBuilder;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.braintreepayments.api.models.TokenizationKey;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters.Builder;
import com.google.android.gms.wallet.Wallet;
import java.util.ArrayList;
import org.json.JSONException;

public class AndroidPay {
    public static void isReadyToPay(final BraintreeFragment fragment, final BraintreeResponseListener<Boolean> listener) {
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                if (!configuration.getAndroidPay().isEnabled(fragment.getApplicationContext())) {
                    listener.onResponse(Boolean.valueOf(false));
                } else {
                    fragment.getGoogleApiClient(new BraintreeResponseListener<GoogleApiClient>() {
                        public void onResponse(GoogleApiClient googleApiClient) {
                            Wallet.Payments.isReadyToPay(googleApiClient).setResultCallback(new ResultCallback<BooleanResult>() {
                                public void onResult(BooleanResult booleanResult) {
                                    listener.onResponse(Boolean.valueOf(booleanResult.getStatus().isSuccess() && booleanResult.getValue()));
                                }
                            });
                        }
                    });
                }
            }
        });
    }

    public static void getTokenizationParameters(final BraintreeFragment fragment, final TokenizationParametersListener listener) {
        fragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                listener.onResult(AndroidPay.getTokenizationParameters(fragment), AndroidPay.getAllowedCardNetworks(fragment));
            }
        });
    }

    static PaymentMethodTokenizationParameters getTokenizationParameters(BraintreeFragment fragment) {
        Builder parameters = PaymentMethodTokenizationParameters.newBuilder().setPaymentMethodTokenizationType(1).addParameter("gateway", "braintree").addParameter("braintree:merchantId", fragment.getConfiguration().getMerchantId()).addParameter("braintree:authorizationFingerprint", fragment.getConfiguration().getAndroidPay().getGoogleAuthorizationFingerprint()).addParameter("braintree:apiVersion", "v1").addParameter("braintree:sdkVersion", "2.5.2").addParameter("braintree:metadata", new MetadataBuilder().integration(fragment.getIntegrationType()).sessionId(fragment.getSessionId()).version().toString());
        if (fragment.getAuthorization() instanceof TokenizationKey) {
            parameters.addParameter("braintree:clientKey", fragment.getAuthorization().toString());
        }
        return parameters.build();
    }

    static ArrayList<Integer> getAllowedCardNetworks(BraintreeFragment fragment) {
        String[] supportedNetworks;
        ArrayList<Integer> allowedNetworks = new ArrayList<>();
        for (String network : fragment.getConfiguration().getAndroidPay().getSupportedNetworks()) {
            char c = 65535;
            switch (network.hashCode()) {
                case -2038717326:
                    if (network.equals("mastercard")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2997727:
                    if (network.equals("amex")) {
                        c = 2;
                        break;
                    }
                    break;
                case 3619905:
                    if (network.equals("visa")) {
                        c = 0;
                        break;
                    }
                    break;
                case 273184745:
                    if (network.equals("discover")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    allowedNetworks.add(Integer.valueOf(5));
                    break;
                case 1:
                    allowedNetworks.add(Integer.valueOf(4));
                    break;
                case 2:
                    allowedNetworks.add(Integer.valueOf(1));
                    break;
                case 3:
                    allowedNetworks.add(Integer.valueOf(2));
                    break;
            }
        }
        return allowedNetworks;
    }

    @Deprecated
    public static void tokenize(BraintreeFragment fragment, FullWallet wallet) {
        tokenize(fragment, wallet, null);
    }

    public static void tokenize(BraintreeFragment fragment, FullWallet wallet, Cart cart) {
        try {
            fragment.postCallback((PaymentMethodNonce) AndroidPayCardNonce.fromFullWallet(wallet, cart));
            fragment.sendAnalyticsEvent("android-pay.nonce-received");
        } catch (JSONException e) {
            fragment.sendAnalyticsEvent("android-pay.failed");
            try {
                fragment.postCallback((Exception) ErrorWithResponse.fromJson(wallet.getPaymentMethodToken().getToken()));
            } catch (JSONException e1) {
                fragment.postCallback((Exception) e1);
            }
        }
    }

    static void onActivityResult(BraintreeFragment fragment, int resultCode, Intent data) {
        if (resultCode == -1) {
            if (data.hasExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET")) {
                fragment.sendAnalyticsEvent("android-pay.authorized");
                tokenize(fragment, (FullWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET"), (Cart) data.getParcelableExtra("com.braintreepayments.api.EXTRA_CART"));
            }
        } else if (resultCode == 0) {
            fragment.sendAnalyticsEvent("android-pay.canceled");
        } else {
            if (data != null) {
                if (data.hasExtra("com.braintreepayments.api.EXTRA_ERROR")) {
                    fragment.postCallback((Exception) new AndroidPayException(data.getStringExtra("com.braintreepayments.api.EXTRA_ERROR")));
                } else {
                    fragment.postCallback((Exception) new AndroidPayException("Android Pay error code: " + data.getIntExtra("com.google.android.gms.wallet.EXTRA_ERROR_CODE", -1) + " see https://developers.google.com/android/reference/com/google/android/gms/wallet/WalletConstants " + "for more details"));
                }
            }
            fragment.sendAnalyticsEvent("android-pay.failed");
        }
    }

    protected static int getEnvironment(AndroidPayConfiguration configuration) {
        if ("production".equals(configuration.getEnvironment())) {
            return 1;
        }
        return 3;
    }
}
