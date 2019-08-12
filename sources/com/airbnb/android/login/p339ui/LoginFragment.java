package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment;
import com.airbnb.android.core.fragments.CountryCodeSelectionFragment.CountrySelectedListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.PhoneUtil;
import com.airbnb.android.core.utils.SecurityUtil;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.core.views.PhoneNumberInputSheet.PhoneNumberInputViewDelegate;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.airbnb.p027n2.utils.TextUtil;
import icepick.State;
import java.util.ArrayList;
import java.util.HashSet;

/* renamed from: com.airbnb.android.login.ui.LoginFragment */
public class LoginFragment extends BaseLoginFragment implements CountrySelectedListener, PhoneNumberInputViewDelegate {
    private static final String ARG_LOGIN_DATA = "arg_login_data";
    private static final String ERROR_TYPE_INVALID_PASSWORD = "invalid_credentials";
    @State
    AirPhone airPhone;
    @State
    CountryCodeItem countryCodeItem;
    private final OnClickListener countryCodeSelectionButtonClickListener = LoginFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    SheetInputText editEmail;
    @BindView
    SheetInputText editPassword;
    @BindView
    PhoneNumberInputSheet editPhone;
    @BindView
    AirButton loginButton;
    @State
    AccountLoginData loginData;
    @State
    LoginMode loginMode;
    @BindView
    AirButton swapModeButton;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            LoginFragment.this.loginButton.setEnabled(LoginFragment.this.hasEnteredValidInfo());
        }
    };

    /* renamed from: com.airbnb.android.login.ui.LoginFragment$LoginMode */
    public enum LoginMode {
        Email(C7331R.string.switch_to_use_phone_number, "email"),
        Phone(C7331R.string.switch_to_use_email, "phone");
        
        public final String serviceNameForAnalytics;
        public final int swapModeBabyButtonStringRes;

        private LoginMode(int swapModeBabyButtonStringRes2, String serviceNameForAnalytics2) {
            this.swapModeBabyButtonStringRes = swapModeBabyButtonStringRes2;
            this.serviceNameForAnalytics = serviceNameForAnalytics2;
        }

        public static LoginMode stringToLoginMode(String loginMode) {
            try {
                return valueOf(loginMode);
            } catch (IllegalArgumentException e) {
                return Email;
            }
        }
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    public static LoginFragment newInstance(AccountLoginData data) {
        Bundle args = new Bundle();
        LoginFragment fragment = newInstance();
        args.putParcelable(ARG_LOGIN_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    static /* synthetic */ void lambda$new$0(LoginFragment loginFragment, View v) {
        CountryCodeSelectionFragment fragment = CountryCodeSelectionFragment.newInstance();
        fragment.setOnCountryCodeSelectedListener(loginFragment);
        loginFragment.getAirActivity().showFragment(fragment, ((ViewGroup) loginFragment.getView().getParent()).getId(), FragmentTransitionType.FadeInAndOut, true, fragment.getClass().getSimpleName());
    }

    private void showAppropriateSnackbar(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        updateViewStates(AirButton.State.Normal);
        if (errorResponse == null || !"invalid_credentials".equals(errorResponse.errorType)) {
            NetworkUtil.tryShowErrorWithSnackbar(getView(), e, C7331R.string.sign_in_error, C7331R.string.default_sign_in_error);
        } else {
            showIncorrectPasswordSnackBar(getView());
        }
    }

    private void showIncorrectPasswordSnackBar(View view) {
        updateViewStates(AirButton.State.Normal);
        String titleText = getString(C7331R.string.incorrect_login_credentials_snackbar_title);
        new SnackbarWrapper().view(view).title(titleText, true).duration(0).action(getString(C7331R.string.signin_failed_snackbar_button), LoginFragment$$Lambda$2.lambdaFactory$(this)).buildAndShow();
    }

    public void autoPopulateEmailInputSheet() {
        this.editEmail.setAutoCompleteTextView(new ArrayList(new HashSet(this.mPrefsHelper.getListFromGlobalSharedPrefs(AirbnbConstants.PREFS_PREVIOUS_ACCOUNT_EMAILS))));
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_login, container, false);
        bindViews(view);
        setupViews();
        setupSwapModeButton();
        if (savedInstanceState == null) {
            bindPassedInData();
            initModeIfNecessary();
        }
        updateLoginMode();
        updateViewsByMode();
        guardPNR();
        autoPopulateEmailInputSheet();
        return view;
    }

    private void updateLoginMode() {
        String lastUsedLoginType = this.mPreferences.getGlobalSharedPreferences().getString(AirbnbConstants.PREF_REMEMBER_EMAIL_PHONE_LOGIN, null);
        if (lastUsedLoginType != null) {
            this.loginMode = LoginMode.stringToLoginMode(lastUsedLoginType);
        }
    }

    private void guardPNR() {
        if (!PhoneUtil.isPNLoginEnabled()) {
            this.loginMode = LoginMode.Email;
            updateViewsByMode();
        }
    }

    private boolean shouldShowSwapModeButton() {
        return PhoneUtil.isPNLoginEnabled();
    }

    private void setupSwapModeButton() {
        ViewUtils.setVisibleIf((View) this.swapModeButton, shouldShowSwapModeButton());
    }

    private void bindPassedInData() {
        if (getArguments() != null) {
            AccountLoginData loginData2 = (AccountLoginData) getArguments().getParcelable(ARG_LOGIN_DATA);
            Check.state(!loginData2.accountSource().isSocialNetwork(), "Can only support non-social login");
            if (loginData2.accountSource() == AccountSource.Phone) {
                this.loginMode = LoginMode.Phone;
                this.airPhone = loginData2.airPhone();
                this.countryCodeItem = this.airPhone.countryCodeItem();
                this.editPhone.setPhoneNumberEditText((CharSequence) this.airPhone.phoneInputText());
                this.editPhone.onNewCountryCodeSelected(this.countryCodeItem);
            } else {
                this.loginMode = LoginMode.Email;
                this.editEmail.setText(loginData2.email());
            }
            this.editPassword.requestFocus();
        }
    }

    private void initModeIfNecessary() {
        if (this.loginMode != null) {
            return;
        }
        if (ChinaUtils.isUserInChinaOrPrefersChineseLanguage()) {
            String lastUsedLogin = this.mPreferences.getGlobalSharedPreferences().getString(AirbnbConstants.PREF_REMEMBER_EMAIL_PHONE_LOGIN, null);
            this.loginMode = lastUsedLogin == null ? LoginMode.Phone : LoginMode.stringToLoginMode(lastUsedLogin);
            return;
        }
        this.loginMode = LoginMode.Email;
    }

    private void updateViewsByMode() {
        boolean z;
        boolean z2 = true;
        this.swapModeButton.setText(this.loginMode.swapModeBabyButtonStringRes);
        SheetInputText sheetInputText = this.editEmail;
        if (this.loginMode == LoginMode.Phone) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setGoneIf(sheetInputText, z);
        PhoneNumberInputSheet phoneNumberInputSheet = this.editPhone;
        if (this.loginMode != LoginMode.Email) {
            z2 = false;
        }
        ViewUtils.setGoneIf(phoneNumberInputSheet, z2);
        this.loginButton.setEnabled(hasEnteredValidInfo());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSwapModeButtonClick() {
        if (this.loginMode == LoginMode.Phone) {
            this.loginMode = LoginMode.Email;
        } else {
            this.loginMode = LoginMode.Phone;
        }
        saveLoginMode(this.loginMode);
        RegistrationAnalytics.trackEmailPhoneToggleEvent(getNavigationTrackingTag(), this.loginMode.serviceNameForAnalytics);
        updateViewsByMode();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7331R.C7334menu.menu_forgot_password, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7331R.C7333id.menu_forgot_password) {
            return super.onOptionsItemSelected(item);
        }
        if (this.loginMode == LoginMode.Email) {
            startActivity(EmailForgotPasswordFragment.newIntent(getContext(), this.editEmail.getText().toString()));
        } else {
            this.airPhone = AirPhone.withCountryCodeItem(this.airPhone, this.countryCodeItem);
            startActivity(PhoneForgotPasswordFragment.newIntent(getContext(), this.airPhone));
        }
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.FORGOT_PASSWORD_BUTTON, this.loginMode.serviceNameForAnalytics, getNavigationTrackingTag());
        return true;
    }

    public void onDestroyView() {
        this.editEmail.removeTextChangedListener(this.textWatcher);
        this.editPassword.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    private void setupViews() {
        this.editEmail.addTextChangedListener(this.textWatcher);
        this.editEmail.setAutoCompleteTextView(SecurityUtil.getAccountEmails(getContext()));
        this.editPassword.addTextChangedListener(this.textWatcher);
        this.editPassword.setOnShowPasswordToggleListener(LoginFragment$$Lambda$3.lambdaFactory$(this));
        this.editPassword.setOnEditorActionListener(LoginFragment$$Lambda$4.lambdaFactory$(this));
        this.editPhone.initPhoneNumberInputView(this);
        this.editPhone.setCountryCodeSelectionButtonClickListener(this.countryCodeSelectionButtonClickListener);
        this.editPhone.onNewCountryCodeSelected(this.countryCodeItem);
    }

    static /* synthetic */ void lambda$setupViews$2(LoginFragment loginFragment, boolean showPassword) {
        RegistrationAnalytics.trackClickEvent(showPassword ? RegistrationAnalytics.SHOW_PASSWORD_BUTTON : RegistrationAnalytics.HIDE_PASSWORD_BUTTON, loginFragment.getNavigationTrackingTag());
    }

    static /* synthetic */ boolean lambda$setupViews$3(LoginFragment loginFragment, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event) || !loginFragment.hasEnteredValidInfo()) {
            return false;
        }
        loginFragment.login();
        return true;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void login() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.LOG_IN_REQUEST_BUTTON, this.loginMode.serviceNameForAnalytics, getNavigationTrackingTag());
        KeyboardUtils.dismissSoftKeyboard(getView());
        updateViewStates(AirButton.State.Loading);
        if (this.loginMode == LoginMode.Phone) {
            this.loginData = AccountLoginData.builder(AccountSource.Phone).airPhone(this.airPhone).password(this.editPassword.getText().toString()).build();
        } else {
            this.loginData = AccountLoginData.builder(AccountSource.Email).email(this.editEmail.getText().toString()).password(this.editPassword.getText().toString()).build();
        }
        logInWithData(this.loginData);
        saveLoginMode(this.loginMode);
    }

    private void saveLoginMode(LoginMode loginMode2) {
        this.mPreferences.getGlobalSharedPreferences().edit().putString(AirbnbConstants.PREF_REMEMBER_EMAIL_PHONE_LOGIN, loginMode2.toString()).apply();
    }

    /* access modifiers changed from: private */
    public boolean hasEnteredValidInfo() {
        boolean valid;
        if (this.loginMode == LoginMode.Email) {
            valid = TextUtil.isValidEmail(this.editEmail.getText().toString());
        } else {
            valid = this.editPhone.isPhoneNumberValid();
        }
        return valid && TextUtil.textNotEmptyWithTriming(this.editPassword.getText());
    }

    public void afterPhoneNumberTextChanged(AirPhone airPhone2) {
        if (this.loginMode == LoginMode.Phone) {
            this.loginButton.setEnabled(hasEnteredValidInfo());
        }
        this.airPhone = airPhone2;
    }

    public void afterPhoneNumberTextChanged(String formattedPhoneNumber, String displayPhoneNumber) {
    }

    public void onCountrySelected(CountryCodeItem item) {
        this.countryCodeItem = item;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationLogin;
    }

    private void updateViewStates(AirButton.State state) {
        boolean z;
        boolean z2 = true;
        this.loginButton.setState(state);
        SheetInputText sheetInputText = this.editEmail;
        if (state != AirButton.State.Loading) {
            z = true;
        } else {
            z = false;
        }
        sheetInputText.setEnabled(z);
        PhoneNumberInputSheet phoneNumberInputSheet = this.editPhone;
        if (state == AirButton.State.Loading) {
            z2 = false;
        }
        phoneNumberInputSheet.setEnabled(z2);
    }

    public void onLogInSuccess(Login login) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.LOG_IN_RESPONSE, this.loginMode.serviceNameForAnalytics, getNavigationTrackingTag(), getContext());
        updateViewStates(AirButton.State.Success);
    }

    public void onLogInError(NetworkException exception) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.LOG_IN_RESPONSE, this.loginMode.serviceNameForAnalytics, getNavigationTrackingTag(), exception);
        showAppropriateSnackbar(exception);
    }
}
