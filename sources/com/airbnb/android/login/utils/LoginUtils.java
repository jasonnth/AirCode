package com.airbnb.android.login.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.p002v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.Patterns;
import android.widget.TextView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.SignUpLoginAnalytics;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.requests.OAuthCallbackRequest;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.common.collect.FluentIterable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class LoginUtils {
    private static final List<String> FACEBOOK_PERMISSIONS = Arrays.asList(new String[]{"email", "user_friends", "user_birthday", "user_education_history", "user_hometown", "user_likes", "user_location"});
    public static final List<String> FACEBOOK_REQUIRED_PERMISSIONS = Arrays.asList(new String[]{"email", "user_birthday"});
    public static final String GOOGLE_AUTHTOKEN_EXTRA = "authtoken";
    public static final int REQUEST_CODE_GOOGLE = 23456;
    public static final int REQUEST_CODE_WEIBO = 1002;
    /* access modifiers changed from: private */
    public static final String TAG = LoginUtils.class.getSimpleName();
    private static final String WEIBO_DEFAULT_EMAIL_PREFIX = "weibo_default_user";
    @Deprecated
    private static final FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        public void onSuccess(LoginResult loginResult) {
            if (loginResult.getAccessToken().getPermissions().containsAll(LoginUtils.FACEBOOK_REQUIRED_PERMISSIONS)) {
                LoginUtils.mListener.onFacebookTokenAcquired(loginResult.getAccessToken().getToken());
                return;
            }
            LoginUtils.mListener.showRequestedPermissions(AuthorizeService.FACEBOOK);
            if (LoginUtils.mListener.getHostingActivity() != null) {
                LoginManager.getInstance().logInWithReadPermissions(LoginUtils.mListener.getHostingActivity(), (Collection<String>) LoginUtils.getDeclinedFilteredPermissions(loginResult.getAccessToken().getDeclinedPermissions()));
            }
        }

        public void onCancel() {
            LoginUtils.mListener.onCancel(AuthorizeService.FACEBOOK);
        }

        public void onError(FacebookException e) {
            LoginUtils.mListener.onError(e.getMessage(), AuthorizeService.FACEBOOK);
        }
    };
    /* access modifiers changed from: private */
    @Deprecated
    public static LoginListener mListener;

    public static class GoogleAuthHelper {
        private String mAccountName;
        /* access modifiers changed from: private */
        public final LoginListener mListener;
        private final GoogleScopeSet mScopeSet;

        public GoogleAuthHelper(LoginListener listener, GoogleScopeSet scopeSet) {
            this.mListener = listener;
            this.mScopeSet = scopeSet;
        }

        @SuppressLint({"NewApi"})
        public void logInToGoogleAsync() {
            new AsyncTask<Void, Void, String>() {
                /* access modifiers changed from: protected */
                public String doInBackground(Void... params) {
                    Pair<String, Intent> returned = GoogleAuthHelper.this.logIntoGoogleWithScopes();
                    if (returned.second != null) {
                        GoogleAuthHelper.this.doPermissions((Intent) returned.second);
                    }
                    return (String) returned.first;
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(String token) {
                    GoogleAuthHelper.this.mListener.onGoogleTokenAcquired(token);
                    super.onPostExecute(token);
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }

        @SuppressLint({"MissingPermission"})
        public void getAccountAndLogin(Context context) throws NoGoogleAccountsFoundException, SecurityException {
            Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
            if (accounts.length == 1) {
                this.mAccountName = accounts[0].name;
                logInToGoogleAsync();
            } else if (accounts.length == 0) {
                throw new NoGoogleAccountsFoundException();
            } else {
                String[] accountEmails = new String[accounts.length];
                for (int i = 0; i < accountEmails.length; i++) {
                    accountEmails[i] = accounts[i].name;
                }
                new Builder(context).setTitle(C7331R.string.login_select_account).setItems((CharSequence[]) accountEmails, LoginUtils$GoogleAuthHelper$$Lambda$1.lambdaFactory$(this, accountEmails)).create().show();
            }
        }

        static /* synthetic */ void lambda$getAccountAndLogin$0(GoogleAuthHelper googleAuthHelper, String[] accountEmails, DialogInterface dialog, int which) {
            dialog.dismiss();
            googleAuthHelper.mAccountName = accountEmails[which];
            googleAuthHelper.logInToGoogleAsync();
        }

        /* access modifiers changed from: private */
        public void doPermissions(Intent intent) {
            this.mListener.getHostingActivity().runOnUiThread(LoginUtils$GoogleAuthHelper$$Lambda$4.lambdaFactory$(this, intent));
        }

        static /* synthetic */ void lambda$doPermissions$1(GoogleAuthHelper googleAuthHelper, Intent intent) {
            if (MiscUtils.canHandleIntent(googleAuthHelper.mListener.getHostingActivity(), intent)) {
                googleAuthHelper.mListener.getHostingActivity().startActivityForResult(intent, LoginUtils.REQUEST_CODE_GOOGLE);
            } else {
                googleAuthHelper.mListener.onCallbackResponse(false, googleAuthHelper.mListener.getHostingActivity().getString(C7331R.string.google_signin_no_play_error));
            }
        }

        /* access modifiers changed from: private */
        public Pair<String, Intent> logIntoGoogleWithScopes() {
            try {
                return Pair.create(GoogleAuthUtil.getToken(this.mListener.getContext(), this.mAccountName, this.mScopeSet.scopeString), null);
            } catch (UserRecoverableAuthException userAuthEx) {
                if (this.mListener != null) {
                    this.mListener.showRequestedPermissions(AuthorizeService.GOOGLE);
                    return Pair.create(null, userAuthEx.getIntent());
                }
            } catch (IOException e) {
                if (this.mListener != null) {
                    this.mListener.onError(null, AuthorizeService.GOOGLE);
                }
            } catch (IllegalArgumentException e2) {
                BugsnagWrapper.notify((Throwable) e2);
                if (this.mListener != null) {
                    this.mListener.onError(null, AuthorizeService.GOOGLE);
                }
            } catch (ParseException | GoogleAuthException e3) {
                Log.wtf(LoginUtils.TAG, e3);
            }
            return Pair.create(null, null);
        }
    }

    public enum GoogleScopeSet {
        Login("oauth2:email profile"),
        VerifiedId("oauth2:server:client_id:622686756548-j87bjniqthcq1e4hbf1msh3fikqn892p.apps.googleusercontent.com:api_scope: email https://www.google.com/m8/feeds");
        
        public final String scopeString;

        private GoogleScopeSet(String scopeString2) {
            this.scopeString = scopeString2;
        }
    }

    public interface LoginListener {
        Context getContext();

        Activity getHostingActivity();

        void onCallbackResponse(boolean z, String str);

        void onCancel(AuthorizeService authorizeService);

        void onError(String str, AuthorizeService authorizeService);

        void onFacebookTokenAcquired(String str);

        void onGoogleTokenAcquired(String str);

        void showRequestedPermissions(AuthorizeService authorizeService);
    }

    public static class NoGoogleAccountsFoundException extends Exception {
    }

    private enum TOSSection {
        TERMS(C7331R.string.terms_of_service, C7331R.string.tos_url_terms),
        PAYMENTS_TERMS(C7331R.string.payments_terms_of_service, C7331R.string.tos_url_payments_terms),
        PRIVACY_POLICY(C7331R.string.privacy_policy, C7331R.string.tos_url_privacy),
        ANTI_DISCRIMINATION(C7331R.string.anti_discrimination_policy_link_text, C7331R.string.tos_url_anti_discrimination),
        CHINA_RADICAL_TRANSPARENCY(C7331R.string.radical_transparency_learn_more, C7331R.string.tos_url_china_terms);
        
        final int title;
        final int url;

        private TOSSection(int title2, int url2) {
            this.title = title2;
            this.url = url2;
        }

        public String htmlTitle(Context context) {
            return context.getString(C7331R.string.white_html_link, new Object[]{context.getString(this.title)});
        }
    }

    public static boolean isDefaultWeiboEmail(String emailAddress) {
        return !TextUtils.isEmpty(emailAddress) && emailAddress.startsWith(WEIBO_DEFAULT_EMAIL_PREFIX);
    }

    public static Set<String> getDeclinedFilteredPermissions(Set<String> declinedPermissions) {
        Set<String> filteredPermissions = new HashSet<>();
        for (String permission : FACEBOOK_REQUIRED_PERMISSIONS) {
            if (declinedPermissions.contains(permission)) {
                filteredPermissions.add(permission);
            }
        }
        return filteredPermissions;
    }

    @Deprecated
    public static void logInToFacebook(CallbackManager callbackManager, LoginListener listener) {
        mListener = listener;
        FacebookSdk.sdkInitialize(mListener.getHostingActivity().getApplicationContext());
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);
        LoginManager.getInstance().logInWithReadPermissions(mListener.getHostingActivity(), (Collection<String>) FACEBOOK_PERMISSIONS);
    }

    @SuppressLint({"MissingPermission"})
    public static void postGoogleToken(final SharedPrefsHelper sharedPrefsHelper, final LoginListener listener, final String code) {
        OAuthCallbackRequest.oauthCallbackServerRequest(code, "google", new NonResubscribableRequestListener<Object>() {
            public void onResponse(Object response) {
                sharedPrefsHelper.saveLoginServiceType(AuthorizeService.GOOGLE.ordinal());
                if (listener != null && listener.getContext() != null) {
                    GoogleAuthUtil.invalidateToken(listener.getContext(), code);
                    listener.onCallbackResponse(true, null);
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                if (listener != null && listener.getContext() != null) {
                    GoogleAuthUtil.invalidateToken(listener.getContext(), code);
                    listener.onCallbackResponse(false, NetworkUtil.errorMessage(error));
                    NetworkUtil.toastGenericNetworkError(listener.getContext());
                }
            }
        }).execute(NetworkUtil.singleFireExecutor());
    }

    static List<String> getEmailsFromAccounts(Account[] accounts) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        List<String> emailAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches() && !emailAccounts.contains(account.name)) {
                emailAccounts.add(account.name);
            }
        }
        return emailAccounts;
    }

    public static void setupTOSText(TextView agreementText, Context context) {
        ArrayList<TOSSection> sections = new ArrayList<>();
        Collections.addAll(sections, TOSSection.values());
        sections.remove(TOSSection.CHINA_RADICAL_TRANSPARENCY);
        String agreement = getAgreementStringWithSections(context, sections, C7331R.string.login_tos_agreement_statement_four_links);
        if (AppLaunchUtils.isUserInChina()) {
            sections.add(TOSSection.CHINA_RADICAL_TRANSPARENCY);
            String learnMoreLink = context.getString(C7331R.string.white_html_link, new Object[]{context.getString(C7331R.string.radical_transparency_learn_more)});
            agreement = agreement + " " + context.getString(C7331R.string.sign_up_china_terms_link, new Object[]{learnMoreLink});
        } else if (AppLaunchUtils.isUserInGermany()) {
            sections.remove(TOSSection.PRIVACY_POLICY);
            String agreement2 = getAgreementStringWithSections(context, sections, C7331R.string.login_tos_agreement_statement_three_links);
            sections.add(TOSSection.PRIVACY_POLICY);
            agreement = agreement2 + " " + context.getString(C7331R.string.login_tos_agreement_statement_germany_extra_sentence, new Object[]{TOSSection.PRIVACY_POLICY.htmlTitle(context)});
        }
        ClickableLinkUtils.setupClickableTextView(agreementText, agreement, LoginUtils$$Lambda$1.lambdaFactory$(sections, context), C7331R.color.canonical_press_darken, true);
    }

    static /* synthetic */ void lambda$setupTOSText$0(ArrayList sections, Context context, int linkIndex) {
        SignUpLoginAnalytics.trackLandingAction("tos_click");
        context.startActivity(WebViewIntentBuilder.newBuilder(context, context.getString(((TOSSection) sections.get(linkIndex)).url)).toIntent());
    }

    private static String getAgreementStringWithSections(Context context, ArrayList<TOSSection> sections, int agreementRes) {
        return context.getString(agreementRes, (Object[]) ((String[]) FluentIterable.from((Iterable<E>) sections).transform(LoginUtils$$Lambda$2.lambdaFactory$(context)).toArray(String.class)));
    }

    static /* synthetic */ String lambda$getAgreementStringWithSections$1(Context context, TOSSection s) {
        if (s != null) {
            return s.htmlTitle(context);
        }
        return null;
    }
}
