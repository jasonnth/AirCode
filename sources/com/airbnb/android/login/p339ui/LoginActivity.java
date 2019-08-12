package com.airbnb.android.login.p339ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.events.ExperimentConfigFetchCompleteEvent;
import com.airbnb.android.core.events.ResetPasswordEntryEvent;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.GiftCard;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.LoginGraph;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.oauth.OAuthStrategyListener;
import com.airbnb.android.login.p339ui.BaseLoginFragment.LoginFragmentListener;
import com.airbnb.android.login.p339ui.LoginLandingFragment.LoginLandingFragmentListener;
import com.airbnb.android.login.p339ui.MobileWebLandingFragment.MobileWebLandingFragmentListener;
import com.airbnb.android.login.requests.UserLoginRequest;
import com.airbnb.android.login.responses.UserLoginResponse;
import com.airbnb.android.login.smartlock.GoogleSmartLockAnalytics;
import com.airbnb.android.login.smartlock.GoogleSmartLockController;
import com.airbnb.android.login.smartlock.GoogleSmartLockController.Factory;
import com.airbnb.android.login.smartlock.GoogleSmartLockController.GoogleSmartLockCredentialListener;
import com.airbnb.android.referrals.rolodex.ContactUploadActivity;
import com.airbnb.android.registration.AccountRegistrationActivity;
import com.airbnb.android.registration.CreateSocialAccountFragment;
import com.airbnb.android.registration.EmailRegistrationFragment;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.requests.AccountCreationRequest;
import com.airbnb.android.registration.requests.CreateSocialSignupRequest;
import com.airbnb.android.registration.responses.EmptySocialSignupResponse;
import com.airbnb.android.registration.responses.SocialSignupResponse;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.android.gms.auth.api.credentials.Credential;
import com.squareup.otto.Subscribe;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.LoginActivity */
public class LoginActivity extends AirActivity implements OAuthStrategyListener, GoogleSmartLockCredentialListener, LoginFragmentListener, LoginLandingFragmentListener, MobileWebLandingFragmentListener {
    public static final String EMAIL_ALREADY_IN_USE_ERROR = "email_already_in_use";
    public static final String INVALID_CREDENTIALS_ERROR = "invalid_credentials";
    private static final int RC_CREATE_ACCOUNT = 101;
    private static final int RC_CREATE_SOCIAL_ACCOUNT = 104;
    private static final int RC_MORE_OPTIONS = 107;
    public static final String SOCIAL_ACCOUNT_EXIST_ERROR = "social_account_already_exist";
    @BindView
    ViewGroup contentContainer;
    @State
    Credential credentialFromSmartLock;
    private BaseLoginFragment currentLoginFragment;
    ExperimentConfigController experimentConfigController;
    final RequestListener<AccountResponse> fetchExistingAccountUsingEmailListener = new C0699RL().onResponse(LoginActivity$$Lambda$3.lambdaFactory$(this)).onError(LoginActivity$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<SocialSignupResponse> fetchExistingAccountUsingOAuthListener = new C0699RL().onResponse(LoginActivity$$Lambda$1.lambdaFactory$(this)).onError(LoginActivity$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<SocialSignupResponse> fetchSocialAccountInfoListener = new C0699RL().onResponse(LoginActivity$$Lambda$5.lambdaFactory$(this)).onError(LoginActivity$$Lambda$6.lambdaFactory$(this)).build();
    private GoogleSmartLockController googleSmartLockController;
    @State
    boolean isSignUp;
    @BindView
    LoaderFrame loaderFrame;
    @State
    Login login;
    @State
    AccountLoginData loginData;
    final RequestListener<UserLoginResponse> loginRequestListener = new C0699RL().onResponse(LoginActivity$$Lambda$7.lambdaFactory$(this)).onError(LoginActivity$$Lambda$8.lambdaFactory$(this)).build();
    private OAuthLoginManager oauthLoginManager;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$1(LoginActivity loginActivity, AccountResponse data) {
        if (data.account.isAccountExists()) {
            loginActivity.onExistingEmailAccountRetreived(data.account);
        }
    }

    static /* synthetic */ void lambda$new$2(LoginActivity loginActivity, SocialSignupResponse data) {
        if (data.hasAccountData()) {
            loginActivity.startCreateAccountForSocialSignupResponse(data);
        } else {
            loginActivity.startRegistrationFlowWithEmptySocialInfo();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.resourceManager.fetchResourceIfLanguageChanged();
        this.bus.register(this);
        this.oauthLoginManager = OAuthLoginManager.getInstance();
        this.googleSmartLockController = Factory.create(this, this, savedInstanceState);
        setContentView(C7331R.layout.activity_login);
        ((LoginGraph) CoreApplication.instance(this).component()).inject(this);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            setUpBackNavigation();
            showInitialFragment();
        }
        displayToastIfNeeded();
    }

    private void setUpBackNavigation() {
        getSupportFragmentManager().addOnBackStackChangedListener(LoginActivity$$Lambda$9.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setUpBackNavigation$3(LoginActivity loginActivity) {
        Fragment modal = loginActivity.getSupportFragmentManager().findFragmentById(C7331R.C7333id.modal_container);
        if (loginActivity.getSupportFragmentManager().getBackStackEntryCount() == 0 || (modal != null && modal.isVisible())) {
            loginActivity.toolbar.setNavigationIcon(2);
        } else {
            loginActivity.toolbar.setNavigationIcon(1);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.bus.unregister(this);
        super.onDestroy();
    }

    private void showInitialFragment() {
        SharedPreferences sharedPreferences = this.preferences.getSharedPreferences();
        boolean enabled = sharedPreferences.getBoolean("login", false);
        String moWebToken = sharedPreferences.getString(AirbnbConstants.PREFS_MOBILE_WEB_TOKEN, null);
        String id = sharedPreferences.getString(AirbnbConstants.PREFS_MOBILE_WEB_ID, null);
        String name = sharedPreferences.getString(AirbnbConstants.PREFS_MOBILE_WEB_NAME, null);
        if (enabled && !TextUtils.isEmpty(moWebToken) && !TextUtils.isEmpty(id) && !TextUtils.isEmpty(name)) {
            showFragment(MobileWebLandingFragment.newInstance(moWebToken, id, name), false);
            sharedPreferences.edit().putBoolean("login", false).apply();
        } else if (isResetPasswordFlow()) {
            showFragment(EmailResetPasswordFragment.newInstanceWithSecret(getIntent().getStringExtra("secret")), false);
        } else {
            showFragment(LoginLandingFragment.newInstance(this.preferences.getGlobalSharedPreferences().getString(AirbnbConstants.PREF_REMEMBER_SOCIAL_LOGIN, null)), false);
            this.googleSmartLockController.requestCredential();
        }
    }

    private void displayToastIfNeeded() {
        Intent intent = getIntent();
        if (intent != null) {
            String toastMessage = intent.getStringExtra(LoginActivityIntents.EXTRA_TOAST_MSG);
            if (!TextUtils.isEmpty(toastMessage)) {
                Toast.makeText(this, toastMessage, 0).show();
            }
        }
    }

    public void onLogInWithData(AccountLoginData loginData2) {
        this.loginData = loginData2;
        startLoader();
        UserLoginRequest.newRequest(this, loginData2).withListener((Observer) this.loginRequestListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void logInRequestSuccess(UserLoginResponse userLoginResponse) {
        this.login = userLoginResponse.login;
        this.experimentConfigController.fetchConfigurationForUser(this.accountManager.getCurrentUserId());
    }

    /* access modifiers changed from: private */
    public void logInRequestError(NetworkException e) {
        if (this.credentialFromSmartLock != null) {
            this.googleSmartLockController.deleteInvalidCredential(this.credentialFromSmartLock);
        }
        if (this.loginData.accountSource().isSocialNetwork()) {
            if (isNonExistingSocialLogin(e)) {
                if (this.loginData.accountSource() == AccountSource.WeChat || this.loginData.accountSource() == AccountSource.Alipay) {
                    startRegistrationFlowWithEmptySocialInfo();
                    return;
                } else {
                    CreateSocialSignupRequest.forFetchingSocialAccountInfo(this.loginData).withListener((Observer) this.fetchSocialAccountInfoListener).execute(this.requestManager);
                    return;
                }
            } else if (isEmailExistingError(e) && ((this.loginData.accountSource() == AccountSource.Facebook || this.loginData.accountSource() == AccountSource.Google) && Experiments.showFacebookGoogleWrongAuthLoginSheet())) {
                handleExistingAccountError();
                return;
            }
        }
        if (isSocialAccountExistingError(e)) {
            handleExistingAccountError();
        }
        stopLoader();
        if (this.currentLoginFragment != null) {
            this.currentLoginFragment.onLogInError(e);
            return;
        }
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.LOG_IN_RESPONSE, this.loginData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag(), e);
        NetworkUtil.tryShowErrorWithSnackbar(this.contentContainer, e, C7331R.string.sign_in_error, C7331R.string.default_sign_in_error);
    }

    @Subscribe
    public void onExperimentConfigFetchComplete(ExperimentConfigFetchCompleteEvent event) {
        if (this.login != null) {
            this.googleSmartLockController.saveCredential(this.accountManager.getCurrentUser(), this.loginData);
            if (this.currentLoginFragment != null) {
                this.currentLoginFragment.onLogInSuccess(this.login);
            } else {
                RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.LOG_IN_RESPONSE, this.loginData.accountSource().getServiceNameForAnalytics(), getNavigationTrackingTag(), this);
            }
            completeLoginAndLaunchAfterLoginTasks();
        }
    }

    public void completeLoginAndLaunchAfterLoginTasks() {
        CommunityCommitmentManager.launchCommunityCommitmentIfNeeded(this.isSignUp && isCommunityCommitmentRequired(this.login.getAccount()), TargetUserType.NewUser, this);
        GiftCard giftCard = (GiftCard) getIntent().getParcelableExtra("web_link_gift_card");
        if (giftCard != null) {
            startActivity(ReactNativeIntents.intentForGiftCardsRedemptionApp(this, giftCard.code(), giftCard.verificationToken()));
        }
        if (Experiments.shouldShowContactUploadRequest()) {
            ContactUploadActivity.maybeDoContactUpload(this);
        }
        setResult(-1);
        finish();
        if (isResetPasswordFlow()) {
            startActivity(HomeActivityIntents.intentForDefaultTab(this));
        }
    }

    private boolean isCommunityCommitmentRequired(Account account) {
        return this.isSignUp && account.acceptCommunityCommitmentRequired();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.googleSmartLockController.onActivityResult(requestCode, resultCode, data);
        this.oauthLoginManager.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101:
                if (resultCode == -1) {
                    onReturnFromRegistration(data);
                    return;
                } else if (resultCode == 719 && data != null) {
                    showFragment(ExistingAccountFragment.newInstanceWithExistingAccountData((AccountLoginData) data.getParcelableExtra(EmailRegistrationFragment.EXTRA_EXISTING_ACCOUNT_DATA)), true);
                    return;
                } else {
                    return;
                }
            case 104:
                if (resultCode == -1) {
                    onReturnFromSocialSignup(data);
                    return;
                } else {
                    stopLoader();
                    return;
                }
            case 107:
                if (resultCode == -1) {
                    onMoreOptionsSelected(data);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void onThirdPartyTokenAcquired(OAuthOption option, String token) {
        if (!TextUtils.isEmpty(token)) {
            onLogInWithData(AccountLoginData.builder(AccountSource.findAccountSourceByName(option.name())).authToken(token).build());
        }
    }

    public void switchAccount() {
        showFragment(LoginLandingFragment.newInstance(), true);
    }

    public void onMoreOptions() {
        startActivityForResult(MoreOptionsActivity.newIntent(this), 107);
    }

    public void onLogInWithEmail() {
        showFragment(LoginFragment.newInstance(), true);
    }

    public void onSignUp() {
        startActivityForResult(AccountRegistrationActivity.newIntent(this), 101);
    }

    public void onLoginWithPrimaryOption(OAuthOption option) {
        onLogInWithOAuthOption(option);
    }

    public void startLoader() {
        if (!this.loaderFrame.isAnimating()) {
            this.loaderFrame.startAnimation();
        }
    }

    public void stopLoader() {
        this.loaderFrame.finishImmediate();
    }

    public void onLogInWithOAuthOption(OAuthOption option) {
        startLoader();
        this.oauthLoginManager.loginWith(option, this, this);
        saveSocialLoginMethod(option);
    }

    @Subscribe
    public void onResetPasswordWebIntentRecevied(ResetPasswordEntryEvent event) {
        if (TextUtils.isEmpty(getIntent().getStringExtra("secret"))) {
            finish();
        }
    }

    private void saveSocialLoginMethod(OAuthOption option) {
        this.preferences.getGlobalSharedPreferences().edit().putString(AirbnbConstants.PREF_REMEMBER_SOCIAL_LOGIN, option.toString()).apply();
    }

    public void setCurrentLoginFragment(BaseLoginFragment currentLoginFragment2) {
        this.currentLoginFragment = currentLoginFragment2;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C7331R.anim.activity_transition_back_to_prev, C7331R.anim.activity_transition_slide_out_new);
    }

    public void showFragment(Fragment fragment, boolean addToBackstack) {
        showFragment(fragment, C7331R.C7333id.content_container, FragmentTransitionType.SlideInFromSide, addToBackstack);
    }

    public void onHomeActionPressed() {
        if (this.googleSmartLockController.isRequestingCredential()) {
            stopLoader();
            GoogleSmartLockAnalytics.trackRequestCredentialCancel();
            this.googleSmartLockController.ignoreCredentialResponse();
            return;
        }
        super.onHomeActionPressed();
    }

    private boolean isNonExistingSocialLogin(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        return errorResponse != null && TextUtils.equals(INVALID_CREDENTIALS_ERROR, errorResponse.errorType);
    }

    private boolean isEmailExistingError(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        return errorResponse != null && TextUtils.equals(EMAIL_ALREADY_IN_USE_ERROR, errorResponse.errorType);
    }

    private boolean isSocialAccountExistingError(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        return errorResponse != null && TextUtils.equals(SOCIAL_ACCOUNT_EXIST_ERROR, errorResponse.errorType);
    }

    private void startCreateAccountForSocialSignupResponse(SocialSignupResponse response) {
        startActivityForResult(CreateSocialAccountFragment.newIntentWithPrefilledData(this, AccountRegistrationData.forConfirmSocialSignup(this.loginData.accountSource(), this.loginData.authToken(), response), response.isEmailReadOnly()), 104);
    }

    private void startRegistrationFlowWithEmptySocialInfo() {
        startCreateAccountForSocialSignupResponse(new EmptySocialSignupResponse());
    }

    public void onCredentialRetrievalError() {
        stopLoader();
    }

    public void onCredentialRetrievalCanceled() {
        stopLoader();
    }

    public void onCredentialRetrievalSuccess(Credential credential) {
        this.credentialFromSmartLock = credential;
        String accountType = credential.getAccountType();
        if (accountType == null) {
            if (TextUtils.isEmpty(credential.getPassword())) {
                BugsnagWrapper.throwOrNotify(new RuntimeException("Google Smartlock try to signin with email without a valid password."));
                Toast.makeText(this, getString(C7331R.string.f9988x5a888045), 1).show();
                return;
            }
            this.loginData = AccountLoginData.builder(AccountSource.Email).email(credential.getId()).password(credential.getPassword()).build();
            onLogInWithData(this.loginData);
        } else if (accountType.equals("https://accounts.google.com")) {
            this.oauthLoginManager.loginWithGoogleCredential(credential, this, this);
            startLoader();
        } else if (accountType.equals("https://www.facebook.com")) {
            onLogInWithOAuthOption(OAuthOption.Facebook);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.googleSmartLockController.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        if (this.loaderFrame.isAnimating()) {
            return NavigationTag.RegistrationLandingNoNewImpression;
        }
        return NavigationTag.RegistrationLanding;
    }

    private void onReturnFromRegistration(Intent data) {
        if (data.hasExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_REGISTRATION_DATA)) {
            this.isSignUp = true;
            this.loginData = AccountLoginData.fromRegistrationData((AccountRegistrationData) data.getParcelableExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_REGISTRATION_DATA));
            onLogInWithData(this.loginData);
            return;
        }
        this.isSignUp = false;
        showFragment(LoginFragment.newInstance((AccountLoginData) data.getParcelableExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA)), true);
    }

    private void onReturnFromSocialSignup(Intent data) {
        if (data.hasExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA)) {
            AccountLoginData accountLoginData = (AccountLoginData) data.getParcelableExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA);
            this.isSignUp = false;
            showFragment(LoginFragment.newInstance(accountLoginData), true);
            return;
        }
        this.isSignUp = true;
        onLogInWithData(this.loginData);
    }

    private void onMoreOptionsSelected(Intent data) {
        if (data.getBooleanExtra(MoreOptionsActivity.EXTRA_RESULT_CREATE_ACCOUNT_SELECTED, false)) {
            onSignUp();
        } else {
            onLogInWithOAuthOption((OAuthOption) data.getSerializableExtra(MoreOptionsActivity.EXTRA_RESULT_OPTION_SELECTED));
        }
    }

    public void onOAuthLoginSuccess(OAuthOption option, String token) {
        onThirdPartyTokenAcquired(option, token);
    }

    public void onOAuthLoginCanceled(OAuthOption option) {
        stopLoader();
    }

    public void onOAuthLoginError(OAuthOption option) {
        stopLoader();
        new SnackbarWrapper().view(this.contentContainer).title(getString(C7331R.string.oauth_login_error_title, new Object[]{getString(option.title)}), true).body(getString(C7331R.string.oauth_login_error_body, new Object[]{getString(option.title)})).duration(3000).action(C7331R.string.retry, LoginActivity$$Lambda$10.lambdaFactory$(this, option)).buildAndShow();
    }

    private void handleExistingAccountError() {
        startLoader();
        if (!TextUtils.isEmpty(this.loginData.email())) {
            AccountCreationRequest.forValidatingEmail(this.loginData.email()).withListener((Observer) this.fetchExistingAccountUsingEmailListener).execute(this.requestManager);
        } else {
            CreateSocialSignupRequest.forFetchingSocialAccountInfoWithExistingAccount(this.loginData).withListener((Observer) this.fetchExistingAccountUsingOAuthListener).execute(this.requestManager);
        }
    }

    private void onExistingEmailAccountRetreived(Account account) {
        stopLoader();
        AccountSource source = AccountSource.findAccountSourceByName(account.getAccountType());
        if (source == null) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Client key and API mismathes. Possibiely due to a recent API change. Please fix ASAP."));
        }
        String firstName = account.getFirstName();
        showFragment(ExistingAccountFragment.newInstanceWithExistingAccountData(AccountLoginData.builder(source).email(this.loginData.email()).firstName(firstName).profilePicture(account.getPictureUrl()).build()), true);
    }

    /* access modifiers changed from: private */
    public void onExistingSocialAccountRetreived(Account account) {
        stopLoader();
        User user = account.getUser();
        String email = user.getEmailAddress();
        String firstName = user.getFirstName();
        showFragment(ExistingAccountFragment.newInstanceWithExistingAccountData(AccountLoginData.builder(AccountSource.findAccountSourceBySignupMethodCode(user.getSignupMethod())).email(email).firstName(firstName).profilePicture(user.getPictureUrl()).build()), true);
    }

    /* access modifiers changed from: private */
    public void onNetworkError(NetworkException e) {
        stopLoader();
        NetworkUtil.tryShowErrorWithSnackbar(this.contentContainer, e, C7331R.string.sign_in_error, C7331R.string.default_sign_in_error);
    }

    private boolean isResetPasswordFlow() {
        return !TextUtils.isEmpty(getIntent().getStringExtra("secret"));
    }
}
