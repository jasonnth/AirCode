package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.PasswordUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment */
public class EmailResetPasswordFragment extends BaseLoginFragment {
    private static final String ARG_RESET_PASSWORD_SECRET = "ARG_RESET_PASSWORD_SECRET";
    @BindView
    AirButton createPasswordAndLoginButton;
    @State
    String email = "";
    @State
    AccountLoginData loginData;
    @State
    String newPassword;
    @BindView
    SheetInputText password;
    @BindView
    SheetInputText passwordRetype;
    final RequestListener<ForgotPasswordResponse> resetPasswordListener = new C0699RL().onResponse(EmailResetPasswordFragment$$Lambda$3.lambdaFactory$(this)).onError(EmailResetPasswordFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    String secret;
    final RequestListener<ForgotPasswordResponse> secretVerificationListener = new C0699RL().onResponse(EmailResetPasswordFragment$$Lambda$1.lambdaFactory$(this)).onError(EmailResetPasswordFragment$$Lambda$2.lambdaFactory$(this)).build();
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            String newPassword = EmailResetPasswordFragment.this.password.getText().toString();
            String newPasswordRetype = EmailResetPasswordFragment.this.passwordRetype.getText().toString();
            EmailResetPasswordFragment.this.createPasswordAndLoginButton.setEnabled(!TextUtils.isEmpty(newPassword) && !TextUtils.isEmpty(newPasswordRetype));
            if (!EmailResetPasswordFragment.this.createPasswordAndLoginButton.isEnabled() || !TextUtils.equals(newPassword, newPasswordRetype)) {
                EmailResetPasswordFragment.this.password.setState(SheetInputText.State.Normal);
                EmailResetPasswordFragment.this.passwordRetype.setState(SheetInputText.State.Normal);
                return;
            }
            EmailResetPasswordFragment.this.password.setState(SheetInputText.State.Valid);
            EmailResetPasswordFragment.this.passwordRetype.setState(SheetInputText.State.Valid);
        }
    };

    public static EmailResetPasswordFragment newInstanceWithSecret(String secret2) {
        return (EmailResetPasswordFragment) ((FragmentBundleBuilder) FragmentBundler.make(new EmailResetPasswordFragment()).putString(ARG_RESET_PASSWORD_SECRET, secret2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_email_reset_password, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.secret = getArguments().getString(ARG_RESET_PASSWORD_SECRET);
            initView();
        }
        verifySecret();
        return view;
    }

    /* access modifiers changed from: private */
    public void onResetPasswordSuccess(ForgotPasswordResponse forgotPasswordResponse) {
        if (!forgotPasswordResponse.isSuccess()) {
            updateViewStates(AirButton.State.Normal);
            NetworkUtil.createNetworkErrorSnackbar(getView(), forgotPasswordResponse.getMessage(), C7331R.string.reset_password_error).buildAndShow();
            RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.EMAIL_RESET_PASSWORD_RESET, "email", getNavigationTrackingTag(), Strap.make().mo11639kv("error_message", forgotPasswordResponse.getMessage()).mo11639kv("reset_password_failure_message", forgotPasswordResponse.getMessage()));
            return;
        }
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.EMAIL_RESET_PASSWORD_RESET, "email", getNavigationTrackingTag(), getContext());
        login();
    }

    private void login() {
        Toast.makeText(getContext(), getString(C7331R.string.reset_password_success_and_login), 0).show();
        updateViewStates(AirButton.State.Loading);
        this.loginData = AccountLoginData.builder(AccountSource.Email).email(this.email).password(this.newPassword).build();
        logInWithData(this.loginData);
    }

    /* access modifiers changed from: private */
    public void onSecretVerificationSuccess(ForgotPasswordResponse forgotPasswordResponse) {
        stopLoader();
        this.email = forgotPasswordResponse.getId();
        if (!forgotPasswordResponse.isSuccess()) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(getView(), EmailResetPasswordFragment$$Lambda$5.lambdaFactory$(this));
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Success status is expected on reset password success"));
            return;
        }
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.EMAIL_RESET_PASSWORD_VERIFY_SECRET, "email", getNavigationTrackingTag(), getContext());
    }

    /* access modifiers changed from: private */
    public void onSecretVerificationFailed(NetworkException e) {
        KeyboardUtils.dismissSoftKeyboard(getView());
        stopLoader();
        updateViewStates(AirButton.State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(getView(), e, EmailResetPasswordFragment$$Lambda$6.lambdaFactory$(this));
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.EMAIL_RESET_PASSWORD_VERIFY_SECRET, "email", getNavigationTrackingTag(), e);
    }

    /* access modifiers changed from: private */
    public void onResetPasswordFaild(NetworkException e) {
        stopLoader();
        updateViewStates(AirButton.State.Normal);
        NetworkUtil.createNetworkErrorSnackbar(getView(), e, C7331R.string.reset_password_error).buildAndShow();
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.EMAIL_RESET_PASSWORD_RESET, "email", getNavigationTrackingTag(), e);
    }

    private void verifySecret() {
        if (TextUtils.isEmpty(this.secret)) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("ResetPasswrodFragment is getting an unexpected null secret"));
        }
        startLoader();
        ForgotPasswordRequest.forEmailResetPasswordVeirfySecret(this.secret).withListener((Observer) this.secretVerificationListener).execute(this.requestManager);
    }

    private void initView() {
        this.password.addTextChangedListener(this.textWatcher);
        this.passwordRetype.addTextChangedListener(this.textWatcher);
        this.passwordRetype.setOnEditorActionListener(EmailResetPasswordFragment$$Lambda$7.lambdaFactory$(this));
        this.password.setOnShowPasswordToggleListener(EmailResetPasswordFragment$$Lambda$8.lambdaFactory$(this));
        this.passwordRetype.setOnShowPasswordToggleListener(EmailResetPasswordFragment$$Lambda$9.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$initView$2(EmailResetPasswordFragment emailResetPasswordFragment, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event)) {
            return false;
        }
        emailResetPasswordFragment.resetPasswordAndLoginAttempt();
        return true;
    }

    static /* synthetic */ void lambda$initView$3(EmailResetPasswordFragment emailResetPasswordFragment, boolean showPassword) {
        RegistrationAnalytics.trackClickEvent(showPassword ? RegistrationAnalytics.SHOW_PASSWORD_BUTTON : RegistrationAnalytics.HIDE_PASSWORD_BUTTON, emailResetPasswordFragment.getNavigationTrackingTag());
        emailResetPasswordFragment.passwordRetype.showPassword(showPassword);
    }

    static /* synthetic */ void lambda$initView$4(EmailResetPasswordFragment emailResetPasswordFragment, boolean showPassword) {
        RegistrationAnalytics.trackClickEvent(showPassword ? RegistrationAnalytics.SHOW_PASSWORD_BUTTON : RegistrationAnalytics.HIDE_PASSWORD_BUTTON, emailResetPasswordFragment.getNavigationTrackingTag());
        emailResetPasswordFragment.password.showPassword(showPassword);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void resetPasswordAndLoginAttempt() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        boolean isPasswordValid = isPasswordValidLocalCheck();
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.EMAIL_RESET_PASSWORD_RESET_BUTTON, "email", getNavigationTrackingTag(), Strap.make().mo11640kv("is_password_valid_local", isPasswordValid));
        if (isPasswordValid) {
            updateViewStates(AirButton.State.Loading);
            ForgotPasswordRequest.forEmailResetPassword(this.email, this.secret, this.password.getText().toString(), this.passwordRetype.getText().toString()).withListener((Observer) this.resetPasswordListener).execute(this.requestManager);
        }
    }

    private boolean isPasswordValidLocalCheck() {
        String newPassword2 = this.password.getText().toString();
        String newPasswordRetype = this.passwordRetype.getText().toString();
        String buttonText = getString(C7331R.string.signin_failed_snackbar_button);
        String bodyText = "";
        if (!TextUtils.equals(newPassword2, newPasswordRetype)) {
            bodyText = getString(C7331R.string.reset_password_password_mismatch_error);
        } else if (!PasswordUtils.isValidPassword(newPassword2)) {
            bodyText = PasswordUtils.getInvalidPasswordErrorMessage(getContext(), newPassword2);
        }
        if (!TextUtils.isEmpty(bodyText)) {
            new SnackbarWrapper().view(getView()).title(C7331R.string.reset_password_error, true).body(bodyText).duration(0).action(buttonText, EmailResetPasswordFragment$$Lambda$10.lambdaFactory$(this)).buildAndShow();
            return false;
        }
        this.newPassword = newPassword2;
        return true;
    }

    static /* synthetic */ void lambda$isPasswordValidLocalCheck$5(EmailResetPasswordFragment emailResetPasswordFragment, View v) {
        emailResetPasswordFragment.password.showPassword(true);
        emailResetPasswordFragment.passwordRetype.showPassword(true);
    }

    public void onDestroyView() {
        this.password.removeTextChangedListener(this.textWatcher);
        this.passwordRetype.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public void onLogInSuccess(Login login) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.EMAIL_RESET_PASSWORD_LOGIN, "email", getNavigationTrackingTag(), getContext());
        updateViewStates(AirButton.State.Success);
    }

    public void onLogInError(NetworkException exception) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.EMAIL_RESET_PASSWORD_LOGIN, "email", getNavigationTrackingTag(), exception);
        updateViewStates(AirButton.State.Normal);
        NetworkUtil.createNetworkErrorSnackbar(getView(), exception, C7331R.string.reset_password_error).buildAndShow();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.EmailResetPassword;
    }

    private void updateViewStates(AirButton.State state) {
        boolean z;
        boolean z2 = true;
        this.createPasswordAndLoginButton.setState(state);
        SheetInputText sheetInputText = this.password;
        if (state != AirButton.State.Loading) {
            z = true;
        } else {
            z = false;
        }
        sheetInputText.setEnabled(z);
        SheetInputText sheetInputText2 = this.passwordRetype;
        if (state == AirButton.State.Loading) {
            z2 = false;
        }
        sheetInputText2.setEnabled(z2);
    }
}
