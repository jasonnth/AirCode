package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.QuickPayJitneyLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.datepicker.DatePickerDialog;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.payments.DigitalRiverCreditCard;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.requests.payments.CreatePaymentInstrumentRequest;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.DigitalRiverCreditCardBody;
import com.airbnb.android.core.requests.payments.requestbodies.CreatePaymentInstrumentRequestBody.DigitalRiverCreditCardBody.Builder;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.creditcard.brazil.activities.BrazilCreditCardDetailsActivity;
import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCep;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilCpfFormatter;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilDefaultTextFormatter;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilMobileNumberFormatter;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.formatter.BrazilPaymentInputFormatter;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.watcher.BrazilCreditCardTextWatcher;
import com.airbnb.android.lib.payments.creditcard.brazil.textinput.watcher.BrazilCreditCardTextWatcher.BrazilCreditCardTextChangedListener;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverApi;
import com.airbnb.android.lib.payments.digitalriver.DigitalRiverTokenizerListener;
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
import icepick.State;
import java.io.IOException;
import p032rx.Observer;

public class BrazilCreditCardDetailsFragment extends AirFragment implements BrazilCreditCardTextChangedListener, DigitalRiverTokenizerListener {
    private static final String ARG_BRAZIL_CEP = "arg_brazil_cep";
    private static final String ARG_CREDIT_CARD = "arg_credit_card";
    @BindView
    PaymentInputLayout birthdateInput;
    @State
    BrazilCep brazilCep;
    @BindView
    PaymentInputLayout buildingNumberInput;
    @BindView
    PaymentInputLayout cityInput;
    @BindView
    PaymentInputLayout complementInput;
    @State
    DigitalRiverCreditCard creditCard;
    @State
    String csePayload;
    DigitalRiverApi digitalRiverApi;
    @BindView
    DocumentMarquee documentMarquee;
    @State
    String firstName;
    @BindView
    PaymentInputLayout firstNameInput;
    BrazilPaymentInputFormatter inputFormatter;
    QuickPayJitneyLogger jitneyLogger;
    @State
    String lastName;
    @BindView
    PaymentInputLayout lastNameInput;
    @State
    String mobileNumber;
    @BindView
    PaymentInputLayout mobileNumberInput;
    @BindView
    AirButton nextButton;
    final RequestListener<PaymentInstrumentResponse> requestListener = new C0699RL().onResponse(BrazilCreditCardDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(BrazilCreditCardDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    AirDate selectedBirthday;
    @BindView
    PaymentInputLayout stateInput;
    @BindView
    PaymentInputLayout streetAddressInput;
    @BindView
    PaymentInputLayout taxpayerIdInput;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment, PaymentInstrumentResponse response) {
        OldPaymentInstrument paymentInstrument = brazilCreditCardDetailsFragment.creditCard;
        paymentInstrument.setId(response.paymentInstrument.getId());
        paymentInstrument.setName(response.paymentInstrument.getDetailText());
        brazilCreditCardDetailsFragment.returnResult(paymentInstrument, brazilCreditCardDetailsFragment.csePayload);
    }

    static /* synthetic */ void lambda$new$1(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment, AirRequestNetworkException networkException) {
        brazilCreditCardDetailsFragment.setNextButtonToNormal();
        ErrorUtils.showErrorUsingSnackbar(brazilCreditCardDetailsFragment.getView(), networkException.getMessage());
    }

    public static BrazilCreditCardDetailsFragment newInstance(BrazilCep brazilCep2, DigitalRiverCreditCard creditCard2) {
        return (BrazilCreditCardDetailsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BrazilCreditCardDetailsFragment()).putParcelable(ARG_BRAZIL_CEP, brazilCep2)).putSerializable(ARG_CREDIT_CARD, creditCard2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.jitneyLogger.brazilCreditCardFlow(C2596QuickpayAddCcSection.BrazilDetailsImpression);
            User currentUser = this.mAccountManager.getCurrentUser();
            this.firstName = currentUser.getFirstName();
            this.lastName = currentUser.getLastName();
            this.mobileNumber = currentUser.getPhone();
            this.selectedBirthday = currentUser.getBirthdate();
            this.brazilCep = (BrazilCep) getArguments().getParcelable(ARG_BRAZIL_CEP);
            this.creditCard = (DigitalRiverCreditCard) getArguments().getSerializable(ARG_CREDIT_CARD);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_brazil_credit_card_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.documentMarquee.setTitle(C0880R.string.brazil_credit_card_marquee_title);
        this.firstNameInput.setTitle(C0880R.string.brazil_credit_card_cardholders_name);
        this.firstNameInput.setHint(C0880R.string.brazil_credit_card_first_name_hint);
        this.firstNameInput.setText((CharSequence) this.firstName);
        this.firstNameInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilDefaultTextFormatter(), this));
        this.firstNameInput.setInputTypeToText();
        this.firstNameInput.setSelection(this.firstNameInput.getText().length());
        this.lastNameInput.setHint(C0880R.string.brazil_credit_card_last_name_hint);
        this.lastNameInput.setText((CharSequence) this.lastName);
        this.lastNameInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilDefaultTextFormatter(), this));
        this.lastNameInput.setInputTypeToText();
        this.birthdateInput.setTitle(C0880R.string.brazil_credit_card_birthdate_title);
        this.birthdateInput.setHint((CharSequence) DatePickerDialog.getDefaultBirthdate().formatDate(getString(C0880R.string.mdy_format_shorter)));
        this.birthdateInput.setText((CharSequence) this.selectedBirthday.formatDate(getString(C0880R.string.mdy_format_shorter)));
        this.birthdateInput.makeInputTextClickableOnly(BrazilCreditCardDetailsFragment$$Lambda$3.lambdaFactory$(this));
        this.mobileNumberInput.setTitle(C0880R.string.brazil_credit_card_mobile_number_title);
        this.mobileNumberInput.setHint(C0880R.string.brazil_credit_card_mobile_number_hint);
        this.mobileNumberInput.setText((CharSequence) this.mobileNumber);
        this.mobileNumberInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilMobileNumberFormatter(this.inputFormatter, this.mobileNumberInput), this));
        this.taxpayerIdInput.setTitle(C0880R.string.brazil_credit_card_taxpayer_id_title);
        this.taxpayerIdInput.setHint(C0880R.string.brazil_credit_card_taxpayer_id_hint);
        this.taxpayerIdInput.setInputMaxLength(14);
        this.taxpayerIdInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilCpfFormatter(this.inputFormatter, this.taxpayerIdInput), this));
        this.streetAddressInput.setTitle(C0880R.string.brazil_credit_card_street_address_title);
        this.streetAddressInput.setHint(C0880R.string.brazil_credit_card_street_address_hint);
        this.streetAddressInput.setText((CharSequence) this.brazilCep.address());
        this.streetAddressInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilDefaultTextFormatter(), this));
        this.streetAddressInput.setInputTypeToText();
        this.buildingNumberInput.setTitle(C0880R.string.brazil_credit_card_building_number_title);
        this.buildingNumberInput.setHint(C0880R.string.brazil_credit_card_building_number_hint);
        this.buildingNumberInput.setInputTypeToText();
        this.complementInput.setTitle(C0880R.string.brazil_credit_card_complement_title);
        this.complementInput.setHint(C0880R.string.brazil_credit_card_complement_hint);
        this.complementInput.setInputTypeToText();
        this.cityInput.setTitle(C0880R.string.brazil_credit_card_city_title);
        this.cityInput.setHint(C0880R.string.brazil_credit_card_city_hint);
        this.cityInput.setText((CharSequence) this.brazilCep.city());
        this.cityInput.setInputTypeToText();
        this.cityInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilDefaultTextFormatter(), this));
        this.stateInput.setTitle(C0880R.string.brazil_credit_card_state_title);
        this.stateInput.setHint(C0880R.string.brazil_credit_card_state_hint);
        this.stateInput.setText((CharSequence) this.brazilCep.state());
        this.stateInput.setInputTypeToText();
        this.stateInput.addTextChangedListener(new BrazilCreditCardTextWatcher(new BrazilDefaultTextFormatter(), this));
        this.nextButton.setEnabled(this.inputFormatter.areInputsValid(this.firstNameInput, this.lastNameInput, this.birthdateInput, this.mobileNumberInput, this.taxpayerIdInput, this.streetAddressInput, this.cityInput, this.stateInput));
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case DatePickerDialog.DATE_PICKER_OK /*2002*/:
                    this.selectedBirthday = (AirDate) data.getParcelableExtra("date");
                    this.birthdateInput.setText((CharSequence) this.selectedBirthday.formatDate(getString(C0880R.string.mdy_format_shorter)));
                    return;
                default:
                    return;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onTextChanged(String text) {
        this.nextButton.setEnabled(this.inputFormatter.areInputsValid(this.firstNameInput, this.lastNameInput, this.birthdateInput, this.mobileNumberInput, this.taxpayerIdInput, this.streetAddressInput, this.cityInput, this.stateInput));
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void launchBirthdayPicker() {
        DatePickerDialog.newInstance(this.selectedBirthday == null ? DatePickerDialog.getDefaultBirthdate() : this.selectedBirthday, true, this, C0880R.string.select_birth_date, null, AirDate.today()).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClicked() {
        this.jitneyLogger.brazilCreditCardFlow(C2596QuickpayAddCcSection.BrazilDetailsNext);
        setNextButtonToLoading();
        try {
            this.digitalRiverApi.tokenize(this.creditCard, this.firstNameInput.getText().toString(), this.lastNameInput.getText().toString(), this);
        } catch (IOException e) {
            setNextButtonToNormal();
            ErrorUtils.showErrorUsingSnackbar(getView(), C0880R.string.request_error);
        }
    }

    public void onDigitalRiverCreditCardTokenized(String csePayload2) {
        this.csePayload = csePayload2;
        vaultCreditCard(this.creditCard, csePayload2);
    }

    private void setNextButtonToLoading() {
        this.nextButton.setEnabled(false);
        this.nextButton.setState(AirButton.State.Loading);
    }

    private void setNextButtonToNormal() {
        this.nextButton.setEnabled(true);
        this.nextButton.setState(AirButton.State.Normal);
    }

    private void vaultCreditCard(DigitalRiverCreditCard creditCard2, String csePayload2) {
        CreatePaymentInstrumentRequest.forDigitalRiverCreditCard(digitalRiverCreditCardBody(creditCard2, csePayload2, TextUtil.removeNonDigits(this.mobileNumberInput.getText().toString()))).withListener((Observer) this.requestListener).execute(this.requestManager);
    }

    private DigitalRiverCreditCardBody digitalRiverCreditCardBody(DigitalRiverCreditCard creditCard2, String csePayload2, String rawPhoneNumber) {
        Builder birthdate = new Builder().paymentMethodCsePayload(csePayload2).userId(String.valueOf(this.mAccountManager.getCurrentUserId())).firstName(this.firstNameInput.getText().toString()).lastName(this.lastNameInput.getText().toString()).birthdate(this.selectedBirthday.getLocalDate().toString());
        if (!this.inputFormatter.isCountryCodeInPhoneNumber(rawPhoneNumber)) {
            rawPhoneNumber = this.inputFormatter.prependCountryCodeToPhoneNumber(rawPhoneNumber);
        }
        return birthdate.mobilePhoneNumber(rawPhoneNumber).brazilCpf(TextUtil.removeNonDigits(this.taxpayerIdInput.getText().toString())).streetAddress1(this.streetAddressInput.getText().toString()).streetAddress2(this.buildingNumberInput.getText().toString()).streetAddress3(this.complementInput.getText().toString()).locality(this.cityInput.getText().toString()).region(this.stateInput.getText().toString()).countryCode(AirbnbConstants.COUNTRY_CODE_BRAZIL).postalCode(TextUtil.removeNonDigits(creditCard2.getPostalCode())).build();
    }

    private void returnResult(OldPaymentInstrument paymentInstrument, String csePayload2) {
        KeyboardUtils.dismissSoftKeyboard(getView());
        Intent intent = new Intent();
        intent.putExtra(BrazilCreditCardDetailsActivity.RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT, paymentInstrument);
        intent.putExtra(BrazilCreditCardDetailsActivity.RESULT_EXTRA_BRAZIL_PAYMENT_INSTRUMENT_CVV_PAYLOAD, csePayload2);
        getActivity().setResult(-1, intent);
        getActivity().finish();
    }
}
