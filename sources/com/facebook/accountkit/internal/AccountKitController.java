package com.facebook.accountkit.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit.InitializeCallback;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountPreferences;
import com.facebook.accountkit.EmailLoginModel;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.p029ui.AdvancedUIManagerWrapper;
import com.facebook.accountkit.p029ui.BaseUIManager;
import com.facebook.accountkit.p029ui.LoginType;
import com.facebook.accountkit.p029ui.NotificationChannel;
import com.facebook.accountkit.p029ui.SkinManager;
import com.facebook.accountkit.p029ui.ThemeUIManager;
import com.facebook.accountkit.p029ui.UIManager;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccountKitController {
    private static final ExperimentationConfigurator experimentationConfigurator = new ExperimentationConfigurator();
    /* access modifiers changed from: private */
    public static final Initializer initializer = new Initializer();

    public static class Logger {
        public static void logUIPhoneLoginShown(String countryCode, String countryCodeSource, boolean isRetry) {
            JSONObject extras = new JSONObject();
            try {
                extras.put("country_code", countryCode);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_COUNTRY_CODE_SOURCE, countryCodeSource);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_READ_NUMBER_PERM, Utility.hasReadPhoneStatePermissions(AccountKitController.initializer.getApplicationContext()) ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_RECEIVE_SMS_PERM, Utility.hasReceiveSmsPermissions(AccountKitController.initializer.getApplicationContext()) ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SIM_LOCALE, Utility.getCurrentCountry(AccountKitController.initializer.getApplicationContext()));
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_RETRY, isRetry ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, "phone", true, extras);
        }

        public static void logUIEmailLoginShown(boolean isRetry) {
            JSONObject extras = new JSONObject();
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_GET_ACCOUNTS_PERM, Utility.hasGetAccountsPermissions(AccountKitController.initializer.getApplicationContext()) ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_RETRY, isRetry ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_EMAIL_VIEW, "email", true, extras);
        }

        public static void logUIPhoneLoginInteraction(String buttonName, String url) {
            JSONObject extras = new JSONObject();
            if (url != null) {
                try {
                    extras.put("link", url);
                } catch (JSONException e) {
                }
            }
            logUIInteraction(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, buttonName, extras);
        }

        public static void logUIPhoneLoginInteraction(String buttonName, String phoneNumberSource, PhoneNumber submittedPhoneNumber) {
            JSONObject extras = new JSONObject();
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_PHONE_NUMBER_SOURCE, phoneNumberSource);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SUBMITTED_PHONE_NUMBER, submittedPhoneNumber.toString());
            } catch (JSONException e) {
            }
            logUIInteraction(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, buttonName, extras);
        }

        public static void logUIEmailLoginInteraction(String buttonName, String emailAppSuppliedUse, String emailSelectedUse, String submittedEmail) {
            JSONObject extras = new JSONObject();
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_EMAIL, submittedEmail);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_EMAIL_APP_SUPPLIED_USE, emailAppSuppliedUse);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_EMAIL_SELECTED_USE, emailSelectedUse);
            } catch (JSONException e) {
            }
            logUIInteraction(InternalLogger.EVENT_NAME_EMAIL_VIEW, buttonName, extras);
        }

        public static void logUIEmailVerifyInteraction(String buttonName) {
            logUIInteraction(InternalLogger.EVENT_NAME_EMAIL_VERIFY_VIEW, buttonName, null);
        }

        public static void logUIPhoneLogin() {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_PHONE_NUMBER_VIEW, "phone", false, null);
        }

        public static void logUIConfirmationCodeShown(boolean isRetry) {
            JSONObject extras = new JSONObject();
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_RETRY, isRetry ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_CONFIRMATION_CODE_VIEW, "phone", true, extras);
        }

        public static void logUIConfirmationCode() {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_CONFIRMATION_CODE_VIEW, "phone", false, null);
        }

        public static void logUIConfirmationCodeInteraction(String buttonName, String url) {
            JSONObject extras = new JSONObject();
            if (url != null) {
                try {
                    extras.put("link", url);
                } catch (JSONException e) {
                }
            }
            logUIInteraction(InternalLogger.EVENT_NAME_CONFIRMATION_CODE_VIEW, buttonName, extras);
        }

        public static void logUIConfirmationCodeInteraction(String buttonName) {
            logUIInteraction(InternalLogger.EVENT_NAME_CONFIRMATION_CODE_VIEW, buttonName, null);
        }

        public static void logUIConfirmationCodeInteraction(String buttonName, String initialConfirmationCode, String confirmationCodeSubmitted) {
            JSONObject extras = new JSONObject();
            try {
                if (Utility.isNullOrEmpty(initialConfirmationCode)) {
                    extras.put("confirmation_code", InternalLogger.EVENT_PARAM_EXTRAS_NOT_SUPPLIED);
                } else if (!Utility.isNullOrEmpty(confirmationCodeSubmitted)) {
                    if (initialConfirmationCode.equals(confirmationCodeSubmitted)) {
                        extras.put("confirmation_code", InternalLogger.EVENT_PARAM_EXTRAS_EQUALS);
                    } else {
                        extras.put("confirmation_code", InternalLogger.EVENT_PARAM_EXTRAS_NOT_EQUALS);
                    }
                }
            } catch (JSONException e) {
            }
            logUIInteraction(InternalLogger.EVENT_NAME_CONFIRMATION_CODE_VIEW, buttonName, extras);
        }

        public static void logUIError(boolean isPresented, LoginType loginType) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_ERROR_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", isPresented, null);
        }

        public static void logUIErrorInteraction(String buttonName) {
            logUIInteraction(InternalLogger.EVENT_NAME_ERROR_VIEW, buttonName);
        }

        public static void logInvalidUIManager() {
            logUIInteraction(InternalLogger.EVENT_INVALID_UI_MANAGER, null);
        }

        public static void logUIResend(boolean isPresented) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_RESEND_VIEW, "phone", isPresented, null);
        }

        public static void logUIResendInteraction(String buttonName) {
            logUIInteraction(InternalLogger.EVENT_NAME_RESEND_VIEW, buttonName);
        }

        public static void logUIConfirmSeamlessLoginInteraction(String buttonName) {
            logUIInteraction(InternalLogger.EVENT_NAME_CONFIRM_ACCOUNT_VERIFIED_VIEW, buttonName);
        }

        public static void logUISendingCode(boolean isPresented, LoginType loginType) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_SENDING_CODE_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", isPresented, null);
        }

        public static void logUISentCode(boolean isPresented, LoginType loginType) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_SENT_CODE_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", isPresented, null);
        }

        public static void logUIVerifyingCode(boolean isPresented, LoginType loginType) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_VERIFYING_CODE_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", isPresented, null);
        }

        public static void logUIVerifiedCode(boolean isPresented, LoginType loginType) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_VERIFIED_CODE_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", isPresented, null);
        }

        public static void logUIEmailLogin() {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_EMAIL_VIEW, "email", false, null);
        }

        public static void logUIEmailVerify(boolean isPresented) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_EMAIL_VERIFY_VIEW, "email", isPresented, null);
        }

        public static void logUICountryCode(boolean isPresented, String selectedCountry) {
            JSONObject extras = new JSONObject();
            try {
                extras.put("country_code", selectedCountry);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_COUNTRY_CODE_VIEW, "phone", isPresented, extras);
        }

        public static void logUIAccountVerified(boolean isPresented) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_ACCOUNT_VERIFIED_VIEW, "phone", isPresented, null);
        }

        public static void logUIConfirmAccountVerified(boolean isPresented) {
            AccountKitController.initializer.getLogger().logImpression(InternalLogger.EVENT_NAME_CONFIRM_ACCOUNT_VERIFIED_VIEW, "phone", isPresented, null);
        }

        public static void logUIManager(UIManager uiManager) {
            String uiManagerType;
            JSONObject extras = new JSONObject();
            try {
                if (uiManager instanceof SkinManager) {
                    uiManagerType = InternalLogger.EVENT_PARAM_UIMANAGER_SKIN;
                } else if (uiManager instanceof AdvancedUIManagerWrapper) {
                    uiManagerType = InternalLogger.EVENT_PARAM_UIMANAGER_ADVANCED;
                } else if (uiManager instanceof ThemeUIManager) {
                    uiManagerType = InternalLogger.EVENT_PARAM_UIMANAGER_THEME;
                } else if (uiManager instanceof BaseUIManager) {
                    uiManagerType = InternalLogger.EVENT_PARAM_UIMANAGER_BASE;
                } else {
                    uiManagerType = InternalLogger.EVENT_PARAM_UIMANAGER_DEFAULT;
                }
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_UIMANAGER, uiManagerType);
                if (uiManager instanceof SkinManager) {
                    SkinManager skinManager = (SkinManager) uiManager;
                    extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SKIN_TYPE, skinManager.getSkin());
                    extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SKIN_MANAGER_HAS_BG_IMAGE, skinManager.hasBackgroundImage());
                    extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SKIN_MANAGER_PRIMARY_COLOR, skinManager.getPrimaryColor());
                    extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SKIN_MANAGER_TINT, skinManager.getTint());
                    extras.put(InternalLogger.EVENT_PARAM_EXTRAS_SKIN_MANAGER_TINT_INTENSITY, skinManager.getTintIntensity());
                }
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logUIManager(InternalLogger.EVENT_NAME_UI_MANAGER_VIEW, extras);
        }

        public static void logUICustomFragment(LoginType loginType, String fragmentType, boolean fragmentProvided) {
            JSONObject extras = new JSONObject();
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_CUSTOM_VIEW_TYPE, fragmentType);
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_CUSTOM_VIEW_PROVIDED, fragmentProvided);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logCustomUI(InternalLogger.EVENT_NAME_CUSTOM_VIEW, loginType.equals(LoginType.PHONE) ? "phone" : "email", extras);
        }

        private static void logUIInteraction(String uiName, String buttonName, JSONObject extras) {
            if (extras == null) {
                extras = new JSONObject();
            }
            try {
                extras.put(InternalLogger.EVENT_PARAM_EXTRAS_BUTTON_CLICKED_TYPE, buttonName);
            } catch (JSONException e) {
            }
            AccountKitController.initializer.getLogger().logButtonImpression(uiName, "phone", extras);
        }

        private static void logUIInteraction(String uiName, String buttonName) {
            logUIInteraction(uiName, buttonName, null);
        }
    }

    public static Context getApplicationContext() {
        return initializer.getApplicationContext();
    }

    public static boolean isInitialized() {
        return initializer.isInitialized();
    }

    public static void initialize(Context applicationContext, InitializeCallback callback) {
        initializer.initialize(applicationContext, callback);
        experimentationConfigurator.initialize(applicationContext);
    }

    public static void initializeLogin() {
        initializer.getLoginManager().initializeLogin();
    }

    public static EmailLoginModel logInWithEmail(String email, String responseType, String initialAuthState) {
        if (getCurrentAccessToken() != null) {
            logOut();
        }
        return initializer.getLoginManager().logInWithEmail(email, responseType, initialAuthState);
    }

    public static PhoneLoginModel logInWithPhoneNumber(PhoneNumber phoneNumber, NotificationChannel notificationChannel, String responseType, String initialAuthState) {
        if (getCurrentAccessToken() != null) {
            logOut();
        }
        return initializer.getLoginManager().logInWithPhoneNumber(phoneNumber, notificationChannel, responseType, initialAuthState);
    }

    public static void logOut() {
        initializer.getLoginManager().logOut();
    }

    public static void logOut(AccountKitCallback<Void> callback) {
        initializer.getLoginManager().logOut(callback);
    }

    public static void cancelLogin() {
        initializer.getLoginManager().cancelLogin();
    }

    public static void continueLoginWithCode(String code) {
        initializer.getLoginManager().continueWithCode(code);
    }

    public static void continueSeamlessLogin() {
        initializer.getLoginManager().continueSeamlessLogin();
    }

    public static ExperimentationConfiguration getExperimentationConfiguration() {
        return experimentationConfigurator.getExperimentationConfiguration();
    }

    public static AccessToken getCurrentAccessToken() {
        return initializer.getAccessTokenManager().getCurrentAccessToken();
    }

    public static AccountPreferences getAccountPreferences() {
        AccessToken accessToken = getCurrentAccessToken();
        if (accessToken == null) {
            return null;
        }
        return new AccountPreferencesImpl(accessToken);
    }

    public static void getCurrentAccount(AccountKitCallback<Account> callback) {
        initializer.getLoginManager().getCurrentAccount(callback);
    }

    public static EmailLoginModel getCurrentEmailLogInModel() {
        return initializer.getLoginManager().getCurrentEmailLogInModel();
    }

    public static PhoneLoginModel getCurrentPhoneNumberLogInModel() {
        return initializer.getLoginManager().getCurrentPhoneNumberLogInModel();
    }

    public static void onActivityCreate(Activity activity, Bundle savedInstanceState) {
        initializer.getLoginManager().onActivityCreate(activity, savedInstanceState);
    }

    public static void onActivityDestroy(Activity activity) {
        initializer.getLoginManager().onActivityDestroy(activity);
    }

    public static void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        initializer.getLoginManager().onActivitySaveInstanceState(activity, outState);
    }

    public static String getApplicationId() {
        return initializer.getApplicationId();
    }

    public static String getApplicationName() {
        return initializer.getApplicationName();
    }

    public static String getClientToken() {
        return initializer.getClientToken();
    }

    public static boolean getAccountKitFacebookAppEventsEnabled() {
        return initializer.getAccountKitFacebookAppEventsEnabled();
    }
}
