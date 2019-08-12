package com.airbnb.android.lib.payments.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.PaymentInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.CreditCardBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.DigitalRiverCreditCardBody.Builder;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.braintree.BraintreeCreditCardApi;
import com.airbnb.android.lib.payments.braintree.BraintreeFactory;
import com.airbnb.android.lib.payments.creditcard.brazil.activities.BrazilCreditCardDetailsActivity;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.request.BrazilCepRequest;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCepResponse;
import com.airbnb.android.lib.payments.creditcard.presenter.CreditCardDetailsPresenter;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardCvvTextWatcher;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardCvvTextWatcher.CardCvvTextListener;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardDateTextWatcher;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardDateTextWatcher.CardDateTextListener;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardNumberTextWatcher;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardNumberTextWatcher.CardNumberTextListener;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardPostalCodeTextWatcher;
import com.airbnb.android.lib.payments.creditcard.textwatcher.CardPostalCodeTextWatcher.CardPostalCodeTextListener;
import com.airbnb.android.lib.payments.creditcard.validation.CreditCardValidator;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverApi;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverTokenizerListener;
import com.airbnb.android.lib.payments.paymentinstruments.PaymentInstrumentsApi;
import com.airbnb.android.lib.payments.paymentinstruments.PaymentInstrumentsDelegate;
import com.airbnb.android.lib.payments.paymentinstruments.PaymentInstrumentsDelegate.PaymentInstrumentsDelegateListener;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.jitney.event.logging.QuickpayAddCcSection.p215v1.C2596QuickpayAddCcSection;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.primitives.AirButton;
import com.braintreepayments.api.BraintreeFragment;
import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import com.braintreepayments.api.interfaces.PaymentMethodNonceCreatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;
import icepick.State;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import p032rx.Observer;

