package com.airbnb.android.registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.utils.SecurityUtil;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;
import com.airbnb.android.registration.requests.AccountCreationRequest;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;
import p032rx.Observer;

public class EmailRegistrationFragment extends BaseRegistrationFragment {
    public static final String EXTRA_EXISTING_ACCOUNT_DATA = "extra_existing_account_data";
    public static final int RESULT_EXISTING_ACCOUNT = 719;
    @State
    String email;
    @BindView
    SheetInputText emailInput;
    final RequestListener<AccountResponse> emailValidationRequestListener = new C0699RL().onResponse(EmailRegistrationFragment$$Lambda$1.lambdaFactory$(this)).onError(EmailRegistrationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;
    @BindView
    SwitchRow promoEmailOptInSwitch;
    @BindView
    View rootView;
    @State
    SheetState sheetState;
    private Snackbar snackbar;
    @BindView
    AirButton swapToPhoneButton;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            if (EmailRegistrationFragment.this.sheetState != SheetState.Normal) {
                EmailRegistrationFragment.this.setSheetState(SheetState.Normal);
            }
            EmailRegistrationFragment.this.emailInput.setState(SheetInputText.State.Normal);
            EmailRegistrationFragment.this.dismissError();
            EmailRegistrationFragment.this.nextButton.setEnabled(EmailRegistrationFragment.this.shouldEnableNextButton());
        }
    };

    static /* synthetic */ void lambda$new$0(EmailRegistrationFragment emailRegistrationFragment, AccountResponse data) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.VERIFY_EMAIL_RESPNOSE, "email", emailRegistrationFragment.getNavigationTrackingTag(), emailRegistrationFragment.getContext());
        if (data.account.isAccountExists()) {
            emailRegistrationFragment.nextButton.setState(AirButton.State.Normal);
            emailRegistrationFragment.emailInput.setState(SheetInputText.State.Error);
            emailRegistrationFragment.nextButton.setEnabled(false);
            emailRegistrationFragment.newHandleWrongAuthMethod(data.account);
            return;
        }
        emailRegistrationFragment.emailInput.setState(SheetInputText.State.Valid);
        emailRegistrationFragment.nextButton.setState(AirButton.State.Success);
        emailRegistrationFragment.handler.postDelayed(EmailRegistrationFragment$$Lambda$6.lambdaFactory$(emailRegistrationFragment), 700);
    }

    static /* synthetic */ void lambda$new$1(EmailRegistrationFragment emailRegistrationFragment, AirRequestNetworkException e) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.VERIFY_EMAIL_RESPNOSE, "email", emailRegistrationFragment.getNavigationTrackingTag(), (NetworkException) e);
        emailRegistrationFragment.emailInput.setState(SheetInputText.State.Normal);
        emailRegistrationFragment.nextButton.setState(AirButton.State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(emailRegistrationFragment.getView(), e);
    }

    private void newHandleWrongAuthMethod(Account account) {
        AccountSource source = AccountSource.findAccountSourceByName(account.getAccountType());
        if (source == null) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Client key and API mismathes. Possibiely due to a recent API change. Please fix ASAP."));
        }
        String firstName = account.getFirstName();
        String profilePicture = account.getPictureUrl();
        if (source == null || !source.isSocialNetwork()) {
            displayError(getString(C1562R.string.registration_email_in_use_desc), getString(C1562R.string.sign_in), EmailRegistrationFragment$$Lambda$3.lambdaFactory$(this));
            return;
        }
        AccountLoginData signinData = AccountLoginData.builder(source).email(this.email).firstName(firstName).profilePicture(profilePicture).build();
        Intent intent = new Intent();
        intent.putExtra(EXTRA_EXISTING_ACCOUNT_DATA, signinData);
        getActivity().setResult(RESULT_EXISTING_ACCOUNT, intent);
        getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_email_registration, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.emailInput.setText(this.dataPassedIn.email());
            this.promoEmailOptInSwitch.setChecked(true);
        }
        setupViews();
        return view;
    }

    public void onResume() {
        super.onResume();
        this.nextButton.setEnabled(shouldEnableNextButton());
    }

    public void onPause() {
        dismissError();
        this.handler.removeCallbacksAndMessages(null);
        super.onPause();
    }

    public void onDestroyView() {
        this.emailInput.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationEmail;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void next() {
        if (hasEnteredValidEmail()) {
            KeyboardUtils.dismissSoftKeyboard((View) this.emailInput);
            this.email = this.emailInput.getText().toString();
            RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.NEXT_BUTTON, this.dataPassedIn.getRegistrationServiceForAnalytics(), getNavigationTrackingTag());
            validateEmail();
            return;
        }
        displayError(getString(C1562R.string.registration_error_email_invalid));
        this.emailInput.setState(SheetInputText.State.Error);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void swapToPhone() {
        RegistrationAnalytics.trackEmailPhoneToggleEvent(getNavigationTrackingTag(), "phone");
        ((AccountIdentifierRegistrationFragment) getParentFragment()).swapToPhone();
    }

    private void validateEmail() {
        this.emailInput.setState(SheetInputText.State.Loading);
        this.nextButton.setState(AirButton.State.Loading);
        AccountCreationRequest.forValidatingEmail(this.email).withListener((Observer) this.emailValidationRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void onEmailValidated() {
        this.registrationController.onStepFinished(AccountRegistrationStep.AccountIdentifier, AccountRegistrationData.builder().email(this.email).accountSource(this.dataPassedIn.isSocialSignUp() ? this.dataPassedIn.accountSource() : AccountSource.Email).promoOptIn(this.promoEmailOptInSwitch.isChecked()).build());
    }

    private void setupViews() {
        ViewUtils.setVisibleIf((View) this.swapToPhoneButton, PhoneUtil.isPNSignupEnabled());
        this.emailInput.addTextChangedListener(this.textWatcher);
        this.emailInput.setOnEditorActionListener(EmailRegistrationFragment$$Lambda$4.lambdaFactory$(this));
        this.emailInput.setAutoCompleteTextView(SecurityUtil.getAccountEmails(getContext()));
        this.promoEmailOptInSwitch.setOnCheckedChangeListener(EmailRegistrationFragment$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupViews$3(EmailRegistrationFragment emailRegistrationFragment, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event)) {
            return false;
        }
        emailRegistrationFragment.next();
        return true;
    }

    private boolean hasEnteredValidEmail() {
        return TextUtil.isValidEmail(this.emailInput.getText());
    }

    /* access modifiers changed from: private */
    public boolean shouldEnableNextButton() {
        return hasEnteredValidEmail() || TextUtil.fieldNotEmptyWithTrimming(this.emailInput);
    }

    /* access modifiers changed from: private */
    public void setSheetState(SheetState sheetState2) {
        this.sheetState = sheetState2;
        this.rootView.setBackgroundColor(ContextCompat.getColor(getContext(), sheetState2.backgroundColor));
    }

    private void displayError(String errorMessage) {
        displayError(errorMessage, null, null);
    }

    private void displayError(String errorMessage, String action, OnClickListener listener) {
        setSheetState(SheetState.Error);
        this.snackbar = new SnackbarWrapper().view(getView()).body(errorMessage).action(action, listener).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void dismissError() {
        setSheetState(SheetState.Normal);
        if (this.snackbar != null) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
    }
}
