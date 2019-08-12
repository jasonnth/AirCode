package com.braintreepayments.api;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.Wallet.WalletOptions;

public class AndroidPayActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {
    private GoogleApiClient mGoogleApiClient;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupGoogleApiClient();
        if (savedInstanceState == null || !savedInstanceState.getBoolean("com.braintreepayments.api.EXTRA_RECREATING")) {
            int requestType = getIntent().getIntExtra("com.braintreepayments.api.EXTRA_REQUEST_TYPE", -1);
            switch (requestType) {
                case 1:
                    loadMaskedWallet();
                    return;
                case 2:
                    changeMaskedWallet();
                    return;
                default:
                    setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", "EXTRA_REQUEST_TYPE contained an unexpected type: " + requestType));
                    finish();
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("com.braintreepayments.api.EXTRA_RECREATING", true);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mGoogleApiClient.disconnect();
    }

    private void loadMaskedWallet() {
        Wallet.Payments.loadMaskedWallet(this.mGoogleApiClient, MaskedWalletRequest.newBuilder().setMerchantName(getIntent().getStringExtra("com.braintreepayments.api.EXTRA_MERCHANT_NAME")).setCurrencyCode(getCart().getCurrencyCode()).setCart(getCart()).setEstimatedTotalPrice(getCart().getTotalPrice()).setShippingAddressRequired(getIntent().getBooleanExtra("com.braintreepayments.api.EXTRA_SHIPPING_ADDRESS_REQUIRED", false)).setPhoneNumberRequired(getIntent().getBooleanExtra("com.braintreepayments.api.EXTRA_PHONE_NUMBER_REQUIRED", false)).setPaymentMethodTokenizationParameters((PaymentMethodTokenizationParameters) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_TOKENIZATION_PARAMETERS")).addAllowedCardNetworks(getIntent().getIntegerArrayListExtra("com.braintreepayments.api.EXTRA_ALLOWED_CARD_NETWORKS")).addAllowedCountrySpecificationsForShipping(getIntent().getParcelableArrayListExtra("com.braintreepayments.api.EXTRA_ALLOWED_COUNTRIES")).build(), 1);
    }

    private void changeMaskedWallet() {
        Wallet.Payments.changeMaskedWallet(this.mGoogleApiClient, getIntent().getStringExtra("com.braintreepayments.api.EXTRA_GOOGLE_TRANSACTION_ID"), null, 2);
    }

    private void loadFullWallet(String googleTransactionId) {
        Wallet.Payments.loadFullWallet(this.mGoogleApiClient, FullWalletRequest.newBuilder().setCart(getCart()).setGoogleTransactionId(googleTransactionId).build(), 3);
    }

    private void setupGoogleApiClient() {
        this.mGoogleApiClient = new Builder(this).addApi(Wallet.API, new WalletOptions.Builder().setEnvironment(getIntent().getIntExtra("com.braintreepayments.api.EXTRA_ENVIRONMENT", 3)).setTheme(1).build()).build();
        this.mGoogleApiClient.registerConnectionCallbacks(this);
        this.mGoogleApiClient.registerConnectionFailedListener(this);
        this.mGoogleApiClient.connect();
    }

    public void onConnected(Bundle bundle) {
    }

    public void onConnectionSuspended(int i) {
        setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", "Connection suspended: " + i));
        finish();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        setResult(2, new Intent().putExtra("com.braintreepayments.api.EXTRA_ERROR", "Connection failed. " + connectionResult.getErrorMessage() + ". Code: " + connectionResult.getErrorCode()));
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && (requestCode == 1 || requestCode == 2)) {
            loadFullWallet(((MaskedWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_MASKED_WALLET")).getGoogleTransactionId());
        } else if (resultCode == -1 && requestCode == 3) {
            data.putExtra("com.braintreepayments.api.EXTRA_CART", getCart());
            setResult(resultCode, data);
            finish();
        } else {
            setResult(resultCode, data);
            finish();
        }
    }

    private Cart getCart() {
        return (Cart) getIntent().getParcelableExtra("com.braintreepayments.api.EXTRA_CART");
    }

    public void finish() {
        super.finish();
        overridePendingTransition(17432576, 17432577);
    }
}