public class CreditCardDetailsFragment extends AirFragment implements CardCvvTextListener, CardDateTextListener, CardNumberTextListener, CardPostalCodeTextListener, DigitalRiverTokenizerListener, PaymentInstrumentsDelegateListener {
    private static final String ARG_COUNTRY_CODE = "arg_country_code";
    private static final String ARG_PAYMENT_METHOD_TYPE = "arg_payment_method_type";
    private static final int REQUEST_CODE_ADD_BRAZIL_CC = 100;
    public static final String RESULT_EXTRA_CVV_PAYLOAD = "result_extra_cvv_payload";
    public static final String RESULT_EXTRA_PAYMENT_INSTRUMENT = "result_extra_payment_instrument";
    @State
    BraintreeCreditCard braintreeCreditCard;
    private BraintreeCreditCardApi braintreeCreditCardApi;
    private final BraintreeErrorListener braintreeErrorListener = new BraintreeErrorListener() {
        public void onError(Exception error) {
            CreditCardDetailsFragment.this.creditCardPresenter.enableAllInputLayouts();
            ErrorUtils.showErrorUsingSnackbar(CreditCardDetailsFragment.this.getView(), error.getLocalizedMessage());
            CreditCardDetailsFragment.this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.Error);
            CreditCardDetailsFragment.this.setNextButtonToNormal();
        }
    };
    BraintreeFactory braintreeFactory;
    private final PaymentMethodNonceCreatedListener braintreeNonceCreatedListener = new PaymentMethodNonceCreatedListener() {
        public void onPaymentMethodNonceCreated(PaymentMethodNonce paymentNonce) {
            if (!CreditCardDetailsFragment.this.isCreditCardVaulted) {
                BraintreeCreditCard creditCard = new BraintreeCreditCard();
                creditCard.setNonce(paymentNonce.getNonce());
                creditCard.setPostalCode(CreditCardDetailsFragment.this.cardPostalCodeInputLayout.getText().toString());
                creditCard.setCountry(CreditCardDetailsFragment.this.countryCode);
                CreditCardDetailsFragment.this.vaultBraintreeCreditCard(creditCard);
                return;
            }
            CreditCardDetailsFragment.this.returnResult(CreditCardDetailsFragment.this.paymentInstrument, paymentNonce.getNonce());
        }
    };
    final RequestListener<BrazilCepResponse> brazilCepListener = new C0699RL().onResponse(CreditCardDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(CreditCardDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    PaymentInputLayout cardCvvInputLayout;
    private CardCvvTextWatcher cardCvvTextWatcher;
    @BindView
    PaymentInputLayout cardDateInputLayout;
    private CardDateTextWatcher cardDateTextWatcher;
    @BindView
    LinearLayout cardDetailsContainer;
    @BindView
    PaymentInputLayout cardNumberInputLayout;
    @BindView
    PaymentInputLayout cardPostalCodeInputLayout;
    CreditCardValidator cardValidator;
    @State
    String countryCode;
    /* access modifiers changed from: private */
    public CreditCardDetailsPresenter creditCardPresenter;
    @State
    String cvvPayload;
    DigitalRiverApi digitalRiverApi;
    @State
    DigitalRiverCreditCard digitalRiverCreditCard;
    @BindView
    DocumentMarquee documentMarquee;
    @State
    boolean isCreditCardVaulted;
    QuickPayJitneyLogger jitneyLogger;
    @BindView
    AirButton nextButton;
    @State
    OldPaymentInstrument paymentInstrument;
    private PaymentInstrumentsApi paymentInstrumentsApi;
    @State
    PaymentMethodType paymentMethodType;
    @BindView
    AirToolbar toolbar;

    public void onPaymentInstrumentCreated(PaymentInstrument instrument) {
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.Successful);
        this.isCreditCardVaulted = true;
        switch (this.paymentInstrument.getType()) {
            case DigitalRiverCreditCard:
                this.paymentInstrument = this.digitalRiverCreditCard;
                this.paymentInstrument.setId(instrument.getId());
                this.paymentInstrument.setName(instrument.getDetailText());
                returnResult(this.paymentInstrument, this.cvvPayload);
                return;
            case BraintreeCreditCard:
                this.paymentInstrument = this.braintreeCreditCard;
                this.paymentInstrument.setId(instrument.getId());
                this.paymentInstrument.setName(instrument.getDetailText());
                this.braintreeCreditCardApi.tokenizeCvv(this.braintreeCreditCardApi.buildCreditCard("", "", "", this.cardCvvInputLayout.getText().toString(), ""));
                return;
            default:
                return;
        }
    }

    public void onPaymentInstrumentFailure(NetworkException networkException) {
        this.isCreditCardVaulted = false;
        this.creditCardPresenter.enableAllInputLayouts();
        ErrorUtils.showErrorUsingSnackbar(getView(), NetworkUtil.errorMessage(networkException));
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.Error);
        setNextButtonToNormal();
    }

    public static CreditCardDetailsFragment newInstance(PaymentMethodType paymentMethodType2, String countryCode2) {
        return (CreditCardDetailsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CreditCardDetailsFragment()).putSerializable(ARG_PAYMENT_METHOD_TYPE, paymentMethodType2)).putString("arg_country_code", countryCode2)).build();
    }

    static /* synthetic */ void lambda$new$0(CreditCardDetailsFragment creditCardDetailsFragment, BrazilCepResponse response) {
        creditCardDetailsFragment.setNextButtonToNormal();
        creditCardDetailsFragment.startActivityForResult(BrazilCreditCardDetailsActivity.intentForBrazil(creditCardDetailsFragment.getActivity(), response.brazilCep, DigitalRiverCreditCard.fromCreditCard(creditCardDetailsFragment.braintreeCreditCard)), 100);
    }

    static /* synthetic */ void lambda$new$1(CreditCardDetailsFragment creditCardDetailsFragment, AirRequestNetworkException e) {
        creditCardDetailsFragment.setNextButtonToNormal();
        ErrorUtils.showErrorUsingSnackbar(creditCardDetailsFragment.getView(), C0880R.string.error, C0880R.string.brazil_credit_cep_error_body);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.paymentMethodType = (PaymentMethodType) getArguments().getSerializable(ARG_PAYMENT_METHOD_TYPE);
            this.countryCode = getArguments().getString("arg_country_code");
        }
        BraintreeFragment braintreeFragment = this.braintreeFactory.createBraintreeFragment(getActivity());
        braintreeFragment.addListener(this.braintreeNonceCreatedListener);
        braintreeFragment.addListener(this.braintreeErrorListener);
        this.paymentInstrumentsApi = new PaymentInstrumentsDelegate(this.requestManager, this);
        this.braintreeCreditCardApi = this.braintreeFactory.createCreditCardApi(braintreeFragment);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_credit_card_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.documentMarquee.setTitle((CharSequence) getString(C0880R.string.add_credit_card_details_marquee));
        this.cardNumberInputLayout.setTitle((CharSequence) getString(C0880R.string.add_credit_card_number_title));
        this.cardNumberInputLayout.setHint((CharSequence) getString(C0880R.string.add_credit_card_number_hint));
        this.cardNumberInputLayout.addTextChangedListener(new CardNumberTextWatcher(this, this.cardValidator, this.cardNumberInputLayout));
        this.cardDateInputLayout.setTitle((CharSequence) getString(C0880R.string.add_credit_card_valid_till_title));
        this.cardDateInputLayout.setHint((CharSequence) getString(C0880R.string.add_credit_card_valid_till_hint));
        this.cardDateTextWatcher = new CardDateTextWatcher(this, this.cardValidator, this.cardDateInputLayout, new SimpleDateFormat("MM / yy", Locale.US), Calendar.getInstance());
        this.cardDateInputLayout.addTextChangedListener(this.cardDateTextWatcher);
        this.cardCvvInputLayout.setTitle((CharSequence) getString(C0880R.string.add_credit_card_cvv_title));
        this.cardCvvInputLayout.setHint((CharSequence) getString(C0880R.string.add_credit_card_cvv_hint));
        this.cardCvvTextWatcher = new CardCvvTextWatcher(this, this.cardValidator);
        this.cardCvvInputLayout.addTextChangedListener(this.cardCvvTextWatcher);
        this.cardPostalCodeInputLayout.setTitle((CharSequence) this.countryCode.equals(AirbnbConstants.COUNTRY_CODE_BRAZIL) ? getString(C0880R.string.add_credit_card_zipcode_title_cep) : getString(C0880R.string.add_credit_card_zipcode_title));
        this.cardPostalCodeInputLayout.setHint((CharSequence) getString(C0880R.string.add_credit_card_zipcode_hint));
        this.cardPostalCodeInputLayout.setInputTypeToText();
        this.cardPostalCodeInputLayout.addTextChangedListener(new CardPostalCodeTextWatcher(this, this.cardValidator));
        this.creditCardPresenter = new CreditCardDetailsPresenter(this.cardNumberInputLayout, this.cardDateInputLayout, this.cardCvvInputLayout, this.cardPostalCodeInputLayout);
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == -1) {
            returnResult((OldPaymentInstrument) data.getSerializableExtra(BrazilCreditCardDetailsActivity.RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT), data.getStringExtra(BrazilCreditCardDetailsActivity.RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT_CVV_PAYLOAD));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick
    public void onNextButtonClicked() {
        setNextButtonToLoading();
        this.creditCardPresenter.disableAllInputLayouts();
        this.braintreeCreditCard = buildCreditCard();
        if (isCountryCodeBrazil(this.countryCode)) {
            executeBrazilCepRequest();
        } else if (isCreditCardDigitalRiver(this.paymentMethodType)) {
            tokenizeDigitalRiverCreditCard();
        } else {
            tokenizeBraintreeCreditCard(this.braintreeCreditCard);
        }
    }

    public void onDigitalRiverCreditCardTokenized(String csePayload) {
        this.cvvPayload = csePayload;
        vaultDigitalRiverCreditCard(csePayload);
    }

    public void onCardNumberFormatted(CardType cardType) {
        this.creditCardPresenter.showCardLogo(cardType);
    }

    public void onFullCardNumberEntered(CardType cardType, boolean isValidCardNumber) {
        checkCardNumber(isValidCardNumber);
        enableNextButton();
        this.cardCvvTextWatcher.updateCardType(cardType);
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.CcNumber);
    }

    public void hideCardNumberError() {
        this.cardNumberInputLayout.hideError();
        this.creditCardPresenter.hideSnackbarError();
    }

    public void showCardNumberError() {
        this.cardNumberInputLayout.showError();
    }

    public void onFullCardDateEntered() {
        enableNextButton();
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.ExpirationDate);
    }

    public void hideCardDateError() {
        this.cardDateInputLayout.hideError();
    }

    public void showCardDateError() {
        this.cardDateInputLayout.showError();
    }

    public void onFullCardCvvEntered() {
        enableNextButton();
        this.cardCvvInputLayout.hideError();
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.CvvCode);
    }

    public void hideCardCvvError() {
        this.cardCvvInputLayout.hideError();
    }

    public void showCardCvvError() {
        this.cardCvvInputLayout.showError();
    }

    public void onFullPostalCodeEntered() {
        enableNextButton();
        this.jitneyLogger.creditCardDetailEntered(C2596QuickpayAddCcSection.ZipCode);
    }

    /* access modifiers changed from: private */
    public void vaultBraintreeCreditCard(BraintreeCreditCard creditCard) {
        this.paymentInstrumentsApi.createPaymentInstrument(CreatePaymentInstrumentRequest.forCreditCard(CreditCardBody.make().paymentMethodNonce(creditCard.getNonce()).postalCode(creditCard.getPostalCode()).country(creditCard.getCountry()).build()));
    }

    private void vaultDigitalRiverCreditCard(String csePayload) {
        this.paymentInstrumentsApi.createPaymentInstrument(CreatePaymentInstrumentRequest.forDigitalRiverCreditCard(new Builder().paymentMethodCsePayload(csePayload).build()));
    }

    private void executeBrazilCepRequest() {
        new BrazilCepRequest(formatCep(this.cardPostalCodeInputLayout.getText().toString())).withListener((Observer) this.brazilCepListener).execute(this.requestManager);
    }

    private void checkCardNumber(boolean isValidCardNumber) {
        if (isValidCardNumber) {
            this.creditCardPresenter.showCardDetailsRow(this.documentMarquee, this.cardDetailsContainer);
            this.creditCardPresenter.hideSnackbarError();
            return;
        }
        this.creditCardPresenter.showSnackbarError(getView(), getString(C0880R.string.add_credit_card_number_error_title), getString(C0880R.string.add_credit_card_number_error_body));
    }

    private BraintreeCreditCard buildCreditCard() {
        return this.braintreeCreditCardApi.buildCreditCard(this.cardNumberInputLayout.getText().toString(), String.valueOf(this.cardDateTextWatcher.getExpirationDate().get(2) + 1), String.valueOf(this.cardDateTextWatcher.getExpirationDate().get(1)), this.cardCvvInputLayout.getText().toString(), this.cardPostalCodeInputLayout.getText().toString());
    }

    private void tokenizeBraintreeCreditCard(BraintreeCreditCard creditCard) {
        this.paymentInstrument = creditCard;
        this.braintreeCreditCardApi.tokenize(creditCard);
    }

    private void tokenizeDigitalRiverCreditCard() {
        try {
            this.digitalRiverCreditCard = DigitalRiverCreditCard.fromCreditCard(this.braintreeCreditCard);
            this.digitalRiverApi.tokenize(this.digitalRiverCreditCard, this.mAccountManager.getCurrentUser().getFirstName(), this.mAccountManager.getCurrentUser().getLastName(), this);
        } catch (IOException e) {
            setNextButtonToNormal();
            ErrorUtils.showErrorUsingSnackbar(getView(), C0880R.string.request_error);
        }
    }

    private void enableNextButton() {
        if (this.cardValidator.areCardDetailsEntered(this.cardNumberInputLayout.getText().toString(), this.cardDateInputLayout.getText().toString(), this.cardCvvInputLayout.getText().toString(), this.cardPostalCodeInputLayout.getText().toString())) {
            this.nextButton.setEnabled(true);
            this.nextButton.setState(AirButton.State.Normal);
        }
    }

    private void setNextButtonToLoading() {
        this.nextButton.setEnabled(false);
        this.nextButton.setState(AirButton.State.Loading);
    }

    /* access modifiers changed from: private */
    public void setNextButtonToNormal() {
        this.nextButton.setEnabled(true);
        this.nextButton.setState(AirButton.State.Normal);
    }

    private boolean isCountryCodeBrazil(String countryCode2) {
        return TextUtils.equals(countryCode2, AirbnbConstants.COUNTRY_CODE_BRAZIL);
    }

    private boolean isCreditCardDigitalRiver(PaymentMethodType paymentMethodType2) {
        return paymentMethodType2 == PaymentMethodType.DigitalRiverCreditCard;
    }

    private String formatCep(String unformattedCep) {
        return TextUtil.removeNonDigits(unformattedCep);
    }

    /* access modifiers changed from: private */
    public void returnResult(OldPaymentInstrument creditCard, String cvvPayload2) {
        KeyboardUtils.dismissSoftKeyboard(getView());
        Intent intent = new Intent();
        intent.putExtra("result_extra_payment_instrument", creditCard);
        intent.putExtra("result_extra_cvv_payload", cvvPayload2);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }
}
