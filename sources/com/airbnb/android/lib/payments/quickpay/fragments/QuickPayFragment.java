package com.airbnb.android.lib.payments.quickpay.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger.QuickPayConfirmAndPayParams;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.QuickPayActivityIntents;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.Bill;
import com.airbnb.android.core.payments.models.BillPriceQuote;
import com.airbnb.android.core.payments.models.BillProductType;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.security.ThreatMetrixClient;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.activities.PaymentOptionsActivity;
import com.airbnb.android.lib.payments.addpayment.activities.AddPaymentMethodActivity;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.errors.QuickPayError;
import com.airbnb.android.lib.payments.errors.QuickPayError.QuickPayErrorType;
import com.airbnb.android.lib.payments.factories.BillPriceQuoteRequestFactory;
import com.airbnb.android.lib.payments.factories.PaymentOptionFactory;
import com.airbnb.android.lib.payments.networking.billpricequote.BillPriceQuoteApi;
import com.airbnb.android.lib.payments.networking.billpricequote.BillPriceQuoteDelegate;
import com.airbnb.android.lib.payments.networking.billpricequote.BillPriceQuoteDelegate.BillPriceQuoteDelegateListener;
import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters;
import com.airbnb.android.lib.payments.networking.createbill.CreateBillParameters.Builder;
import com.airbnb.android.lib.payments.networking.createbill.requester.CreateBillApi;
import com.airbnb.android.lib.payments.networking.createbill.requester.CreateBillDelegate;
import com.airbnb.android.lib.payments.networking.createbill.requester.CreateBillDelegate.CreateBillDelegateListener;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsApi;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate;
import com.airbnb.android.lib.payments.paymentoptions.PaymentOptionsDelegate.PaymentOptionsDelegateListener;
import com.airbnb.android.lib.payments.quickpay.adapters.BaseQuickPayAdapter;
import com.airbnb.android.lib.payments.quickpay.adapters.QuickPayAdapterFactory;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.QuickPayClickListener;
import com.airbnb.android.lib.payments.quickpay.paymentredirect.PaymentRedirectCoordinator;
import com.airbnb.android.lib.payments.quickpay.views.QuickPayView;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.jitney.event.logging.PaymentInstrumentRowSection.p182v1.C2503PaymentInstrumentRowSection;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class QuickPayFragment extends AirFragment implements BillPriceQuoteDelegateListener, CreateBillDelegateListener, PaymentOptionsDelegateListener, QuickPayClickListener {
    static final String ARG_CART_ITEM = "arg_cart_item";
    static final String ARG_QUICK_PAY_CLIENT_TYPE = "arg_quick_pay_client_type";
    public static final int REQUEST_CODE_ADD_PAYMENT_METHOD = 1002;
    public static final int REQUEST_CODE_ADD_SECURITY_CODE = 1001;
    public static final int REQUEST_CODE_PAYMENT_OPTION = 1000;
    public static final int REQUEST_CODE_REDIRECT_PAYMENT = 1003;
    QuickPayAdapterFactory adapterFactory;
    AirlockErrorHandler airlockErrorHandler;
    @State
    Bill bill;
    @State
    BillPriceQuote billPriceQuote;
    protected BillPriceQuoteApi billPriceQuoteApi;
    BillPriceQuoteRequestFactory billPriceQuoteRequestFactory;
    @State
    BillProductType billProductType;
    BraintreeFactory braintreeFactory;
    @State
    CartItem cartItem;
    @State
    QuickPayClientType clientType;
    private CreateBillApi createBillApi;
    @State
    boolean isFirstTimePriceQuote;
    @BindView
    FixedActionFooter payButton;
    PaymentOptionFactory paymentOptionFactory;
    @State
    ArrayList<PaymentOption> paymentOptions;
    protected PaymentOptionsApi paymentOptionsApi;
    PaymentRedirectCoordinator paymentRedirectCoordinator;
    @State
    String postalCode;
    protected BaseQuickPayAdapter quickPayAdapter;
    QuickPayJitneyLogger quickPayJitneyLogger;
    /* access modifiers changed from: private */
    public QuickPayView quickPayView;
    @BindView
    RecyclerView recyclerView;
    @State
    PaymentOption selectedPaymentOption;
    @State
    String settlementCurrency;
    @State
    boolean shouldIncludeAirbnbCredit;
    ThreatMetrixClient threatMetrixClient;
    @BindView
    AirToolbar toolbar;
    @State
    boolean userAgreedToCurrencyMismatch;

    public static QuickPayFragment instanceForCartItem(CartItem cartItem2, QuickPayClientType clientType2) {
        return (QuickPayFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new QuickPayFragment()).putParcelable(ARG_CART_ITEM, cartItem2)).putSerializable(ARG_QUICK_PAY_CLIENT_TYPE, clientType2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.cartItem = (CartItem) getArguments().getParcelable(ARG_CART_ITEM);
            this.clientType = (QuickPayClientType) getArguments().getSerializable(ARG_QUICK_PAY_CLIENT_TYPE);
            this.billProductType = BillProductType.getProductTypeFromClientType(this.clientType);
            this.paymentOptions = new ArrayList<>();
            this.isFirstTimePriceQuote = true;
            this.settlementCurrency = this.mCurrencyHelper.getLocalCurrencyString();
        }
        this.quickPayAdapter = this.adapterFactory.createAdapter(this.clientType, this.cartItem, this);
        this.paymentOptionsApi = new PaymentOptionsDelegate(this.requestManager, this);
        this.createBillApi = new CreateBillDelegate(this.requestManager, this, this.threatMetrixClient);
        this.billPriceQuoteApi = new BillPriceQuoteDelegate(this.requestManager, this, this.billPriceQuoteRequestFactory);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_quick_pay, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            disablePayButton();
            requestPaymentOptions();
        } else {
            if (this.billPriceQuote != null) {
                this.quickPayAdapter.setPriceQuote(this.billPriceQuote);
                this.quickPayAdapter.updateLegalAndFxRow(this.billPriceQuote);
                setPayButtonPrice(this.billPriceQuote.getPrice().getTotal().formattedForDisplay());
            }
            if (this.selectedPaymentOption != null) {
                this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
            }
        }
        this.quickPayView = new QuickPayView(this.payButton, this.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false) {
            public void onLayoutCompleted(RecyclerView.State state) {
                super.onLayoutCompleted(state);
                QuickPayFragment.this.quickPayView.togglePayButtonVisibility();
            }
        });
        this.recyclerView.setAdapter(this.quickPayAdapter);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                QuickPayFragment.this.quickPayView.togglePayButtonVisibility();
            }
        });
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000 && resultCode == -1) {
            this.paymentOptions = data.getParcelableArrayListExtra("result_extra_payment_options");
            this.selectedPaymentOption = (PaymentOption) data.getParcelableExtra("result_extra_payment_option");
            this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
            showLoadingState(true);
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
        } else if (requestCode == 11002 && resultCode == -1) {
            this.postalCode = data.getStringExtra(CreditCardActivity.RESULT_EXTRA_POSTAL_CODE);
            sendBill();
        } else if (requestCode == 1002 && resultCode == -1) {
            onPaymentMethodAdded(data);
        } else if (requestCode != 1003) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            onBillRequestCompleted(this.bill);
        } else {
            setPayButtonToNormal();
        }
    }

    public void onPaymentRowClicked() {
        String currency = this.mCurrencyHelper.getLocalCurrencyString();
        long priceDisplayed = this.billPriceQuote.getPrice().getTotal().getAmountMicros();
        BillProductType productType = BillProductType.getProductTypeFromClientType(this.clientType);
        if (PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            this.quickPayJitneyLogger.paymentInstrumentRowClicked(C2503PaymentInstrumentRowSection.Wallet, currency, priceDisplayed, productType);
            showExistingPaymentOptions();
            return;
        }
        this.quickPayJitneyLogger.paymentInstrumentRowClicked(C2503PaymentInstrumentRowSection.Add, currency, priceDisplayed, productType);
        showAddPaymentMethods();
    }

    public void onCreateBillSuccess(Bill bill2) {
        this.bill = bill2;
        if (this.paymentRedirectCoordinator.shouldRedirectPayment(bill2.redirectSettings())) {
            this.paymentRedirectCoordinator.launchRedirectPaymentFlow(getActivity(), bill2.redirectSettings().url(), 1003);
        } else {
            onBillRequestCompleted(bill2);
        }
    }

    public void onCreateBillFailure(NetworkException networkException) {
        AirbnbEventLogger.event().name("quickpay_billing").mo8238kv(BaseAnalytics.OPERATION, "error").mo8238kv("error", networkException.bodyString());
        setPayButtonToNormal();
        enablePayButton();
        confirmAndPayLogging(false, networkException);
        onBillRequestError(networkException);
    }

    public void onPaymentOptionsRequestSuccess(List<PaymentOption> paymentOptions2) {
        this.paymentOptions.clear();
        this.paymentOptions.addAll(paymentOptions2);
        setDefaultPaymentOption(this.paymentOptions);
        this.braintreeFactory.createAndroidPayApi(getActivity(), this.braintreeFactory.createBraintreeFragment(getActivity()), this.mCurrencyHelper).isAndroidPayReady(QuickPayFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$onPaymentOptionsRequestSuccess$0(QuickPayFragment quickPayFragment, Boolean isReadyToPay) {
        if (isReadyToPay.booleanValue()) {
            quickPayFragment.paymentOptions.add(quickPayFragment.paymentOptionFactory.createDummyAndroidPayPaymentOption());
        }
        quickPayFragment.fetchBillPriceQuote(quickPayFragment.shouldApplyCreditByDefault(), quickPayFragment.settlementCurrency);
    }

    public void onPaymentOptionsRequestError(NetworkException networkException) {
        showError(new QuickPayError(networkException, this.airlockErrorHandler));
    }

    /* access modifiers changed from: protected */
    public void fetchBillPriceQuote(boolean shouldIncludeAirbnbCredit2, String displayCurrency) {
        disablePayButton();
        executeBillPriceQuoteRequest(shouldIncludeAirbnbCredit2, displayCurrency);
    }

    /* access modifiers changed from: protected */
    public void executeBillPriceQuoteRequest(boolean shouldIncludeAirbnbCredit2, String displayCurrency) {
        this.billPriceQuoteApi.fetchBillPriceQuote(this.clientType, this.selectedPaymentOption, this.cartItem.quickPayParameters(), shouldIncludeAirbnbCredit2, displayCurrency);
    }

    /* access modifiers changed from: protected */
    public void onPaymentMethodAdded(Intent data) {
        OldPaymentInstrument paymentInstrument;
        String cvvPayload = data.getStringExtra("result_extra_cvv_payload");
        if (PaymentUtils.shouldShowNewAddPaymentMethodFlow()) {
            paymentInstrument = (OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument");
        } else {
            paymentInstrument = (OldPaymentInstrument) data.getSerializableExtra("result_extra_payment_instrument");
        }
        PaymentOption newPaymentOption = this.paymentOptionFactory.forNewInstrument(paymentInstrument);
        this.paymentOptions.add(0, newPaymentOption);
        this.selectedPaymentOption = newPaymentOption;
        this.selectedPaymentOption.setCvvPayload(cvvPayload);
        this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
        showLoadingState(true);
        fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
    }

    /* access modifiers changed from: protected */
    public void setPayButtonClickListener(OnClickListener clickListener) {
        this.payButton.setButtonOnClickListener(clickListener);
    }

    /* access modifiers changed from: protected */
    public void setPayButtonToLoading() {
        this.payButton.setButtonLoading(true);
    }

    /* access modifiers changed from: protected */
    public void setPayButtonToNormal() {
        this.payButton.setButtonLoading(false);
    }

    /* access modifiers changed from: protected */
    public void setPayButtonPrice(String price) {
        if (PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_button_text, price));
            return;
        }
        this.payButton.setButtonText((CharSequence) getString(C0880R.string.quick_pay_add_payment));
    }

    /* access modifiers changed from: protected */
    public void disablePayButton() {
        this.payButton.setButtonEnabled(false);
    }

    /* access modifiers changed from: protected */
    public void enablePayButton() {
        this.payButton.setButtonEnabled(true);
    }

    /* access modifiers changed from: protected */
    public OnClickListener payButtonClickListener() {
        return QuickPayFragment$$Lambda$2.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$payButtonClickListener$1(QuickPayFragment quickPayFragment, View v) {
        if (PaymentUtils.isValidPaymentOption(quickPayFragment.selectedPaymentOption)) {
            quickPayFragment.sendBill();
        } else {
            quickPayFragment.showAddPaymentMethods();
        }
    }

    /* access modifiers changed from: protected */
    public void onBillRequestCompleted(Bill bill2) {
        confirmAndPayLogging(true, null);
        getActivity().setResult(-1, new Intent().putExtra(QuickPayActivityIntents.RESULT_EXTRA_BILL, bill2));
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public void onBillRequestError(NetworkException networkException) {
        showError(new QuickPayError(networkException, this.airlockErrorHandler));
    }

    /* access modifiers changed from: protected */
    public void sendBill() {
        disablePayButton();
        setPayButtonToLoading();
        createBill(createBillParameters(this.billProductType).build());
    }

    /* access modifiers changed from: protected */
    public void createBill(CreateBillParameters parameters) {
        this.createBillApi.createBill(parameters);
    }

    /* access modifiers changed from: protected */
    public Builder createBillParameters(BillProductType productType) {
        return CreateBillParameters.builder().userId(this.mAccountManager.getCurrentUserId()).selectedPaymentOption(this.selectedPaymentOption).currency(this.billPriceQuote.getPrice().getTotal().getCurrency()).quickPayParameters(this.cartItem.quickPayParameters()).billPriceQuote(this.billPriceQuote).billProductType(productType).shouldIncludeAirbnbCredit(Boolean.valueOf(this.billPriceQuote.getPrice().hasGiftCredit())).postalCode(this.postalCode);
    }

    /* access modifiers changed from: protected */
    public void setDefaultPaymentOption(List<PaymentOption> paymentOptions2) {
        this.selectedPaymentOption = PaymentUtils.getDefaultPaymentOption(paymentOptions2);
        this.quickPayAdapter.setPaymentOption(this.selectedPaymentOption);
    }

    /* access modifiers changed from: protected */
    public void onPriceQuoteChanged(BillPriceQuote priceQuote) {
        this.billPriceQuote = priceQuote;
        if (this.isFirstTimePriceQuote) {
            this.isFirstTimePriceQuote = false;
            quickPayImpressionLogging();
        }
        showLoadingState(false);
        this.quickPayAdapter.setPriceQuote(priceQuote);
        this.quickPayAdapter.updateLegalAndFxRow(this.billPriceQuote);
        setPayButtonClickListener(payButtonClickListener());
        setPayButtonPrice(this.billPriceQuote.getPrice().getTotal().formattedForDisplay());
        enablePayButton();
    }

    /* access modifiers changed from: protected */
    public boolean shouldApplyCreditByDefault() {
        this.shouldIncludeAirbnbCredit = true;
        return this.shouldIncludeAirbnbCredit;
    }

    /* access modifiers changed from: protected */
    public void requestPaymentOptions() {
        showLoadingState(true);
        this.paymentOptionsApi.getPaymentOptions(this.cartItem.quickPayParameters().productType().getServerKey(), this.mAccountManager.getCurrentUser().getDefaultCountryOfResidence(), false);
    }

    /* access modifiers changed from: protected */
    public void showAddPaymentMethods() {
        if (PaymentUtils.shouldShowNewAddPaymentMethodFlow()) {
            startActivityForResult(AddPaymentMethodActivity.intentForAddPayment(getActivity(), BillProductType.getProductTypeFromClientType(this.clientType), this.paymentOptions), 1002);
        } else {
            startActivityForResult(LegacyAddPaymentMethodActivity.intentForQuickPayWithClientType(getActivity(), this.clientType), 1002);
        }
    }

    /* access modifiers changed from: protected */
    public QuickPayConfirmAndPayParams getConfirmAndPayJitneyParams(long totalPrice, BillProductType productType) {
        return new QuickPayConfirmAndPayParams(this.selectedPaymentOption, this.settlementCurrency, null, totalPrice, productType);
    }

    private void showExistingPaymentOptions() {
        startActivityForResult(PaymentOptionsActivity.intentForPaymentOptionsWithQuickPayClient(getActivity(), this.paymentOptions, this.selectedPaymentOption, this.clientType, this.billPriceQuote.getPrice()), 1000);
    }

    private void quickPayImpressionLogging() {
        this.quickPayJitneyLogger.quickPayImpression(this.mCurrencyHelper.getLocalCurrencyString(), this.billPriceQuote.getPrice().getTotal().getAmountMicros(), this.cartItem.quickPayParameters().productType());
    }

    /* access modifiers changed from: protected */
    public void confirmAndPayLogging(boolean isSuccessful, NetworkException exception) {
        QuickPayConfirmAndPayParams quickPayConfirmAndPayParams = getConfirmAndPayJitneyParams(this.billPriceQuote.getPrice().getTotal().getAmountMicros(), this.cartItem.quickPayParameters().productType());
        if (isSuccessful) {
            this.quickPayJitneyLogger.confirmAndPaySuccess(quickPayConfirmAndPayParams);
        } else {
            this.quickPayJitneyLogger.confirmAndPayError(quickPayConfirmAndPayParams, exception);
        }
    }

    /* access modifiers changed from: private */
    public void launchPostalCodeRetryFlow() {
        startActivityForResult(CreditCardActivity.intentForPostalCodeRetry(getActivity(), ParcelStrap.make()), CreditCardActivity.REQUEST_CODE_POSTAL_RETRY);
    }

    /* access modifiers changed from: protected */
    public void showLoadingState(boolean shouldShowLoadingState) {
        if (shouldShowLoadingState) {
            this.quickPayAdapter.toggleLoadingState(true);
            this.payButton.setVisibility(8);
            return;
        }
        this.quickPayAdapter.toggleLoadingState(false);
        this.payButton.setVisibility(0);
    }

    private void showError(QuickPayError quickPayError) {
        switch (quickPayError.getErrorType()) {
            case CURRENCY_MISMATCH:
                onCurrencyMismatchError(quickPayError.getErrorMessage(), quickPayError.getSettlementCurrency());
                return;
            case IDEMPOTENCE_KEY_EXPIRED:
            case IDEMPOTENCE_KEY_CONFLICT:
                onIdempotenceKeyError(quickPayError.getErrorType(), quickPayError.getError());
                return;
            case POSTAL_CODE_MISMATCH:
                onPostalCodeMismatch(quickPayError.getErrorMessage());
                return;
            case GENERIC_ERROR:
                onNetworkException(quickPayError.getNetworkException());
                return;
            default:
                return;
        }
    }

    private void onNetworkException(NetworkException networkException) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), networkException);
    }

    private void onCurrencyMismatchError(String errorMessage, String settlementCurrency2) {
        this.settlementCurrency = settlementCurrency2;
        String str = errorMessage;
        ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.quick_pay_error_currency_mismatch_title), str, getString(C0880R.string.quick_pay_error_currency_mismatch_action, settlementCurrency2), QuickPayFragment$$Lambda$3.lambdaFactory$(this, settlementCurrency2), 0);
    }

    static /* synthetic */ void lambda$onCurrencyMismatchError$2(QuickPayFragment quickPayFragment, String settlementCurrency2, View v) {
        quickPayFragment.userAgreedToCurrencyMismatch = true;
        quickPayFragment.fetchBillPriceQuote(quickPayFragment.shouldIncludeAirbnbCredit, settlementCurrency2);
    }

    private void onIdempotenceKeyError(QuickPayErrorType errorType, String errorMessage) {
        ErrorUtils.showErrorUsingSnackbar(getView(), errorMessage, getString(C0880R.string.quick_pay_error_price_expired_error), 0);
        if (errorType == QuickPayErrorType.IDEMPOTENCE_KEY_EXPIRED) {
            showLoadingState(true);
            fetchBillPriceQuote(this.shouldIncludeAirbnbCredit, this.settlementCurrency);
        }
    }

    private void onPostalCodeMismatch(String errorMessage) {
        ErrorUtils.showErrorUsingSnackbar(getView(), getString(C0880R.string.error_title_postal_code_mismatch), errorMessage, getString(C0880R.string.error_action_postal_code_mismatch), QuickPayFragment$$Lambda$4.lambdaFactory$(this), -2);
    }

    public void onBillPriceQuoteRequestSuccess(BillPriceQuote priceQuote) {
        onPriceQuoteChanged(priceQuote);
    }

    public void onBillPriceQuoteRequestError(NetworkException networkException) {
        if (PaymentUtils.isValidPaymentOption(this.selectedPaymentOption)) {
            enablePayButton();
        }
        showError(new QuickPayError(networkException, this.airlockErrorHandler));
    }
}
