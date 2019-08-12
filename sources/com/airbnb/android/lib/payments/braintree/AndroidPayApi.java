package com.airbnb.android.lib.payments.braintree;

import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.MaskedWallet;

public interface AndroidPayApi {
    void changeMaskedWallet(int i, MaskedWallet maskedWallet, GoogleApiClient googleApiClient);

    void createGoogleApiClient(BraintreeResponseListener<GoogleApiClient> braintreeResponseListener);

    void isAndroidPayReady(BraintreeResponseListener<Boolean> braintreeResponseListener);

    void loadFullAndroidPayWallet(int i, int i2, String str, MaskedWallet maskedWallet, GoogleApiClient googleApiClient);

    void loadMaskedAndroidPayWallet(int i, int i2, GoogleApiClient googleApiClient);

    void tokenizeAndroidPay(FullWallet fullWallet);
}
