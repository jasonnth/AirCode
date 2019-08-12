package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p002v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.fragments.androidpay.AndroidPayApiLegacy;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.requests.CreateBraintreeClientTokenRequest;
import com.airbnb.android.core.responses.BraintreeClientTokenResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.braintreepayments.api.AndroidPay;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.Card;
import com.braintreepayments.api.DataCollector;
import com.braintreepayments.api.PayPal;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.AndroidPayCardNonce;
import com.braintreepayments.api.models.CardNonce;
import com.braintreepayments.api.models.PayPalAccountNonce;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.PaymentMethodTokenizationParameters;
import com.google.android.gms.wallet.Wallet;
import icepick.State;
import java.util.Collection;
import java.util.Collections;
import p032rx.Observer;

public class PaymentManagerFragment extends AirFragment implements AndroidPayApiLegacy {
    private static final String ARG_BRAINTREE_AUTHORIZATION = "braintree_authorization";
    private static final String BRAINTREE_TOKENIZATION_KEY = "production_x2392hp5_pfpfmbt48yh4ht8c";
    private static final int ERROR_CODE_BRAINTREE_ERROR = 302;
    private static final int ERROR_CODE_INSTANTIATE_BRAINTREE = 301;
    private static final int ERROR_CODE_REQUEST_BRAINTREE_TOKEN = 300;
    public static final int REQUEST_CODE_CHANGE_MASKED_WALLET = 601;
    public static final int REQUEST_CODE_FULL_WALLET = 602;
    public static final int REQUEST_CODE_MASKED_WALLET = 600;
    private static final String TAG = PaymentManagerFragment.class.getSimpleName();
    private String braintreeAuthorization;
    private final BraintreeErrorListener braintreeErrorListener = PaymentManagerFragment$$Lambda$3.lambdaFactory$(this);
    private BraintreeFragment braintreeFragment;
    final RequestListener<BraintreeClientTokenResponse> braintreeTokenRequestListener = new C0699RL().onResponse(PaymentManagerFragment$$Lambda$1.lambdaFactory$(this)).onError(PaymentManagerFragment$$Lambda$2.lambdaFactory$(this)).build();
    private final ConfigurationListener configurationListener = PaymentManagerFragment$$Lambda$4.lambdaFactory$(this);
    private GoogleApiClient googleApiClient;
    @State
    FullWallet googleFullWallet;
    @State
    MaskedWallet googleMaskedWallet;
    private boolean isAndroidPayEnabled;
    /* access modifiers changed from: private */
    public boolean isPayPalEnabled;
    @State
    OldPaymentInstrument paymentInstrument;
    private PaymentManagerListener paymentManagerListener;
    private final PaymentMethodNonceCreatedListener paymentMethodNonceCreatedListener = PaymentManagerFragment$$Lambda$5.lambdaFactory$(this);

    public interface PaymentManagerListener {
        void onAndroidPayConfigured();

        void onNonceCreated(OldPaymentInstrument oldPaymentInstrument);

        void onNonceError();

        void onPaymentManagerError(int i, Exception exc);
    }

