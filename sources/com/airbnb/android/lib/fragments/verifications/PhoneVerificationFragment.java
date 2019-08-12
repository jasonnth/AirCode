package com.airbnb.android.lib.fragments.verifications;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.CallingCodeDialogFragment;
import com.airbnb.android.core.interfaces.CallingCodeSelectedListener;
import com.airbnb.android.core.models.CallingCodeEntry;
import com.airbnb.android.core.models.SecurityCheck;
import com.airbnb.android.core.models.Verification.Type;
import com.airbnb.android.core.requests.CallPhoneVerificationRequest;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.PhoneVerificationHelpDialogFragment;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.AnimationUtils;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.rxgroups.TaggedObserver;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import icepick.State;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;

public class PhoneVerificationFragment extends VerificationsFragment {
    private static final int EXPECTED_CONFIRMATION_CODE_LENGTH = 4;
    private static final int Idle = 0;
    private static final int REQUEST_CODE_HELP_DIALOG = 10001;
    private static final int RequestingCall = 3;
    private static final int RequestingCode = 1;
    private static final int SendingCode = 2;
    @BindView
    TextView callingCodeButton;
    final CallingCodeSelectedListener callingCodeListener = new CallingCodeSelectedListener() {
        public void onSelectedCallingCode(CallingCodeEntry entry) {
            String newCountryCode = entry.getCountryCode();
            if (!newCountryCode.equals(PhoneVerificationFragment.this.countryCode)) {
                PhoneVerificationFragment.this.countryCode = newCountryCode;
                PhoneVerificationFragment.this.handleCallingCodeChange(entry.getCallingCode());
            }
        }
    };
    @BindView
    EditText confirmationCodeEditText;
    @BindView
    View confirmationCodeEntryContainer;
    @BindView
    TextView confirmationCodeNotification;
    @State
    String countryCode;
    @State
    boolean displayConfirmationCodeScreen;
    private TextWatcher phoneFormattingTextWatcher;
    @State
    PhoneNumber phoneNumber;
    @BindView
    EditText phoneNumberEditText;
    @BindView
    View phoneNumberEntryContainer;
    PhoneNumberUtil phoneNumberUtil;
    private final SimpleTextWatcher phoneNumberWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            try {
                PhoneVerificationFragment.this.phoneNumber = PhoneVerificationFragment.this.phoneNumberUtil.parse(s.toString(), PhoneVerificationFragment.this.countryCode);
                if (PhoneVerificationFragment.this.phoneNumberUtil.isValidNumberForRegion(PhoneVerificationFragment.this.phoneNumber, PhoneVerificationFragment.this.countryCode)) {
                    PhoneVerificationFragment.this.textCodeButton.setEnabled(true);
                } else {
                    PhoneVerificationFragment.this.textCodeButton.setEnabled(false);
                }
            } catch (NumberParseException e) {
                PhoneVerificationFragment.this.textCodeButton.setEnabled(false);
            }
        }
    };
    PhoneUtil phoneUtil;
    final RequestListener<Object> requestCallListener = new C0699RL().onResponse(PhoneVerificationFragment$$Lambda$5.lambdaFactory$(this)).onError(PhoneVerificationFragment$$Lambda$6.lambdaFactory$(this)).build();
    final RequestListener<Object> requestConfirmationCodeListener = new C0699RL().onResponse(PhoneVerificationFragment$$Lambda$1.lambdaFactory$(this)).onError(PhoneVerificationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    int requestState = 0;
    @BindView
    View skipButton;
    @BindView
    TextView textCodeButton;
    final RequestListener<Object> verifyConfirmationCodeListener = new C0699RL().onResponse(PhoneVerificationFragment$$Lambda$3.lambdaFactory$(this)).onError(PhoneVerificationFragment$$Lambda$4.lambdaFactory$(this)).build();

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestState {
    }

    public static PhoneVerificationFragment newInstance() {
        return new PhoneVerificationFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verifications_phone, container, false);
        bindViews(view);
        setUpFields();
        setUpVerificationsListener();
        setUpCallingCodeListener();
        showOrHideSkipButton(this.skipButton);
        return view;
    }

    public void onStart() {
        super.onStart();
        track("activate_account_phone", "no_phone", "impression");
    }

    public void onResume() {
        super.onResume();
        this.phoneNumberEditText.addTextChangedListener(this.phoneNumberWatcher);
        handleRequestState();
    }

    public void onPause() {
        this.phoneNumberEditText.removeTextChangedListener(this.phoneNumberWatcher);
        super.onPause();
    }

    public void onDestroyView() {
        getVerificationsActivity().deregisterVerificationsListener();
        if (this.phoneFormattingTextWatcher != null) {
            this.phoneNumberEditText.removeTextChangedListener(this.phoneFormattingTextWatcher);
        }
        this.skipButton.setOnClickListener(null);
        super.onDestroyView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == REQUEST_CODE_HELP_DIALOG) {
            handleHelpChoice(data.getIntExtra(PhoneVerificationHelpDialogFragment.RESULT_EXTRA_HELP_CHOICE, -1));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleHelpChoice(int helpChoice) {
        switch (helpChoice) {
            case 0:
                track("activate_account_phone", "code", "click", "change_number_button");
                resetView();
                return;
            case 1:
                track("activate_account_phone", "code", "click", "send_code_again_button");
                sendConfirmationCode();
                return;
            case 2:
                track("activate_account_phone", "code", "click", "call_me_button");
                this.confirmationCodeEditText.setText("");
                requestPhoneCall();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void handleCallingCodeChange(String callingCode) {
        changeEditTextForI18n();
        this.phoneNumber = null;
        this.phoneNumberEditText.setText("");
        this.callingCodeButton.setText("+" + callingCode);
        KeyboardUtils.showSoftKeyboard(this.phoneNumberEditText);
    }

    private void setUpFields() {
        setUpCountryCode();
        PhoneVerificationFragmentPermissionsDispatcher.setUpPhoneNumberWithCheck(this);
        setUpPhoneNumberEditText();
        setUpConfirmationCodeEditText();
        setUpCallingCodeButton();
        if (this.displayConfirmationCodeScreen) {
            displayConfirmationCodeEntryContainer(false);
        } else {
            displayPhoneEntryContainer(false);
        }
    }

    private void setUpCountryCode() {
        this.countryCode = this.phoneUtil.getSimCountryCodeUppercase();
        if (TextUtils.isEmpty(this.countryCode)) {
            String possibleCountry = Locale.getDefault().getCountry();
            if (TextUtils.isEmpty(possibleCountry)) {
                this.countryCode = "US";
                AirbnbEventLogger.track(SecurityCheck.STRATEGY_PHONE_VERIFICATION, Strap.make().mo11639kv("country_code", "default_US"));
                return;
            }
            this.countryCode = possibleCountry;
            AirbnbEventLogger.track(SecurityCheck.STRATEGY_PHONE_VERIFICATION, Strap.make().mo11639kv("country_code", this.countryCode));
        }
    }

    private void setUpPhoneNumberEditText() {
        if (this.phoneNumber == null) {
            this.phoneNumberEditText.requestFocus();
        } else {
            this.phoneNumberEditText.setText(formatNumber(this.phoneNumber));
        }
        changeEditTextForI18n();
    }

    @SuppressLint({"NewApi"})
    private void changeEditTextForI18n() {
        this.phoneNumberEditText.setHint(formatNumber(this.phoneNumberUtil.getExampleNumber(this.countryCode)));
        if (AndroidVersion.isAtLeastLollipop()) {
            if (this.phoneFormattingTextWatcher != null) {
                this.phoneNumberEditText.removeTextChangedListener(this.phoneFormattingTextWatcher);
            }
            this.phoneFormattingTextWatcher = new PhoneNumberFormattingTextWatcher(this.countryCode);
            this.phoneNumberEditText.addTextChangedListener(this.phoneFormattingTextWatcher);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setUpPhoneNumber() {
        if (this.phoneNumber == null) {
            this.phoneNumber = this.phoneUtil.fetchPhoneNumber();
        }
    }

    private String formatNumber(PhoneNumber phoneNumber2) {
        if (phoneNumber2 == null) {
            return null;
        }
        return this.phoneNumberUtil.format(phoneNumber2, PhoneNumberFormat.NATIONAL);
    }

    private void resetView() {
        track("activate_account_phone", "no_phone", "impression");
        this.confirmationCodeEditText.setText("");
        this.displayConfirmationCodeScreen = false;
        displayPhoneEntryContainer(true);
    }

    private void setUpConfirmationCodeEditText() {
        this.confirmationCodeEditText.addTextChangedListener(new SimpleTextWatcher() {
            public void onTextChanged(CharSequence code, int start, int before, int count) {
                if (code.length() == 4) {
                    PhoneVerificationFragment.this.verifyConfirmationCode(code.toString());
                    KeyboardUtils.dismissSoftKeyboard((View) PhoneVerificationFragment.this.confirmationCodeEditText);
                }
            }
        });
    }

    private void setUpCallingCodeButton() {
        String callingCode = this.phoneUtil.getCallingCode(this.phoneUtil.getSimCountryCodeUppercase());
        this.callingCodeButton.setText(callingCode == null ? "+1" : "+" + callingCode);
        this.callingCodeButton.setOnClickListener(PhoneVerificationFragment$$Lambda$7.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpCallingCodeButton$0(PhoneVerificationFragment phoneVerificationFragment, View v) {
        track("activate_account_phone", "no_phone", "click", "change_calling_code");
        CallingCodeDialogFragment.newInstance(phoneVerificationFragment.callingCodeListener).show(phoneVerificationFragment.getFragmentManager(), CallingCodeDialogFragment.TAG);
    }

    private void setUpCallingCodeListener() {
        CallingCodeDialogFragment fragment = (CallingCodeDialogFragment) getFragmentManager().findFragmentByTag(CallingCodeDialogFragment.TAG);
        if (fragment != null) {
            fragment.setListener(this.callingCodeListener);
        }
    }

    @OnFocusChange
    public void onFocusChangeConfirmationCode(boolean focused) {
        if (focused) {
            KeyboardUtils.showSoftKeyboard(getActivity(), getView());
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void sendConfirmationCode() {
        track("activate_account_phone", "no_phone", "click", "text_me_button");
        try {
            this.phoneNumber = this.phoneNumberUtil.parse(this.phoneNumberEditText.getText().toString(), this.countryCode);
            String formattedNumber = this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberFormat.NATIONAL);
            this.confirmationCodeNotification.setText(getString(C0880R.string.verifications_phone_instructions, formattedNumber));
            requestConfirmationCode(this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberFormat.E164));
        } catch (NumberParseException e) {
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void showHelpDialog() {
        track("activate_account_phone", "code", "click", "having_trouble_button");
        PhoneVerificationHelpDialogFragment.newInstance(REQUEST_CODE_HELP_DIALOG).show(getFragmentManager(), (String) null);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void skip() {
        if (this.phoneNumberEntryContainer.getVisibility() == 0) {
            track("activate_account_phone", "no_phone", "click", "skip_button");
        } else {
            track("activate_account_phone", "code", "click", "skip_button");
        }
        KeyboardUtils.dismissSoftKeyboard((View) this.phoneNumberEditText);
        getVerificationsActivity().doneWithVerification(true);
    }

    private void requestConfirmationCode(String formattedPhoneNumber) {
        showLoader(true);
        this.requestManager.executeOrResubscribe(UpdatePhoneNumberRequest.addPhoneNumber(formattedPhoneNumber, this.requestConfirmationCodeListener), this.requestConfirmationCodeListener);
        this.requestState = 1;
    }

    /* access modifiers changed from: private */
    public void verifyConfirmationCode(String confirmationCode) {
        showLoader(true);
        this.requestManager.executeOrResubscribe(UpdatePhoneNumberRequest.verifyPhoneNumber(confirmationCode, this.verifyConfirmationCodeListener), this.verifyConfirmationCodeListener);
        this.requestState = 2;
    }

    private void requestPhoneCall() {
        this.requestManager.executeOrResubscribe(new CallPhoneVerificationRequest(this.phoneNumberUtil.format(this.phoneNumber, PhoneNumberFormat.E164), this.requestCallListener), this.requestCallListener);
        this.requestState = 3;
    }

    private void displayPhoneEntryContainer(boolean withFading) {
        if (withFading) {
            track("activate_account_phone", "no_phone", "impression");
            AnimationUtils.fadeInOut(this.phoneNumberEntryContainer, this.confirmationCodeEntryContainer);
            return;
        }
        this.phoneNumberEntryContainer.setVisibility(0);
        this.phoneNumberEntryContainer.setAlpha(1.0f);
        this.confirmationCodeEntryContainer.setVisibility(4);
        this.confirmationCodeEntryContainer.setAlpha(0.0f);
    }

    private void displayConfirmationCodeEntryContainer(boolean withFading) {
        if (withFading) {
            track("activate_account_phone", "code", "impression");
            AnimationUtils.fadeInOut(this.confirmationCodeEntryContainer, this.phoneNumberEntryContainer);
            return;
        }
        this.confirmationCodeEntryContainer.setVisibility(0);
        this.confirmationCodeEntryContainer.setAlpha(1.0f);
        this.phoneNumberEntryContainer.setVisibility(4);
        this.phoneNumberEntryContainer.setAlpha(0.0f);
    }

    private void handleRequestState() {
        if (this.requestState == 1) {
            showLoader(true);
            this.requestManager.resubscribe((TaggedObserver<T>) this.requestConfirmationCodeListener, UpdatePhoneNumberRequest.class);
        } else if (this.requestState == 2) {
            showLoader(true);
            this.requestManager.resubscribe((TaggedObserver<T>) this.verifyConfirmationCodeListener, UpdatePhoneNumberRequest.class);
        } else if (this.requestState == 3) {
            showLoader(true);
            this.requestManager.resubscribe((TaggedObserver<T>) this.requestCallListener, CallPhoneVerificationRequest.class);
        }
    }

    static /* synthetic */ void lambda$new$1(PhoneVerificationFragment phoneVerificationFragment, Object response) {
        phoneVerificationFragment.showLoader(false);
        phoneVerificationFragment.displayConfirmationCodeScreen = true;
        phoneVerificationFragment.requestState = 0;
        if (phoneVerificationFragment.confirmationCodeEntryContainer.getVisibility() == 4) {
            phoneVerificationFragment.displayConfirmationCodeEntryContainer(true);
        }
    }

    static /* synthetic */ void lambda$new$2(PhoneVerificationFragment phoneVerificationFragment, AirRequestNetworkException e) {
        phoneVerificationFragment.showLoader(false);
        phoneVerificationFragment.requestState = 0;
        Toast.makeText(phoneVerificationFragment.getActivity(), C0880R.string.sms_number_error_please_try_again, 0).show();
    }

    static /* synthetic */ void lambda$new$3(PhoneVerificationFragment phoneVerificationFragment, Object response) {
        phoneVerificationFragment.showLoader(false);
        phoneVerificationFragment.requestState = 0;
        KeyboardUtils.dismissSoftKeyboard((View) phoneVerificationFragment.phoneNumberEditText);
        phoneVerificationFragment.getVerificationsActivity().doneWithVerification(false);
    }

    static /* synthetic */ void lambda$new$4(PhoneVerificationFragment phoneVerificationFragment, AirRequestNetworkException e) {
        phoneVerificationFragment.showLoader(false);
        phoneVerificationFragment.requestState = 0;
        Toast.makeText(phoneVerificationFragment.getActivity(), C0880R.string.verifications_phone_confirmation_code_error, 0).show();
    }

    static /* synthetic */ void lambda$new$5(PhoneVerificationFragment phoneVerificationFragment, Object response) {
        phoneVerificationFragment.showLoader(false);
        phoneVerificationFragment.requestState = 0;
    }

    static /* synthetic */ void lambda$new$6(PhoneVerificationFragment phoneVerificationFragment, AirRequestNetworkException e) {
        phoneVerificationFragment.requestState = 0;
        Toast.makeText(phoneVerificationFragment.getActivity(), C0880R.string.verifications_phone_call_error, 0).show();
    }

    private void setUpVerificationsListener() {
        getVerificationsActivity().registerVerificationsListener(PhoneVerificationFragment$$Lambda$8.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpVerificationsListener$7(PhoneVerificationFragment phoneVerificationFragment, VerificationsResponse response) {
        if (response.getVerification(Type.Phone).isCompleted() && !phoneVerificationFragment.displayConfirmationCodeScreen) {
            phoneVerificationFragment.getVerificationsActivity().doneWithVerification(false);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        PhoneVerificationFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
