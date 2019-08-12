package com.airbnb.android.booking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.GiftCardRedemptionAddCreditCardFragment;
import com.airbnb.android.booking.fragments.PaymentManagerFragment;
import com.airbnb.android.booking.fragments.PaymentManagerFragment.PaymentManagerListener;
import com.airbnb.android.booking.fragments.QuickPayGiftCardLandingFragment;
import com.airbnb.android.booking.fragments.SelectCountryFragment;
import com.airbnb.android.booking.fragments.SelectCountryFragment.CountrySelectorListener;
import com.airbnb.android.booking.fragments.SelectPaymentMethodFragment;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.enums.PaymentMethod;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.payments.models.AddPaymentMethodArguments;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.AndroidPayBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.CreditCardBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.PayPalBody;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.braintreepayments.api.exceptions.GoogleApiClientException;
import icepick.State;
import p032rx.Observer;

public class LegacyAddPaymentMethodActivity extends SheetFlowActivity {
    private static final String EXTRA_ANALYTICS_DATA = "analytics_data";
    private static final String EXTRA_BRAINTREE_AUTHORIZATION_STRING = "braintree_authorization_string";
    private static final String EXTRA_CLIENT_TYPE = "extra_client_type";
    private static final String EXTRA_FLOW = "extra_flow";
    private static final String EXTRA_HIDE_ALIPAY_DIRECT = "hide_alipay_direct";
    private static final int REQUEST_CODE_ALIPAY = 12001;
    public static final String RESULT_EXTRA_CREDIT_CARD = "result_extra_credit_card";
    public static final String RESULT_EXTRA_PAYMENT_INSTRUMENT = "result_extra_payment_instrument";
    @State
    ParcelStrap analyticsData;
    @State
    String braintreeAuthorization;
    @State
    String countryCode;
    private final CountrySelectorListener countrySelectorListener = new CountrySelectorListener() {
        public void onSelectCountry(String country) {
            LegacyAddPaymentMethodActivity.this.countryCode = country;
            LegacyAddPaymentMethodActivity.this.showFragment(SelectPaymentMethodFragment.instanceForCountry(LegacyAddPaymentMethodActivity.this.countryCode, LegacyAddPaymentMethodActivity.this.hideAlipayDirect));
        }
    };
    final RequestListener<PaymentInstrumentResponse> createPaymentInstrumentListener = new C0699RL().onResponse(LegacyAddPaymentMethodActivity$$Lambda$1.lambdaFactory$(this)).onError(LegacyAddPaymentMethodActivity$$Lambda$2.lambdaFactory$(this)).build();
    @State
    BraintreeCreditCard creditCard;
    @State
    Flow flow;
    @State
    boolean hideAlipayDirect;
    @State
    boolean isGiftCard;
    @State
    boolean isQuickPay;
    @State
    OldPaymentInstrument paymentInstrument;
    private PaymentManagerFragment paymentManagerFragment;
    private final PaymentManagerListener paymentManagerListener = new PaymentManagerListener() {
        public void onAndroidPayConfigured() {
        }

        public void onPaymentManagerError(int errorCode, Exception error) {
            View contentView = LegacyAddPaymentMethodActivity.this.findViewById(C0704R.C0706id.content_container);
            if (BuildHelper.isDebugFeaturesEnabled()) {
                ErrorUtils.showErrorUsingSnackbar(contentView, error.getMessage(), -2);
            } else if (!AppLaunchUtils.isUserInChina() || !(error instanceof GoogleApiClientException)) {
                ErrorUtils.showErrorUsingSnackbar(contentView, LegacyAddPaymentMethodActivity.this.getString(C0704R.string.error_braintree), -2);
                AirbnbEventLogger.event().name("p4_mobile_error").mo8236kv("errorCode", errorCode).mo8238kv("errorMessage", error.getMessage());
            } else {
                AirbnbEventLogger.event().name("p4_mobile_error").mo8236kv("errorCode", errorCode).mo8238kv("errorMessage", error.getMessage());
            }
        }

        public void onNonceCreated(OldPaymentInstrument instrument) {
            LegacyAddPaymentMethodActivity.this.paymentInstrument = instrument;
            switch (C55503.f7420x57c2d0b2[LegacyAddPaymentMethodActivity.this.flow.ordinal()]) {
                case 1:
                    LegacyAddPaymentMethodActivity.this.createAsExistingPaymentInstrument(instrument);
                    return;
                case 2:
                    LegacyAddPaymentMethodActivity.this.returnResult(LegacyAddPaymentMethodActivity.this.paymentInstrument);
                    return;
                case 3:
                    ((SelectPaymentMethodFragment) LegacyAddPaymentMethodActivity.this.getSupportFragmentManager().findFragmentById(C0704R.C0706id.content_container)).displayLoader();
                    LegacyAddPaymentMethodActivity.this.createAsExistingPaymentInstrument(instrument);
                    return;
                case 4:
                    ((GiftCardRedemptionAddCreditCardFragment) LegacyAddPaymentMethodActivity.this.getSupportFragmentManager().findFragmentById(C0704R.C0706id.content_container)).displayLoader();
                    LegacyAddPaymentMethodActivity.this.createAsExistingPaymentInstrument(instrument);
                    return;
                default:
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown flow type."));
                    return;
            }
        }

        public void onNonceError() {
        }
    };