    public static PaymentManagerFragment newInstance(AppCompatActivity activity, String braintreeAuthorization2) {
        FragmentManager fm = activity.getSupportFragmentManager();
        PaymentManagerFragment paymentManagerFragment = (PaymentManagerFragment) fm.findFragmentByTag(TAG);
        if (paymentManagerFragment != null) {
            return paymentManagerFragment;
        }
        new Bundle().putString(ARG_BRAINTREE_AUTHORIZATION, braintreeAuthorization2);
        PaymentManagerFragment paymentManagerFragment2 = (PaymentManagerFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PaymentManagerFragment()).putString(ARG_BRAINTREE_AUTHORIZATION, braintreeAuthorization2)).build();
        fm.beginTransaction().add((Fragment) paymentManagerFragment2, TAG).commit();
        return paymentManagerFragment2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        this.braintreeAuthorization = getArguments().getString(ARG_BRAINTREE_AUTHORIZATION);
        initializeBraintreeFragmentWithToken(Boolean.valueOf(true));
    }

    public void onDestroy() {
        removeBraintreeListeners();
        super.onDestroy();
    }

    public String getBraintreeAuthorization() {
        return this.braintreeAuthorization;
    }

    public boolean hasBraintreeAuthorization() {
        return !TextUtils.isEmpty(this.braintreeAuthorization);
    }

    public void setPaymentManagerListener(PaymentManagerListener listener) {
        this.paymentManagerListener = listener;
    }

    public void tokenizeCreditCard(BraintreeCreditCard creditCard) {
        this.paymentInstrument = creditCard;
        Card.tokenize(this.braintreeFragment, creditCard.buildCard());
    }

    public void tokenizeCvv(String cvv) {
        BraintreeCreditCard creditCard = new BraintreeCreditCard(cvv);
        this.paymentInstrument = creditCard;
        Card.tokenize(this.braintreeFragment, creditCard.buildCard());
    }

    public boolean isPayPalEnabled() {
        return this.isPayPalEnabled;
    }

    public void authorizePaypal() {
        PayPal.authorizeAccount(this.braintreeFragment, Collections.singletonList(PayPal.SCOPE_ADDRESS));
    }

    public boolean isAndroidPayEnabled() {
        return this.isAndroidPayEnabled;
    }

    public void loadMaskedAndroidPayWallet(AndroidPayInstrument instrument, int totalPrice) {
        AndroidPay.getTokenizationParameters(this.braintreeFragment, PaymentManagerFragment$$Lambda$6.lambdaFactory$(this, instrument, totalPrice));
    }

    static /* synthetic */ void lambda$loadMaskedAndroidPayWallet$0(PaymentManagerFragment paymentManagerFragment, AndroidPayInstrument instrument, int totalPrice, PaymentMethodTokenizationParameters parameters, Collection allowedCardNetworks) {
        paymentManagerFragment.paymentInstrument = instrument;
        Wallet.Payments.loadMaskedWallet(paymentManagerFragment.googleApiClient, MaskedWalletRequest.newBuilder().setPaymentMethodTokenizationParameters(parameters).addAllowedCardNetworks(allowedCardNetworks).setMerchantName(paymentManagerFragment.getString(C0704R.string.merchant_name)).setCurrencyCode(paymentManagerFragment.mCurrencyHelper.getLocalCurrencyString()).setEstimatedTotalPrice(paymentManagerFragment.mCurrencyHelper.getAndroidPayFormattedPrice(totalPrice)).build(), 600);
    }

    public void loadFullAndroidPayWallet(int totalPrice, String currencyCode) {
        Wallet.Payments.loadFullWallet(this.googleApiClient, FullWalletRequest.newBuilder().setGoogleTransactionId(this.googleMaskedWallet.getGoogleTransactionId()).setCart(Cart.newBuilder().setTotalPrice(this.mCurrencyHelper.getAndroidPayFormattedPrice(totalPrice)).setCurrencyCode(currencyCode).build()).build(), 602);
    }

    public void onMaskedWalletLoaded(MaskedWallet maskedWallet) {
        this.googleMaskedWallet = maskedWallet;
    }

    public void onFullWalletLoaded(FullWallet fullWallet) {
        this.googleFullWallet = fullWallet;
        AndroidPay.tokenize(this.braintreeFragment, this.googleFullWallet);
    }

    public void changeMaskedWallet() {
        Check.notNull(this.googleMaskedWallet);
        Wallet.Payments.changeMaskedWallet(this.googleApiClient, this.googleMaskedWallet.getGoogleTransactionId(), this.googleMaskedWallet.getMerchantTransactionId(), 601);
    }

    public boolean isAndroidPayInitialized() {
        return this.googleMaskedWallet != null;
    }

    private void requestBraintreeToken() {
        new CreateBraintreeClientTokenRequest().withListener((Observer) this.braintreeTokenRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(PaymentManagerFragment paymentManagerFragment, BraintreeClientTokenResponse response) {
        paymentManagerFragment.braintreeAuthorization = response.getToken();
        paymentManagerFragment.initializeBraintreeFragmentWithToken(Boolean.valueOf(false));
    }

    private void initializeBraintreeFragmentWithToken(Boolean useTokenizationKey) {
        if (useTokenizationKey.booleanValue()) {
            try {
                this.braintreeFragment = BraintreeFragment.newInstance(getActivity(), "production_x2392hp5_pfpfmbt48yh4ht8c");
            } catch (InvalidArgumentException e) {
                BugsnagWrapper.notify(new Throwable("[Braintree_Token]Unable to instantiate Braintree with tokenization key"));
                initializeBraintreeFragmentWithToken(Boolean.valueOf(false));
            }
        } else if (hasBraintreeAuthorization()) {
            try {
                this.braintreeFragment = BraintreeFragment.newInstance(getActivity(), this.braintreeAuthorization);
            } catch (InvalidArgumentException e2) {
                this.paymentManagerListener.onPaymentManagerError(301, new IllegalStateException("Unable to instantiate Braintree"));
            }
        } else if (!isBraintreeTokenRequestInFlight()) {
            requestBraintreeToken();
        }
        if (this.braintreeFragment != null) {
            addBraintreeListeners();
            createGoogleApiClient();
        }
    }

    private void addBraintreeListeners() {
        this.braintreeFragment.addListener(this.braintreeErrorListener);
        this.braintreeFragment.addListener(this.paymentMethodNonceCreatedListener);
        this.braintreeFragment.addListener(this.configurationListener);
    }

    private void removeBraintreeListeners() {
        if (this.braintreeFragment != null) {
            this.braintreeFragment.removeListener(this.braintreeErrorListener);
            this.braintreeFragment.removeListener(this.paymentMethodNonceCreatedListener);
            this.braintreeFragment.removeListener(this.configurationListener);
        }
    }

    private void createGoogleApiClient() {
        this.braintreeFragment.getGoogleApiClient(PaymentManagerFragment$$Lambda$7.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$createGoogleApiClient$4(PaymentManagerFragment paymentManagerFragment, GoogleApiClient apiClient) {
        paymentManagerFragment.googleApiClient = apiClient;
        AndroidPay.isReadyToPay(paymentManagerFragment.braintreeFragment, PaymentManagerFragment$$Lambda$8.lambdaFactory$(paymentManagerFragment));
    }

    static /* synthetic */ void lambda$null$3(PaymentManagerFragment paymentManagerFragment, Boolean isReadyToPay) {
        paymentManagerFragment.isAndroidPayEnabled = isReadyToPay.booleanValue();
        if (paymentManagerFragment.paymentManagerListener != null) {
            paymentManagerFragment.paymentManagerListener.onAndroidPayConfigured();
        }
    }

    static /* synthetic */ void lambda$new$7(PaymentManagerFragment paymentManagerFragment, PaymentMethodNonce paymentMethodNonce) {
        if (paymentMethodNonce instanceof CardNonce) {
            ((BraintreeCreditCard) paymentManagerFragment.paymentInstrument).setNonce(paymentMethodNonce.getNonce());
            paymentManagerFragment.paymentManagerListener.onNonceCreated(paymentManagerFragment.paymentInstrument);
        } else if (paymentMethodNonce instanceof PayPalAccountNonce) {
            PayPalInstrument instrument = new PayPalInstrument((PayPalAccountNonce) paymentMethodNonce);
            instrument.setDeviceData(DataCollector.collectDeviceData(paymentManagerFragment.braintreeFragment));
            paymentManagerFragment.paymentManagerListener.onNonceCreated(instrument);
        } else if (paymentMethodNonce instanceof AndroidPayCardNonce) {
            AndroidPayInstrument androidPayInstrument = (AndroidPayInstrument) paymentManagerFragment.paymentInstrument;
            androidPayInstrument.setNonce(paymentMethodNonce.getNonce());
            androidPayInstrument.setCountryCode(paymentManagerFragment.googleFullWallet.getBuyerBillingAddress().getCountryCode());
            androidPayInstrument.setPostalCode(paymentManagerFragment.googleFullWallet.getBuyerBillingAddress().getPostalCode());
            paymentManagerFragment.paymentManagerListener.onNonceCreated(paymentManagerFragment.paymentInstrument);
        }
    }

    private boolean isBraintreeTokenRequestInFlight() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.braintreeTokenRequestListener, CreateBraintreeClientTokenRequest.class);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.PaymentManagerFragment;
    }
}
