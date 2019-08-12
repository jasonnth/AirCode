package com.airbnb.android.lib.payments.braintree;

import com.airbnb.android.lib.C0880R;
import com.braintreepayments.api.interfaces.TokenizationParametersListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.Wallet;
import java.util.Collection;

final /* synthetic */ class AndroidPayClient$$Lambda$1 implements TokenizationParametersListener {
    private final AndroidPayClient arg$1;
    private final int arg$2;
    private final GoogleApiClient arg$3;
    private final int arg$4;

    private AndroidPayClient$$Lambda$1(AndroidPayClient androidPayClient, int i, GoogleApiClient googleApiClient, int i2) {
        this.arg$1 = androidPayClient;
        this.arg$2 = i;
        this.arg$3 = googleApiClient;
        this.arg$4 = i2;
    }

    public static TokenizationParametersListener lambdaFactory$(AndroidPayClient androidPayClient, int i, GoogleApiClient googleApiClient, int i2) {
        return new AndroidPayClient$$Lambda$1(androidPayClient, i, googleApiClient, i2);
    }

    public void onResult(PaymentMethodTokenizationParameters paymentMethodTokenizationParameters, Collection collection) {
        Wallet.Payments.loadMaskedWallet(this.arg$3, MaskedWalletRequest.newBuilder().setPaymentMethodTokenizationParameters(paymentMethodTokenizationParameters).addAllowedCardNetworks(collection).setMerchantName(this.arg$1.context.getString(C0880R.string.merchant_name)).setCurrencyCode(this.arg$1.currencyFormatter.getLocalCurrencyString()).setEstimatedTotalPrice(this.arg$1.currencyFormatter.getAndroidPayFormattedPrice(this.arg$2)).build(), this.arg$4);
    }
}