    public enum Flow {
        BrainTreeAuthorization,
        QuickPay,
        GiftCardRedemption,
        ProfileCompletion
    }

    public static Intent intentForAuthorizationString(Context context, String braintreeAuthorizationString, ParcelStrap analyticsData2, boolean hideAlipayDirect2) {
        return new Intent(context, LegacyAddPaymentMethodActivity.class).putExtra(EXTRA_BRAINTREE_AUTHORIZATION_STRING, braintreeAuthorizationString).putExtra("extra_flow", Flow.BrainTreeAuthorization).putExtra(EXTRA_ANALYTICS_DATA, analyticsData2).putExtra(EXTRA_HIDE_ALIPAY_DIRECT, hideAlipayDirect2);
    }

    public static Intent intentForProfileCompletion(Context context) {
        return new Intent(context, LegacyAddPaymentMethodActivity.class).putExtra("extra_flow", Flow.ProfileCompletion).putExtra(EXTRA_ANALYTICS_DATA, ParcelStrap.make());
    }

    public static Intent intentForQuickPayWithClientType(Context context, QuickPayClientType clientType) {
        return new Intent(context, LegacyAddPaymentMethodActivity.class).putExtra("extra_client_type", clientType).putExtra("extra_flow", Flow.QuickPay);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            this.hideAlipayDirect = intent.getBooleanExtra(EXTRA_HIDE_ALIPAY_DIRECT, false);
            if (intent.hasExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT)) {
                this.flow = getFlowFromRNArguments((AddPaymentMethodArguments) intent.getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT));
            } else {
                this.flow = (Flow) intent.getSerializableExtra("extra_flow");
            }
            Check.notNull(this.flow);
            showFragment(intent);
        }
        initializePaymentManagerFragment();
        this.progressBar.setVisibility(8);
    }

    private Flow getFlowFromRNArguments(AddPaymentMethodArguments arguments) {
        switch (arguments.getClientType()) {
            case GiftCardRedemption:
                return Flow.GiftCardRedemption;
            default:
                throw new IllegalStateException("Unknown add payment method client.");
        }
    }

    private void showFragment(Intent intent) {
        switch (this.flow) {
            case ProfileCompletion:
            case BrainTreeAuthorization:
                this.braintreeAuthorization = intent.getExtras().getString(this.braintreeAuthorization);
                showFragment(SelectCountryFragment.forBooking(C0704R.string.p4_select_country_title, getCountryCode()));
                return;
            case QuickPay:
                QuickPayClientType clientType = (QuickPayClientType) intent.getSerializableExtra("extra_client_type");
                Check.notNull(clientType);
                this.isQuickPay = true;
                this.isGiftCard = clientType.equals(QuickPayClientType.GiftCard);
                if (this.isGiftCard) {
                    showFragment(QuickPayGiftCardLandingFragment.newInstance());
                    return;
                } else {
                    showFragment(SelectCountryFragment.forQuickPay(C0704R.string.p4_select_country_title, getCountryCode()));
                    return;
                }
            case GiftCardRedemption:
                showFragment(GiftCardRedemptionAddCreditCardFragment.newInstance());
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unknown flow type"));
                return;
        }
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.JELLYFISH;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 11001) {
            this.creditCard = (BraintreeCreditCard) data.getSerializableExtra("result_extra_credit_card");
            this.paymentManagerFragment.tokenizeCreditCard(this.creditCard);
        } else if (requestCode == REQUEST_CODE_ALIPAY) {
            returnResult((OldPaymentInstrument) data.getSerializableExtra("result_code_alipay_payment_instrument"));
        }
    }

    public CountrySelectorListener getCountrySelectorListener() {
        return this.countrySelectorListener;
    }

    public void doneWithSelectPaymentMethod(PaymentMethod paymentMethod) {
        Intent intentForAdding;
        switch (paymentMethod) {
            case CreditCard:
                if (this.isQuickPay) {
                    intentForAdding = CreditCardActivity.intentForQuickPay(this, this.countryCode);
                } else {
                    intentForAdding = CreditCardActivity.intentForAdding(this, this.countryCode, (ParcelStrap) getIntent().getParcelableExtra(EXTRA_ANALYTICS_DATA));
                }
                startActivityForResult(intentForAdding, CreditCardActivity.REQUEST_CODE_ADD_CARD);
                return;
            case PayPal:
                this.paymentManagerFragment.authorizePaypal();
                return;
            case Alipay:
                if (!this.isQuickPay) {
                    KonaBookingAnalytics.trackClick("payment_options", "payment_alipay", getAnalyticsData());
                }
                if (ChinaUtils.isAlipayInstalled(this)) {
                    launchAlipayV2Flow();
                    return;
                } else {
                    launchAlipayFlow();
                    return;
                }
            default:
                throw new IllegalArgumentException("Unsupported Payment Method");
        }
    }

    private void launchAlipayFlow() {
        Intent intentForCountryCode;
        if (this.isQuickPay) {
            intentForCountryCode = AlipayActivity.intentForQuickPay(this, this.countryCode);
        } else {
            intentForCountryCode = AlipayActivity.intentForCountryCode(this, this.countryCode, getAnalyticsData());
        }
        startActivityForResult(intentForCountryCode, REQUEST_CODE_ALIPAY);
    }

    private void launchAlipayV2Flow() {
        Intent intentForAuthorization;
        if (this.isQuickPay) {
            intentForAuthorization = AlipayV2Activity.intentForQuickPay(this);
        } else {
            intentForAuthorization = AlipayV2Activity.intentForAuthorization(this, getAnalyticsData());
        }
        startActivityForResult(intentForAuthorization, REQUEST_CODE_ALIPAY);
    }

    public void doneWithGiftCardLanding() {
        this.countryCode = "US";
        showFragment(SelectPaymentMethodFragment.instanceForCountry(this.countryCode, this.hideAlipayDirect));
    }

    /* access modifiers changed from: private */
    public void returnResult(OldPaymentInstrument instrument) {
        Intent resultData = new Intent();
        resultData.putExtra("result_extra_payment_instrument", instrument);
        if (this.isGiftCard) {
            resultData.putExtra("result_extra_credit_card", this.creditCard);
        }
        setResult(instrument == null ? 0 : -1, resultData);
        finish();
    }

    private void initializePaymentManagerFragment() {
        this.paymentManagerFragment = PaymentManagerFragment.newInstance(this, this.braintreeAuthorization);
        this.paymentManagerFragment.setPaymentManagerListener(this.paymentManagerListener);
    }

    /* access modifiers changed from: private */
    public void createAsExistingPaymentInstrument(OldPaymentInstrument paymentInstrument2) {
        if (paymentInstrument2 instanceof BraintreeCreditCard) {
            BraintreeCreditCard creditCard2 = (BraintreeCreditCard) paymentInstrument2;
            CreatePaymentInstrumentRequest.forCreditCard(CreditCardBody.make().paymentMethodNonce(creditCard2.getNonce()).postalCode(creditCard2.getPostalCode()).country(creditCard2.getCountry()).build()).withListener((Observer) this.createPaymentInstrumentListener).execute(this.requestManager);
        } else if (paymentInstrument2 instanceof PayPalInstrument) {
            PayPalInstrument payPalInstrument = (PayPalInstrument) paymentInstrument2;
            CreatePaymentInstrumentRequest.forPayPal(PayPalBody.make().paymentMethodNonce(payPalInstrument.getNonce()).deviceData(payPalInstrument.getDeviceData()).build()).withListener((Observer) this.createPaymentInstrumentListener).execute(this.requestManager);
        } else if (paymentInstrument2 instanceof AndroidPayInstrument) {
            AndroidPayInstrument androidPayInstrument = (AndroidPayInstrument) paymentInstrument2;
            CreatePaymentInstrumentRequest.forAndroidPay(AndroidPayBody.make().paymentMethodNonce(androidPayInstrument.getNonce()).postalCode(androidPayInstrument.getPostalCode()).country(androidPayInstrument.getCountryCode()).build()).withListener((Observer) this.createPaymentInstrumentListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$0(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity, PaymentInstrumentResponse response) {
        legacyAddPaymentMethodActivity.restoreButtonState();
        legacyAddPaymentMethodActivity.paymentInstrument.setId(response.paymentInstrument.getId());
        legacyAddPaymentMethodActivity.returnResult(legacyAddPaymentMethodActivity.paymentInstrument);
    }

    static /* synthetic */ void lambda$new$1(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity, AirRequestNetworkException error) {
        legacyAddPaymentMethodActivity.restoreButtonState();
        NetworkUtil.tryShowErrorWithSnackbar(legacyAddPaymentMethodActivity.findViewById(C0704R.C0706id.content_container), error);
    }

    private void restoreButtonState() {
        if (this.flow.equals(Flow.QuickPay)) {
            ((SelectPaymentMethodFragment) getSupportFragmentManager().findFragmentById(C0704R.C0706id.content_container)).hideLoader();
        } else if (this.flow.equals(Flow.GiftCardRedemption)) {
            ((GiftCardRedemptionAddCreditCardFragment) getSupportFragmentManager().findFragmentById(C0704R.C0706id.content_container)).hideLoader();
        }
    }

    public ParcelStrap getAnalyticsData() {
        if (this.analyticsData == null) {
            this.analyticsData = (ParcelStrap) getIntent().getParcelableExtra(EXTRA_ANALYTICS_DATA);
        }
        return this.analyticsData;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public boolean isLaunchedFromGiftCard() {
        return this.isGiftCard;
    }

    public boolean isQuickPay() {
        return this.isQuickPay;
    }

    public PaymentManagerFragment getPaymentManagerFragment() {
        return this.paymentManagerFragment;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
