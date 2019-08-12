package com.facebook.accountkit.p029ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitError.Type;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.LoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.Tracker;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.InternalAccountKitError;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.KeyboardObserver.OnVisibleFrameChangedListener;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;

/* renamed from: com.facebook.accountkit.ui.AccountKitActivity */
public final class AccountKitActivity extends AppCompatActivity {
    public static final String ACCOUNT_KIT_ACTIVITY_CONFIGURATION = AccountKitConfiguration.TAG;
    private static final IntentFilter LOGIN_FLOW_BROADCAST_RECEIVER_FILTER = LoginFlowBroadcastReceiver.getIntentFilter();
    private static final String LOGIN_FLOW_MANAGER_KEY = (TAG + ".loginFlowManager");
    private static final String PENDING_LOGIN_FLOW_STATE_KEY = (TAG + ".pendingLoginFlowState");
    private static final String TAG = AccountKitActivity.class.getSimpleName();
    private static final String TRACKING_SMS_KEY = (TAG + ".trackingSms");
    private static final String VIEW_STATE_KEY = (TAG + ".viewState");
    private AccessToken accessToken;
    private String authorizationCode;
    private AccountKitConfiguration configuration;
    private AccountKitError error;
    private String finalAuthState;
    private boolean isActive;
    private KeyboardObserver keyboardObserver;
    private final BroadcastReceiver loginFlowBroadcastReceiver = new LoginFlowBroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (LoginFlowBroadcastReceiver.ACTION_UPDATE.contentEquals(intent.getAction())) {
                Event event = (Event) intent.getSerializableExtra(EXTRA_EVENT);
                ContentController contentController = AccountKitActivity.this.stateStackManager.getContentController();
                switch (C33984.f3111x4c63db9f[event.ordinal()]) {
                    case 1:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onSentCodeComplete(AccountKitActivity.this);
                        return;
                    case 2:
                        AccountKitActivity.this.loginFlowManager.getActivityHandler().onAccountVerifiedComplete(AccountKitActivity.this);
                        return;
                    case 3:
                        ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmSeamlessLogin(AccountKitActivity.this, (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager);
                        return;
                    case 4:
                        if (contentController instanceof EmailLoginContentController) {
                            EmailLoginFlowManager manager = (EmailLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityEmailHandler) manager.getActivityHandler()).onEmailLoginComplete(AccountKitActivity.this, manager, intent.getStringExtra(EXTRA_EMAIL));
                            return;
                        }
                        return;
                    case 5:
                        if (contentController instanceof EmailVerifyContentController) {
                            ((ActivityEmailHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onEmailVerifyRetry(AccountKitActivity.this);
                            return;
                        }
                        return;
                    case 6:
                        if (contentController instanceof ErrorContentController) {
                            ActivityErrorHandler.onErrorRestart(AccountKitActivity.this, LoginFlowState.values()[intent.getIntExtra(EXTRA_RETURN_LOGIN_FLOW_STATE, 0)]);
                            return;
                        }
                        return;
                    case 7:
                        if (contentController instanceof PhoneLoginContentController) {
                            PhoneLoginFlowManager manager2 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) manager2.getActivityHandler()).onPhoneLoginComplete(AccountKitActivity.this, manager2, (PhoneNumber) intent.getParcelableExtra(EXTRA_PHONE_NUMBER));
                            return;
                        }
                        return;
                    case 8:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            PhoneLoginFlowManager manager3 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) manager3.getActivityHandler()).onConfirmationCodeComplete(AccountKitActivity.this, manager3, intent.getStringExtra(EXTRA_CONFIRMATION_CODE));
                            return;
                        }
                        return;
                    case 9:
                        if (contentController instanceof ConfirmationCodeContentController) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onConfirmationCodeRetry(AccountKitActivity.this);
                            return;
                        }
                        return;
                    case 10:
                        if ((contentController instanceof ResendContentController) || (contentController instanceof ConfirmationCodeContentController)) {
                            ((ActivityPhoneHandler) AccountKitActivity.this.loginFlowManager.getActivityHandler()).onResend(AccountKitActivity.this);
                            return;
                        }
                        return;
                    case 11:
                        if (contentController instanceof ResendContentController) {
                            PhoneLoginFlowManager manager4 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) manager4.getActivityHandler()).onResendFacebookNotification(AccountKitActivity.this, manager4);
                            return;
                        }
                        return;
                    case 12:
                        if (contentController instanceof ResendContentController) {
                            PhoneLoginFlowManager manager5 = (PhoneLoginFlowManager) AccountKitActivity.this.loginFlowManager;
                            ((ActivityPhoneHandler) manager5.getActivityHandler()).onResendVoiceCallNotification(AccountKitActivity.this, manager5);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public LoginFlowManager loginFlowManager;
    private Tracker loginTracker;
    private LoginResult result = LoginResult.CANCELLED;
    /* access modifiers changed from: private */
    public StateStackManager stateStackManager;
    private long tokenRefreshIntervalInSeconds;
    private final Bundle viewState = new Bundle();

    /* renamed from: com.facebook.accountkit.ui.AccountKitActivity$4 */
    static /* synthetic */ class C33984 {

        /* renamed from: $SwitchMap$com$facebook$accountkit$ui$LoginFlowBroadcastReceiver$Event */
        static final /* synthetic */ int[] f3111x4c63db9f = new int[Event.values().length];

        static {
            $SwitchMap$com$facebook$accountkit$ui$LoginFlowState = new int[LoginFlowState.values().length];
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.PHONE_NUMBER_INPUT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.EMAIL_INPUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.SENDING_CODE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.SENT_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.CODE_INPUT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.ACCOUNT_VERIFIED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.CONFIRM_ACCOUNT_VERIFIED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.CONFIRM_INSTANT_VERIFICATION_LOGIN.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.EMAIL_VERIFY.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.VERIFYING_CODE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.RESEND.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.ERROR.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginFlowState[LoginFlowState.VERIFIED.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            $SwitchMap$com$facebook$accountkit$ui$LoginType = new int[LoginType.values().length];
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginType[LoginType.PHONE.ordinal()] = 1;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$facebook$accountkit$ui$LoginType[LoginType.EMAIL.ordinal()] = 2;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f3111x4c63db9f[Event.SENT_CODE_COMPLETE.ordinal()] = 1;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f3111x4c63db9f[Event.ACCOUNT_VERIFIED_COMPLETE.ordinal()] = 2;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f3111x4c63db9f[Event.CONFIRM_SEAMLESS_LOGIN.ordinal()] = 3;
            } catch (NoSuchFieldError e19) {
            }
            try {
                f3111x4c63db9f[Event.EMAIL_LOGIN_COMPLETE.ordinal()] = 4;
            } catch (NoSuchFieldError e20) {
            }
            try {
                f3111x4c63db9f[Event.EMAIL_VERIFY_RETRY.ordinal()] = 5;
            } catch (NoSuchFieldError e21) {
            }
            try {
                f3111x4c63db9f[Event.ERROR_RESTART.ordinal()] = 6;
            } catch (NoSuchFieldError e22) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_LOGIN_COMPLETE.ordinal()] = 7;
            } catch (NoSuchFieldError e23) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_CONFIRMATION_CODE_COMPLETE.ordinal()] = 8;
            } catch (NoSuchFieldError e24) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_CONFIRMATION_CODE_RETRY.ordinal()] = 9;
            } catch (NoSuchFieldError e25) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_RESEND.ordinal()] = 10;
            } catch (NoSuchFieldError e26) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_RESEND_FACEBOOK_NOTIFICATION.ordinal()] = 11;
            } catch (NoSuchFieldError e27) {
            }
            try {
                f3111x4c63db9f[Event.PHONE_RESEND_VOICE_CALL_NOTIFICATION.ordinal()] = 12;
            } catch (NoSuchFieldError e28) {
            }
        }
    }

    /* renamed from: com.facebook.accountkit.ui.AccountKitActivity$ResponseType */
    public enum ResponseType {
        CODE("code"),
        TOKEN("token");
        
        private final String value;

        private ResponseType(String value2) {
            this.value = value2;
        }

        public String getValue() {
            return this.value;
        }
    }

    @Deprecated
    /* renamed from: com.facebook.accountkit.ui.AccountKitActivity$TitleType */
    public enum TitleType {
        APP_NAME,
        LOGIN
    }

    private static boolean urlIsRedirectUrl(String url) {
        return url.startsWith(Utility.getRedirectURL());
    }

    /* access modifiers changed from: 0000 */
    public ContentController getContentController() {
        return this.stateStackManager.getContentController();
    }

    public void onBackPressed() {
        if (this.stateStackManager.getContentController() == null) {
            super.onBackPressed();
        } else {
            backPressed();
        }
    }

    public void onBackPressed(View view) {
        onBackPressed();
    }

    public void onCancelPressed(View view) {
        sendCancelResult();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.configuration = (AccountKitConfiguration) intent.getParcelableExtra(ACCOUNT_KIT_ACTIVITY_CONFIGURATION);
        if (this.configuration == null) {
            this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_CONFIGURATION);
            sendResult();
        } else if (!ViewUtility.doesTextColorContrast(this, this.configuration.getUIManager())) {
            Logger.logInvalidUIManager();
            this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_BACKGROUND_CONTRACT);
            sendResult();
        } else {
            int themeId = this.configuration.getUIManager().getThemeId();
            if (themeId != -1) {
                setTheme(themeId);
            }
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
            if (!ViewUtility.isTablet(this)) {
                setRequestedOrientation(1);
            }
            String url = intent.getDataString();
            if (url != null && !urlIsRedirectUrl(url)) {
                sendResult();
            } else if (this.configuration.getLoginType() == null) {
                this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_LOGIN_TYPE);
                sendResult();
            } else if (this.configuration.getResponseType() == null) {
                this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_INTENT_EXTRAS_RESPONSE_TYPE);
                sendResult();
            } else {
                this.stateStackManager = new StateStackManager(this, this.configuration);
                setContentView(C3354R.layout.com_accountkit_activity_layout);
                final ConstrainedLinearLayout contentView = (ConstrainedLinearLayout) findViewById(C3354R.C3356id.com_accountkit_content_view);
                View scrollView = findViewById(C3354R.C3356id.com_accountkit_scroll_view);
                if (!(contentView == null || scrollView == null || contentView.getMinHeight() >= 0 || contentView.getRootView() == null)) {
                    this.keyboardObserver = new KeyboardObserver(scrollView);
                    this.keyboardObserver.setOnVisibleFrameChangedListener(new OnVisibleFrameChangedListener() {
                        public void onVisibleFrameChanged(Rect visibleFrame) {
                            int minHeight = visibleFrame.height();
                            if (minHeight >= 0) {
                                contentView.setMinHeight(minHeight);
                            }
                        }
                    });
                }
                AccountKit.onActivityCreate(this, savedInstanceState);
                if (savedInstanceState != null) {
                    this.viewState.putAll(savedInstanceState.getBundle(VIEW_STATE_KEY));
                }
                onViewReadyWithState(this.viewState, savedInstanceState != null);
                LocalBroadcastManager.getInstance(this).registerReceiver(this.loginFlowBroadcastReceiver, LOGIN_FLOW_BROADCAST_RECEIVER_FILTER);
            }
        }
    }

    public void onPause() {
        super.onPause();
        ContentController contentController = getContentController();
        if (contentController != null) {
            contentController.onPause(this);
        }
        this.isActive = false;
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.loginTracker != null) {
            this.loginTracker.pauseTracking();
        }
        AccountKit.onActivitySaveInstanceState(this, outState);
        if (this.loginFlowManager.getLoginType() == LoginType.PHONE) {
            ActivityPhoneHandler phoneHandler = (ActivityPhoneHandler) this.loginFlowManager.getActivityHandler();
            phoneHandler.pauseSmsTracker();
            this.viewState.putBoolean(TRACKING_SMS_KEY, phoneHandler.isSmsTracking());
            this.viewState.putParcelable(LOGIN_FLOW_MANAGER_KEY, this.loginFlowManager);
        }
        outState.putBundle(VIEW_STATE_KEY, this.viewState);
        super.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String url = intent.getDataString();
        if (url != null) {
            if (!urlIsRedirectUrl(url)) {
                sendResult();
            } else if (getContentController() instanceof EmailVerifyContentController) {
                pushState(LoginFlowState.VERIFYING_CODE, null);
            }
        }
    }

    private void onViewReadyWithState(Bundle viewState2, boolean restored) {
        ViewUtility.applyThemeBackground(this, this.configuration.getUIManager(), findViewById(C3354R.C3356id.com_accountkit_background));
        setNewLoginFlowManagerAndHandler((LoginFlowManager) viewState2.getParcelable(LOGIN_FLOW_MANAGER_KEY));
        if (restored) {
            this.stateStackManager.updateContentController(this);
        } else if (this.configuration != null) {
            switch (this.configuration.getLoginType()) {
                case PHONE:
                    pushState(LoginFlowState.PHONE_NUMBER_INPUT, null);
                    return;
                case EMAIL:
                    pushState(LoginFlowState.EMAIL_INPUT, null);
                    return;
                default:
                    this.error = new AccountKitError(Type.INITIALIZATION_ERROR, InternalAccountKitError.INVALID_LOGIN_TYPE);
                    sendResult();
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ContentController contentController = getContentController();
        if (contentController != null) {
            contentController.onResume(this);
        }
        this.isActive = true;
        if (this.configuration != null) {
            switch (this.configuration.getLoginType()) {
                case PHONE:
                case EMAIL:
                    this.loginTracker = this.loginFlowManager.getActivityHandler().getLoginTracker(this);
                    this.loginTracker.startTracking();
                    break;
            }
            if (this.viewState.getBoolean(TRACKING_SMS_KEY, false) && this.loginFlowManager.getLoginType() == LoginType.PHONE) {
                ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).startSmsTrackerIfPossible(this);
            }
            String pending = this.viewState.getString(PENDING_LOGIN_FLOW_STATE_KEY);
            if (!Utility.isNullOrEmpty(pending)) {
                this.viewState.putString(PENDING_LOGIN_FLOW_STATE_KEY, null);
                pushState(LoginFlowState.valueOf(pending), null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.loginFlowBroadcastReceiver);
        super.onDestroy();
        if (this.keyboardObserver != null) {
            this.keyboardObserver.setOnVisibleFrameChangedListener(null);
            this.keyboardObserver = null;
        }
        if (this.loginTracker != null) {
            this.loginTracker.stopTracking();
            this.loginTracker = null;
        }
        if (this.loginFlowManager != null && this.loginFlowManager.getLoginType() == LoginType.PHONE) {
            ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).stopSmsTracker();
        }
        AccountKit.onActivityDestroy(this);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                backPressed();
                return true;
            default:
                return super.onKeyDown(keyCode, event);
        }
    }

    /* access modifiers changed from: 0000 */
    public void sendCancelResult() {
        sendResult(0, new AccountKitLoginResultImpl(null, null, null, 0, null, true));
    }

    /* access modifiers changed from: 0000 */
    public void sendResult() {
        int resultCode;
        if (this.result == LoginResult.SUCCESS) {
            resultCode = -1;
        } else {
            resultCode = 0;
        }
        sendResult(resultCode, new AccountKitLoginResultImpl(this.accessToken, this.authorizationCode, this.finalAuthState, this.tokenRefreshIntervalInSeconds, this.error, false));
    }

    private void sendResult(int resultCode, AccountKitLoginResult loginResult) {
        if (getCallingActivity() == null) {
            startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            finish();
            return;
        }
        Intent data = new Intent();
        data.putExtra(AccountKitLoginResult.RESULT_KEY, loginResult);
        setResult(resultCode, data);
        finish();
    }

    /* access modifiers changed from: 0000 */
    public void setAuthorizationCode(String authorizationCode2) {
        this.authorizationCode = authorizationCode2;
    }

    /* access modifiers changed from: 0000 */
    public void setFinalAuthState(String finalAuthState2) {
        this.finalAuthState = finalAuthState2;
    }

    /* access modifiers changed from: 0000 */
    public void setAccessToken(AccessToken accessToken2) {
        this.accessToken = accessToken2;
    }

    /* access modifiers changed from: 0000 */
    public void setTokenRefreshIntervalInSeconds(long tokenRefreshIntervalInSeconds2) {
        this.tokenRefreshIntervalInSeconds = tokenRefreshIntervalInSeconds2;
    }

    /* access modifiers changed from: 0000 */
    public void setLoginResult(LoginResult result2) {
        this.result = result2;
    }

    private void backPressed() {
        ContentController contentController = this.stateStackManager.getContentController();
        if (contentController != null) {
            if (contentController instanceof ConfirmationCodeContentController) {
                ((ConfirmationCodeContentController) contentController).setRetry(false);
            }
            onContentControllerDismissed(contentController);
            LoginFlowState fromState = contentController.getLoginFlowState();
            LoginFlowState toState = LoginFlowState.getBackState(fromState);
            switch (fromState) {
                case NONE:
                case PHONE_NUMBER_INPUT:
                case EMAIL_INPUT:
                    sendCancelResult();
                    return;
                case SENDING_CODE:
                case SENT_CODE:
                case CODE_INPUT:
                case ACCOUNT_VERIFIED:
                case CONFIRM_ACCOUNT_VERIFIED:
                case CONFIRM_INSTANT_VERIFICATION_LOGIN:
                case EMAIL_VERIFY:
                case VERIFYING_CODE:
                case RESEND:
                    resetFlowTo(fromState, toState);
                    return;
                case ERROR:
                    resetFlowTo(fromState, ((ErrorContentController) contentController).getReturnState());
                    return;
                case VERIFIED:
                    sendResult();
                    return;
                default:
                    resetFlowTo(fromState, LoginFlowState.NONE);
                    return;
            }
        }
    }

    private void resetFlowTo(LoginFlowState fromState, LoginFlowState toState) {
        this.loginFlowManager.setFlowState(toState);
        OnPopListener onPopListener = new OnPopListener() {
            public void onContentPopped() {
                AccountKitActivity.this.getContentController().onResume(AccountKitActivity.this);
            }
        };
        if (fromState != LoginFlowState.RESEND) {
            setNewLoginFlowManagerAndHandler(null);
        }
        popBackStack(toState, onPopListener);
    }

    /* access modifiers changed from: 0000 */
    public void setNewLoginFlowManagerAndHandler(LoginFlowManager restoredLoginFlowManager) {
        LoginFlowState existingState;
        if (this.loginFlowManager == null) {
            existingState = LoginFlowState.NONE;
        } else {
            existingState = this.loginFlowManager.getFlowState();
        }
        if (restoredLoginFlowManager == null && this.loginFlowManager != null) {
            this.loginFlowManager.cancel();
        }
        switch (this.configuration.getLoginType()) {
            case PHONE:
                this.loginFlowManager = new PhoneLoginFlowManager(this.configuration);
                this.loginFlowManager.setFlowState(existingState);
                return;
            case EMAIL:
                this.loginFlowManager = new EmailLoginFlowManager(this.configuration);
                this.loginFlowManager.setFlowState(existingState);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void popBackStack(LoginFlowState loginFlowState, OnPopListener onPopListener) {
        this.stateStackManager.popBackStack(loginFlowState, onPopListener);
    }

    /* access modifiers changed from: 0000 */
    public void multiPopBackStack(OnPopListener onPopListener) {
        this.stateStackManager.multiPopBackStack(onPopListener);
    }

    /* access modifiers changed from: 0000 */
    public void pushError(AccountKitError error2) {
        String userFacingError = error2 == null ? null : error2.getUserFacingMessage();
        this.error = error2;
        LoginFlowState returnState = LoginFlowState.getBackState(this.loginFlowManager.getFlowState());
        this.loginFlowManager.setFlowState(LoginFlowState.ERROR);
        this.stateStackManager.pushError(this, this.loginFlowManager, returnState, error2, this.stateStackManager.getErrorOnPushListener(userFacingError));
    }

    /* access modifiers changed from: 0000 */
    public void pushState(LoginFlowState loginFlowState, OnPushListener onPushListener) {
        if (this.isActive) {
            this.loginFlowManager.setFlowState(loginFlowState);
            if (onPushListener == null) {
                switch (loginFlowState) {
                    case CODE_INPUT:
                        onPushListener = ((ActivityPhoneHandler) this.loginFlowManager.getActivityHandler()).getConfirmationCodePushListener(this);
                        break;
                    case ERROR:
                        pushError(null);
                        return;
                }
            }
            this.stateStackManager.pushState(this, this.loginFlowManager, onPushListener);
        } else {
            this.viewState.putString(PENDING_LOGIN_FLOW_STATE_KEY, loginFlowState.name());
        }
        if (!loginFlowState.equals(LoginFlowState.ERROR)) {
            this.error = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void onContentControllerDismissed(ContentController contentController) {
        if (contentController != null) {
            contentController.onPause(this);
            logContentControllerDismissed(contentController);
        }
    }

    private void logContentControllerDismissed(ContentController contentController) {
        if (this.configuration != null) {
            if (contentController instanceof PhoneLoginContentController) {
                Logger.logUIPhoneLogin();
            } else if (contentController instanceof SendingCodeContentController) {
                Logger.logUISendingCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof SentCodeContentController) {
                Logger.logUISentCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof ConfirmationCodeContentController) {
                Logger.logUIConfirmationCode();
            } else if (contentController instanceof VerifyingCodeContentController) {
                Logger.logUIVerifyingCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof VerifiedCodeContentController) {
                Logger.logUIVerifiedCode(false, this.configuration.getLoginType());
            } else if (contentController instanceof ErrorContentController) {
                Logger.logUIError(false, this.configuration.getLoginType());
            } else if (contentController instanceof EmailLoginContentController) {
                Logger.logUIEmailLogin();
            } else if (contentController instanceof EmailVerifyContentController) {
                Logger.logUIEmailVerify(false);
            } else if (contentController instanceof ResendContentController) {
                Logger.logUIResend(false);
            } else if (contentController instanceof ConfirmAccountVerifiedContentController) {
                Logger.logUIConfirmAccountVerified(false);
            } else if (contentController instanceof AccountVerifiedContentController) {
                Logger.logUIAccountVerified(false);
            } else {
                throw new AccountKitException(Type.INTERNAL_ERROR, InternalAccountKitError.UNEXPECTED_FRAGMENT, contentController.getClass().getName());
            }
        }
    }
}
