package com.airbnb.android.login.oauth.strategies;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.utils.LoginUtils.GoogleScopeSet;
import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.auth.api.credentials.Credential;
import java.io.IOException;

public class GoogleLoginStrategy extends OAuthLoginStrategy {
    private static final int RC_ACCOUNT_PICKER = 60062;
    private static final int RC_GOOGLE_AUTH_PERMISSION = 60061;
    private Credential credential;
    /* access modifiers changed from: private */
    public Account googleAccount;

    static class GoogleAuthResponse {
        Intent authIntent;
        String authToken;

        GoogleAuthResponse(String authToken2, Intent authIntent2) {
            this.authToken = authToken2;
            this.authIntent = authIntent2;
        }
    }

    protected GoogleLoginStrategy(AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        super(appCompatActivity, oauthLoginManager);
    }

    public GoogleLoginStrategy withCredential(Credential credential2) {
        this.credential = credential2;
        return this;
    }

    public void login() {
        if (this.credential != null) {
            logIntoGoogleWithScopes(new Account(this.credential.getName(), "com.google"));
            return;
        }
        getActivity().startActivityForResult(AccountManager.newChooseAccountIntent(null, null, new String[]{"com.google"}, false, null, null, null, null), RC_ACCOUNT_PICKER);
    }

    private void logIntoGoogleWithScopes(Account account) {
        this.googleAccount = account;
        new AsyncTask<Void, Void, GoogleAuthResponse>() {
            /* access modifiers changed from: protected */
            public GoogleAuthResponse doInBackground(Void... params) {
                try {
                    return new GoogleAuthResponse(GoogleAuthUtil.getToken(GoogleLoginStrategy.this.getActivity().getApplicationContext(), GoogleLoginStrategy.this.googleAccount, GoogleScopeSet.Login.scopeString), null);
                } catch (UserRecoverableAuthException e) {
                    return new GoogleAuthResponse(null, e.getIntent());
                } catch (GoogleAuthException | IOException e2) {
                    return new GoogleAuthResponse(null, null);
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(GoogleAuthResponse response) {
                if (response.authToken != null) {
                    GoogleLoginStrategy.this.finishWithToken(response.authToken);
                } else if (response.authIntent != null) {
                    GoogleLoginStrategy.this.getActivity().startActivityForResult(response.authIntent, GoogleLoginStrategy.RC_GOOGLE_AUTH_PERMISSION);
                } else {
                    GoogleLoginStrategy.this.finishWithError();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_GOOGLE_AUTH_PERMISSION && resultCode == -1) {
            logIntoGoogleWithScopes(this.googleAccount);
        } else if (requestCode == RC_ACCOUNT_PICKER && resultCode == -1) {
            logIntoGoogleWithScopes(new Account(data.getStringExtra("authAccount"), "com.google"));
        } else if (requestCode == RC_ACCOUNT_PICKER || requestCode == RC_GOOGLE_AUTH_PERMISSION) {
            finishWithCanceled();
        }
    }

    /* access modifiers changed from: protected */
    public OAuthOption getOAuthOption() {
        return OAuthOption.Google;
    }
}
