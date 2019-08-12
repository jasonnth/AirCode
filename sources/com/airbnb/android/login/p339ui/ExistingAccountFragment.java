package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.p339ui.views.OAuthOptionButton;
import com.airbnb.android.login.requests.ForgotPasswordRequest;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment */
public class ExistingAccountFragment extends BaseLoginFragment {
    private static final String ARG_EXISTING_ACCOUNT_DATA = "ARG_EXISTING_ACCOUNT_DATA";
    @BindView
    AirButton createPasswordButton;
    @BindView
    SheetInputText editPassword;
    @BindView
    AirTextView emailTextView;
    @State
    AccountLoginData existingAccountData;
    @State
    ExistingAccountType existingAccountType;
    @BindView
    AirTextView firstNameTextView;
    final RequestListener<ForgotPasswordResponse> forgotPasswordListener = new C0699RL().onResponse(ExistingAccountFragment$$Lambda$1.lambdaFactory$(this)).onError(ExistingAccountFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirButton loginButton;
    @BindView
    OAuthOptionButton oauthOptionButton;
    @BindView
    SheetMarquee sheetMarquee;
    private final TextWatcher textWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            ExistingAccountFragment.this.loginButton.setEnabled(!ExistingAccountFragment.this.editPassword.getText().toString().isEmpty());
        }
    };
    @BindView
    HaloImageView userProfileImageView;

    /* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$ExistingAccountType */
    public enum ExistingAccountType {
        EXISTING_EMAIL_ACCOUNT,
        EXISTING_SOCIAL_ACCOUNT
    }

    public static ExistingAccountFragment newInstanceWithExistingAccountData(AccountLoginData existingAccountData2) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_EXISTING_ACCOUNT_DATA, existingAccountData2);
        ExistingAccountFragment fragment = new ExistingAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_existing_account, container, false);
        bindViews(view);
        startLoader();
        this.existingAccountData = (AccountLoginData) getArguments().getParcelable(ARG_EXISTING_ACCOUNT_DATA);
        if (this.existingAccountData == null || TextUtils.isEmpty(this.existingAccountData.email()) || TextUtils.isEmpty(this.existingAccountData.firstName()) || TextUtils.isEmpty(this.existingAccountData.profilePicture())) {
            getAirActivity().onBackPressed();
        } else {
            bindData();
            setupViewByExistingAccountType();
        }
        return view;
    }

    private void bindData() {
        if (TextUtils.isEmpty(this.existingAccountData.email()) || TextUtils.isEmpty(this.existingAccountData.firstName())) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Email or First name is missing from ExistingAccountFragment"));
        }
        if (this.existingAccountData.accountSource().isSocialNetwork()) {
            this.existingAccountType = ExistingAccountType.EXISTING_SOCIAL_ACCOUNT;
        } else {
            this.existingAccountType = ExistingAccountType.EXISTING_EMAIL_ACCOUNT;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7331R.C7334menu.menu_forgot_password, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7331R.C7333id.menu_forgot_password) {
            return super.onOptionsItemSelected(item);
        }
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.FORGOT_PASSWORD_REQUEST_EMAIL_BUTTON_WRONG_AUTH, this.existingAccountData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag());
        startLoader();
        ForgotPasswordRequest.forEmail(this.existingAccountData.email()).withListener((Observer) this.forgotPasswordListener).execute(this.requestManager);
        return true;
    }

    /* access modifiers changed from: private */
    public void onForgotPasswordResponse(ForgotPasswordResponse data) {
        stopLoader();
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.FORGOT_PASSWORD_EMAIL_RESPONSE, "email", getNavigationTrackingTag(), getContext());
        int textTemplateId = this.existingAccountType == ExistingAccountType.EXISTING_SOCIAL_ACCOUNT ? C7331R.string.create_password_successful : C7331R.string.reset_password_successful;
        if (data.isSuccess()) {
            new SnackbarWrapper().view(getView()).body(getString(textTemplateId, this.existingAccountData.email())).duration(0).buildAndShow();
        } else {
            NetworkUtil.toastNetworkError(getContext(), data.getMessage());
        }
    }

    private void setupViewByExistingAccountType() {
        this.userProfileImageView.setImageUrl(this.existingAccountData.profilePicture());
        this.firstNameTextView.setText(this.existingAccountData.firstName());
        this.emailTextView.setText(this.existingAccountData.email());
        if (this.existingAccountType == ExistingAccountType.EXISTING_SOCIAL_ACCOUNT) {
            setupViewForExistingSocialAccount();
        } else {
            setupViewForExistingEmailAccount();
        }
        stopLoader();
    }

    private void setupViewForExistingSocialAccount() {
        OAuthOption oauthOption = OAuthOption.stringToOAuthOption(this.existingAccountData.accountSource().getName());
        this.sheetMarquee.setSubtitle(getString(C7331R.string.registration_account_exist_subtitle, getString(oauthOption.title)));
        this.oauthOptionButton.setVisibility(0);
        this.oauthOptionButton.setOption(oauthOption);
        this.oauthOptionButton.setOnClickListener(ExistingAccountFragment$$Lambda$3.lambdaFactory$(this, oauthOption));
        this.createPasswordButton.setVisibility(0);
        this.createPasswordButton.setOnClickListener(ExistingAccountFragment$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupViewForExistingSocialAccount$1(ExistingAccountFragment existingAccountFragment, View v) {
        existingAccountFragment.startLoader();
        ForgotPasswordRequest.forEmail(existingAccountFragment.existingAccountData.email()).withListener((Observer) existingAccountFragment.forgotPasswordListener).execute(existingAccountFragment.requestManager);
    }

    private void setupViewForExistingEmailAccount() {
        setHasOptionsMenu(true);
        this.sheetMarquee.setSubtitle(getString(C7331R.string.registration_account_exist_subtitle, this.existingAccountData.accountSource().getName()));
        this.editPassword.setVisibility(0);
        this.loginButton.setVisibility(0);
        this.editPassword.addTextChangedListener(this.textWatcher);
        this.editPassword.setOnEditorActionListener(ExistingAccountFragment$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupViewForExistingEmailAccount$2(ExistingAccountFragment existingAccountFragment, TextView v, int actionId, KeyEvent event) {
        if (!KeyboardUtils.isEnterOrDone(actionId, event)) {
            return false;
        }
        existingAccountFragment.logIn();
        return true;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void logIn() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        updateViewStates(AirButton.State.Loading);
        AccountLoginData loginData = AccountLoginData.builder(AccountSource.Email).email(this.existingAccountData.email()).password(this.editPassword.getText().toString()).build();
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.LOG_IN_REQUEST_BUTTON, loginData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag());
        logInWithData(loginData);
    }

    private void showAppropriateSnackbar(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        if (errorResponse == null || !LoginActivity.INVALID_CREDENTIALS_ERROR.equals(errorResponse.errorType)) {
            NetworkUtil.tryShowErrorWithSnackbar(getView(), e, C7331R.string.sign_in_error, C7331R.string.default_sign_in_error);
        } else {
            new SnackbarWrapper().view(getView()).title(C7331R.string.incorrect_login_credentials_snackbar_title, true).duration(0).action(C7331R.string.signin_failed_snackbar_button, ExistingAccountFragment$$Lambda$6.lambdaFactory$(this)).buildAndShow();
        }
    }

    private void updateViewStates(AirButton.State state) {
        this.loginButton.setState(state);
        this.editPassword.setEnabled(state != AirButton.State.Loading);
    }

    /* access modifiers changed from: private */
    public void onNetworkError(NetworkException e) {
        stopLoader();
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    public void onDestroyView() {
        this.editPassword.removeTextChangedListener(this.textWatcher);
        super.onDestroyView();
    }

    public void onLogInSuccess(Login login) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.LOG_IN_RESPONSE, this.existingAccountData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag(), getContext());
        updateViewStates(AirButton.State.Success);
    }

    public void onLogInError(NetworkException exception) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.LOG_IN_RESPONSE, this.existingAccountData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag(), exception);
        updateViewStates(AirButton.State.Normal);
        showAppropriateSnackbar(exception);
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.existingAccountType == ExistingAccountType.EXISTING_EMAIL_ACCOUNT ? NavigationTag.EmailAccountExist : NavigationTag.SocialAccountExist;
    }
}
