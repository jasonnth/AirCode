package com.airbnb.android.lib.payments.braintree;

import android.content.Context;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.braintreepayments.api.AndroidPay;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.Wallet;

public class AndroidPayClient implements AndroidPayApi {
    private final BraintreeFragment braintreeFragment;
    private final Context context;
    private final CurrencyFormatter currencyFormatter;

    public AndroidPayClient(Context context2, BraintreeFragment braintreeFragment2, CurrencyFormatter currencyFormatter2) {
        this.context = context2;
        this.braintreeFragment = braintreeFragment2;
        this.currencyFormatter = currencyFormatter2;
    }

    public void createGoogleApiClient(BraintreeResponseListener<GoogleApiClient> listener) {
        this.braintreeFragment.getGoogleApiClient(listener);
    }

    public void isAndroidPayReady(BraintreeResponseListener<Boolean> listener) {
        AndroidPay.isReadyToPay(this.braintreeFragment, listener);
    }

    public void loadMaskedAndroidPayWallet(int requestCode, int totalPrice, GoogleApiClient client) {
        AndroidPay.getTokenizationParameters(this.braintreeFragment, AndroidPayClient$$Lambda$1.lambdaFactory$(this, totalPrice, client, requestCode));
    }

    public void changeMaskedWallet(int requestCode, MaskedWallet maskedWallet, GoogleApiClient client) {
        Wallet.Payments.changeMaskedWallet(client, maskedWallet.getGoogleTransactionId(), maskedWallet.getMerchantTransactionId(), requestCode);
    }

    public void loadFullAndroidPayWallet(int requestCode, int totalPrice, String currencyCode, MaskedWallet maskedWallet, GoogleApiClient googleApiClient) {
        Wallet.Payments.loadFullWallet(googleApiClient, FullWalletRequest.newBuilder().setGoogleTransactionId(maskedWallet.getGoogleTransactionId()).setCart(Cart.newBuilder().setTotalPrice(this.currencyFormatter.getAndroidPayFormattedPrice(totalPrice)).setCurrencyCode(currencyCode).build()).build(), requestCode);
    }

    public void tokenizeAndroidPay(FullWallet fullWallet) {
        AndroidPay.tokenize(this.braintreeFragment, fullWallet, null);
    }
}
