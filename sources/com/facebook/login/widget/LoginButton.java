package com.facebook.login.widget;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.FontMetrics;
import android.os.Bundle;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.C3344R;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.LoginAuthorizationType;
import com.facebook.internal.Utility;
import com.facebook.login.DefaultAudience;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.ToolTipPopup.Style;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LoginButton extends FacebookButtonBase {
    private static final String TAG = LoginButton.class.getName();
    private AccessTokenTracker accessTokenTracker;
    /* access modifiers changed from: private */
    public boolean confirmLogout;
    /* access modifiers changed from: private */
    public String loginLogoutEventName = AnalyticsEvents.EVENT_LOGIN_VIEW_USAGE;
    private LoginManager loginManager;
    private String loginText;
    private String logoutText;
    /* access modifiers changed from: private */
    public LoginButtonProperties properties = new LoginButtonProperties();
    private boolean toolTipChecked;
    private long toolTipDisplayTime = ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME;
    private ToolTipMode toolTipMode;
    private ToolTipPopup toolTipPopup;
    private Style toolTipStyle = Style.BLUE;

    static class LoginButtonProperties {
        /* access modifiers changed from: private */
        public LoginAuthorizationType authorizationType = null;
        private DefaultAudience defaultAudience = DefaultAudience.FRIENDS;
        private LoginBehavior loginBehavior = LoginBehavior.NATIVE_WITH_FALLBACK;
        /* access modifiers changed from: private */

        /* renamed from: permissions reason: collision with root package name */
        public List<String> f11344permissions = Collections.emptyList();

        LoginButtonProperties() {
        }

        public void setDefaultAudience(DefaultAudience defaultAudience2) {
            this.defaultAudience = defaultAudience2;
        }

        public DefaultAudience getDefaultAudience() {
            return this.defaultAudience;
        }

        public void setReadPermissions(List<String> permissions2) {
            if (LoginAuthorizationType.PUBLISH.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setReadPermissions after setPublishPermissions has been called.");
            }
            this.f11344permissions = permissions2;
            this.authorizationType = LoginAuthorizationType.READ;
        }

        public void setPublishPermissions(List<String> permissions2) {
            if (LoginAuthorizationType.READ.equals(this.authorizationType)) {
                throw new UnsupportedOperationException("Cannot call setPublishPermissions after setReadPermissions has been called.");
            } else if (Utility.isNullOrEmpty((Collection<T>) permissions2)) {
                throw new IllegalArgumentException("Permissions for publish actions cannot be null or empty.");
            } else {
                this.f11344permissions = permissions2;
                this.authorizationType = LoginAuthorizationType.PUBLISH;
            }
        }

        /* access modifiers changed from: 0000 */
        public List<String> getPermissions() {
            return this.f11344permissions;
        }

        public void clearPermissions() {
            this.f11344permissions = null;
            this.authorizationType = null;
        }

        public void setLoginBehavior(LoginBehavior loginBehavior2) {
            this.loginBehavior = loginBehavior2;
        }

        public LoginBehavior getLoginBehavior() {
            return this.loginBehavior;
        }
    }

    protected class LoginClickListener implements OnClickListener {
        protected LoginClickListener() {
        }

        public void onClick(View v) {
            LoginButton.this.callExternalOnClickListener(v);
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null) {
                performLogout(LoginButton.this.getContext());
            } else {
                performLogin();
            }
            AppEventsLogger logger = AppEventsLogger.newLogger(LoginButton.this.getContext());
            Bundle parameters = new Bundle();
            parameters.putInt("logging_in", accessToken != null ? 0 : 1);
            logger.logSdkEvent(LoginButton.this.loginLogoutEventName, null, parameters);
        }

        /* access modifiers changed from: protected */
        public void performLogin() {
            LoginManager loginManager = getLoginManager();
            if (LoginAuthorizationType.PUBLISH.equals(LoginButton.this.properties.authorizationType)) {
                if (LoginButton.this.getFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.f11344permissions);
                } else if (LoginButton.this.getNativeFragment() != null) {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.f11344permissions);
                } else {
                    loginManager.logInWithPublishPermissions(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.f11344permissions);
                }
            } else if (LoginButton.this.getFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getFragment(), (Collection<String>) LoginButton.this.properties.f11344permissions);
            } else if (LoginButton.this.getNativeFragment() != null) {
                loginManager.logInWithReadPermissions(LoginButton.this.getNativeFragment(), (Collection<String>) LoginButton.this.properties.f11344permissions);
            } else {
                loginManager.logInWithReadPermissions(LoginButton.this.getActivity(), (Collection<String>) LoginButton.this.properties.f11344permissions);
            }
        }

        /* access modifiers changed from: protected */
        public void performLogout(Context context) {
            String message;
            final LoginManager loginManager = getLoginManager();
            if (LoginButton.this.confirmLogout) {
                String logout = LoginButton.this.getResources().getString(C3344R.string.com_facebook_loginview_log_out_action);
                String cancel = LoginButton.this.getResources().getString(C3344R.string.com_facebook_loginview_cancel_action);
                Profile profile = Profile.getCurrentProfile();
                if (profile == null || profile.getName() == null) {
                    message = LoginButton.this.getResources().getString(C3344R.string.com_facebook_loginview_logged_in_using_facebook);
                } else {
                    message = String.format(LoginButton.this.getResources().getString(C3344R.string.com_facebook_loginview_logged_in_as), new Object[]{profile.getName()});
                }
                Builder builder = new Builder(context);
                builder.setMessage(message).setCancelable(true).setPositiveButton(logout, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        loginManager.logOut();
                    }
                }).setNegativeButton(cancel, null);
                builder.create().show();
                return;
            }
            loginManager.logOut();
        }

        /* access modifiers changed from: protected */
        public LoginManager getLoginManager() {
            LoginManager manager = LoginManager.getInstance();
            manager.setDefaultAudience(LoginButton.this.getDefaultAudience());
            manager.setLoginBehavior(LoginButton.this.getLoginBehavior());
            return manager;
        }
    }

    public enum ToolTipMode {
        AUTOMATIC(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC, 0),
        DISPLAY_ALWAYS("display_always", 1),
        NEVER_DISPLAY("never_display", 2);
        
        public static ToolTipMode DEFAULT;
        private int intValue;
        private String stringValue;

        static {
            DEFAULT = AUTOMATIC;
        }

        public static ToolTipMode fromInt(int enumValue) {
            ToolTipMode[] values;
            for (ToolTipMode mode : values()) {
                if (mode.getValue() == enumValue) {
                    return mode;
                }
            }
            return null;
        }

        private ToolTipMode(String stringValue2, int value) {
            this.stringValue = stringValue2;
            this.intValue = value;
        }

        public String toString() {
            return this.stringValue;
        }

        public int getValue() {
            return this.intValue;
        }
    }

    public LoginButton(Context context) {
        super(context, null, 0, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public LoginButton(Context context, AttributeSet attrs) {
        super(context, attrs, 0, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public LoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle, 0, AnalyticsEvents.EVENT_LOGIN_BUTTON_CREATE, AnalyticsEvents.EVENT_LOGIN_BUTTON_DID_TAP);
    }

    public void setDefaultAudience(DefaultAudience defaultAudience) {
        this.properties.setDefaultAudience(defaultAudience);
    }

    public DefaultAudience getDefaultAudience() {
        return this.properties.getDefaultAudience();
    }

    public void setReadPermissions(List<String> permissions2) {
        this.properties.setReadPermissions(permissions2);
    }

    public void setReadPermissions(String... permissions2) {
        this.properties.setReadPermissions(Arrays.asList(permissions2));
    }

    public void setPublishPermissions(List<String> permissions2) {
        this.properties.setPublishPermissions(permissions2);
    }

    public void setPublishPermissions(String... permissions2) {
        this.properties.setPublishPermissions(Arrays.asList(permissions2));
    }

    public void clearPermissions() {
        this.properties.clearPermissions();
    }

    public void setLoginBehavior(LoginBehavior loginBehavior) {
        this.properties.setLoginBehavior(loginBehavior);
    }

    public LoginBehavior getLoginBehavior() {
        return this.properties.getLoginBehavior();
    }

    public void setToolTipStyle(Style toolTipStyle2) {
        this.toolTipStyle = toolTipStyle2;
    }

    public void setToolTipMode(ToolTipMode toolTipMode2) {
        this.toolTipMode = toolTipMode2;
    }

    public ToolTipMode getToolTipMode() {
        return this.toolTipMode;
    }

    public void setToolTipDisplayTime(long displayTime) {
        this.toolTipDisplayTime = displayTime;
    }

    public long getToolTipDisplayTime() {
        return this.toolTipDisplayTime;
    }

    public void dismissToolTip() {
        if (this.toolTipPopup != null) {
            this.toolTipPopup.dismiss();
            this.toolTipPopup = null;
        }
    }

    public void registerCallback(CallbackManager callbackManager, FacebookCallback<LoginResult> callback) {
        getLoginManager().registerCallback(callbackManager, callback);
    }

    public void unregisterCallback(CallbackManager callbackManager) {
        getLoginManager().unregisterCallback(callbackManager);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.accessTokenTracker != null && !this.accessTokenTracker.isTracking()) {
            this.accessTokenTracker.startTracking();
            setButtonText();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.toolTipChecked && !isInEditMode()) {
            this.toolTipChecked = true;
            checkToolTipSettings();
        }
    }

    /* access modifiers changed from: private */
    public void showToolTipPerSettings(FetchedAppSettings settings) {
        if (settings != null && settings.getNuxEnabled() && getVisibility() == 0) {
            displayToolTip(settings.getNuxContent());
        }
    }

    private void displayToolTip(String toolTipString) {
        this.toolTipPopup = new ToolTipPopup(toolTipString, this);
        this.toolTipPopup.setStyle(this.toolTipStyle);
        this.toolTipPopup.setNuxDisplayTime(this.toolTipDisplayTime);
        this.toolTipPopup.show();
    }

    private void checkToolTipSettings() {
        switch (this.toolTipMode) {
            case AUTOMATIC:
                final String appId = Utility.getMetadataApplicationId(getContext());
                FacebookSdk.getExecutor().execute(new Runnable() {
                    public void run() {
                        final FetchedAppSettings settings = FetchedAppSettingsManager.queryAppSettings(appId, false);
                        LoginButton.this.getActivity().runOnUiThread(new Runnable() {
                            public void run() {
                                LoginButton.this.showToolTipPerSettings(settings);
                            }
                        });
                    }
                });
                return;
            case DISPLAY_ALWAYS:
                displayToolTip(getResources().getString(C3344R.string.com_facebook_tooltip_default));
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        setButtonText();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.accessTokenTracker != null) {
            this.accessTokenTracker.stopTracking();
        }
        dismissToolTip();
    }

    /* access modifiers changed from: protected */
    public void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility != 0) {
            dismissToolTip();
        }
    }

    /* access modifiers changed from: 0000 */
    public List<String> getPermissions() {
        return this.properties.getPermissions();
    }

    /* access modifiers changed from: 0000 */
    public void setProperties(LoginButtonProperties properties2) {
        this.properties = properties2;
    }

    /* access modifiers changed from: protected */
    public void configureButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super.configureButton(context, attrs, defStyleAttr, defStyleRes);
        setInternalOnClickListener(getNewLoginClickListener());
        parseLoginButtonAttributes(context, attrs, defStyleAttr, defStyleRes);
        if (isInEditMode()) {
            setBackgroundColor(getResources().getColor(C3344R.color.com_facebook_blue));
            this.loginText = "Continue with Facebook";
        } else {
            this.accessTokenTracker = new AccessTokenTracker() {
                /* access modifiers changed from: protected */
                public void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                    LoginButton.this.setButtonText();
                }
            };
        }
        setButtonText();
        setCompoundDrawablesWithIntrinsicBounds(AppCompatResources.getDrawable(getContext(), C3344R.C3345drawable.com_facebook_button_login_logo), null, null, null);
    }

    /* access modifiers changed from: protected */
    public LoginClickListener getNewLoginClickListener() {
        return new LoginClickListener();
    }

    /* access modifiers changed from: protected */
    public int getDefaultStyleResource() {
        return C3344R.C3347style.com_facebook_loginview_default_style;
    }

    private void parseLoginButtonAttributes(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this.toolTipMode = ToolTipMode.DEFAULT;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, C3344R.styleable.com_facebook_login_view, defStyleAttr, defStyleRes);
        try {
            this.confirmLogout = a.getBoolean(C3344R.styleable.com_facebook_login_view_com_facebook_confirm_logout, true);
            this.loginText = a.getString(C3344R.styleable.com_facebook_login_view_com_facebook_login_text);
            this.logoutText = a.getString(C3344R.styleable.com_facebook_login_view_com_facebook_logout_text);
            this.toolTipMode = ToolTipMode.fromInt(a.getInt(C3344R.styleable.com_facebook_login_view_com_facebook_tooltip_mode, ToolTipMode.DEFAULT.getValue()));
        } finally {
            a.recycle();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        FontMetrics fontMetrics = getPaint().getFontMetrics();
        int height = getCompoundPaddingTop() + ((int) Math.ceil((double) (Math.abs(fontMetrics.top) + Math.abs(fontMetrics.bottom)))) + getCompoundPaddingBottom();
        Resources resources = getResources();
        String text = this.loginText;
        if (text == null) {
            text = resources.getString(C3344R.string.com_facebook_loginview_log_in_button_continue);
            int logInWidth = measureButtonWidth(text);
            if (resolveSize(logInWidth, widthMeasureSpec) < logInWidth) {
                text = resources.getString(C3344R.string.com_facebook_loginview_log_in_button);
            }
        }
        int logInWidth2 = measureButtonWidth(text);
        String text2 = this.logoutText;
        if (text2 == null) {
            text2 = resources.getString(C3344R.string.com_facebook_loginview_log_out_button);
        }
        setMeasuredDimension(resolveSize(Math.max(logInWidth2, measureButtonWidth(text2)), widthMeasureSpec), height);
    }

    private int measureButtonWidth(String text) {
        return getCompoundPaddingLeft() + getCompoundDrawablePadding() + measureTextWidth(text) + getCompoundPaddingRight();
    }

    /* access modifiers changed from: private */
    public void setButtonText() {
        String string;
        Resources resources = getResources();
        if (!isInEditMode() && AccessToken.getCurrentAccessToken() != null) {
            if (this.logoutText != null) {
                string = this.logoutText;
            } else {
                string = resources.getString(C3344R.string.com_facebook_loginview_log_out_button);
            }
            setText(string);
        } else if (this.loginText != null) {
            setText(this.loginText);
        } else {
            String text = resources.getString(C3344R.string.com_facebook_loginview_log_in_button_continue);
            int width = getWidth();
            if (width != 0 && measureButtonWidth(text) > width) {
                text = resources.getString(C3344R.string.com_facebook_loginview_log_in_button);
            }
            setText(text);
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultRequestCode() {
        return RequestCodeOffset.Login.toRequestCode();
    }

    /* access modifiers changed from: 0000 */
    public LoginManager getLoginManager() {
        if (this.loginManager == null) {
            this.loginManager = LoginManager.getInstance();
        }
        return this.loginManager;
    }

    /* access modifiers changed from: 0000 */
    public void setLoginManager(LoginManager loginManager2) {
        this.loginManager = loginManager2;
    }
}
