package com.airbnb.android.login.smartlock;

import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.User;
import com.airbnb.android.login.smartlock.GoogleSmartLockController.GoogleSmartLockCredentialListener;
import com.airbnb.android.registration.models.AccountLoginData;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import icepick.State;

public class GoogleSmartLockControllerImpl implements GoogleSmartLockController, ConnectionCallbacks, OnConnectionFailedListener {
    private static final int RC_READ = 233;
    private static final int RC_SAVE = 234;
    private static final String TAG = GoogleSmartLockControllerImpl.class.getSimpleName();
    private final FragmentActivity activity;
    private final GoogleApiClient credentialsApi;
    @State
    boolean hasRequestedCredential;
    @State
    boolean ignoreCredentialResponse;
    @State
    boolean isRequestingCredential;
    @State
    boolean isResolving;
    private final GoogleSmartLockCredentialListener listener;
    private boolean requestCredentialOnConnect;

    GoogleSmartLockControllerImpl(FragmentActivity activity2, GoogleSmartLockCredentialListener listener2, Bundle savedInstanceState) {
        this.activity = activity2;
        this.listener = listener2;
        this.credentialsApi = new Builder(activity2).addConnectionCallbacks(this).enableAutoManage(activity2, this).addApi(Auth.CREDENTIALS_API).build();
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
    }

    private boolean shouldRequestCredentials() {
        return !this.ignoreCredentialResponse && !this.hasRequestedCredential;
    }

    public void requestCredential() {
        if (this.credentialsApi.isConnected()) {
            doRequestCredentials();
        } else if (shouldRequestCredentials()) {
            this.requestCredentialOnConnect = true;
        }
    }

    private void doRequestCredentials() {
        if (shouldRequestCredentials()) {
            GoogleSmartLockAnalytics.trackRequestCredentialStart();
            this.hasRequestedCredential = true;
            this.isRequestingCredential = true;
            Auth.CredentialsApi.request(this.credentialsApi, new CredentialRequest.Builder().setPasswordLoginSupported(true).setAccountTypes("https://accounts.google.com", "https://www.facebook.com").build()).setResultCallback(GoogleSmartLockControllerImpl$$Lambda$1.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$doRequestCredentials$0(GoogleSmartLockControllerImpl googleSmartLockControllerImpl, CredentialRequestResult credentialRequestResult) {
        if (googleSmartLockControllerImpl.ignoreCredentialResponse) {
            googleSmartLockControllerImpl.listener.onCredentialRetrievalCanceled();
            return;
        }
        googleSmartLockControllerImpl.isRequestingCredential = false;
        if (credentialRequestResult.getStatus().isSuccess()) {
            Credential credential = credentialRequestResult.getCredential();
            GoogleSmartLockAnalytics.trackCredentialRetrieved(credential);
            googleSmartLockControllerImpl.listener.onCredentialRetrievalSuccess(credential);
            return;
        }
        googleSmartLockControllerImpl.resolveResult(credentialRequestResult.getStatus());
    }

    private void resolveResult(Status status) {
        if (!this.isResolving) {
            this.isResolving = true;
            if (status.getStatusCode() == 6) {
                try {
                    GoogleSmartLockAnalytics.trackResolveCredentialRequestStart();
                    status.startResolutionForResult(this.activity, RC_READ);
                } catch (SendIntentException e) {
                    GoogleSmartLockAnalytics.trackResolveCredentialRequestFailed("Failed to send resolution: " + e);
                    this.listener.onCredentialRetrievalError();
                }
            } else {
                GoogleSmartLockAnalytics.trackResolveCredentialRequestFailed("Unable to resolve status: " + status);
                this.listener.onCredentialRetrievalError();
            }
        }
    }

    public void saveCredential(User user, AccountLoginData loginData) {
        if (this.credentialsApi.isConnected()) {
            doSaveCredential(user, loginData);
        } else {
            GoogleSmartLockAnalytics.trackSaveCredentialFailed("Google Api client not connected");
        }
    }

    public void doSaveCredential(User user, AccountLoginData loginData) {
        Credential.Builder builder = new Credential.Builder(user.getEmailAddress()).setName(user.getName()).setProfilePictureUri(Uri.parse(user.getPictureUrlForThumbnail()));
        switch (loginData.accountSource()) {
            case Email:
                if (TextUtils.isEmpty(loginData.password())) {
                    BugsnagWrapper.throwOrNotify(new RuntimeException("Google Smartlock email save credential with empty password"));
                }
                builder.setPassword(loginData.password());
                break;
            case Google:
                builder.setAccountType("https://accounts.google.com");
                break;
            case Facebook:
                builder.setAccountType("https://www.facebook.com");
                break;
            default:
                return;
        }
        Credential credentialToSave = builder.build();
        GoogleSmartLockAnalytics.trackSaveCredentialAttempt(credentialToSave);
        Auth.CredentialsApi.save(this.credentialsApi, credentialToSave).setResultCallback(GoogleSmartLockControllerImpl$$Lambda$2.lambdaFactory$(this, credentialToSave));
        Auth.CredentialsApi.disableAutoSignIn(this.credentialsApi);
    }

    static /* synthetic */ void lambda$doSaveCredential$1(GoogleSmartLockControllerImpl googleSmartLockControllerImpl, Credential credentialToSave, Status result) {
        Status status = result.getStatus();
        if (status.isSuccess()) {
            GoogleSmartLockAnalytics.trackSaveCredentialSuccess(credentialToSave);
        } else if (status.hasResolution()) {
            try {
                GoogleSmartLockAnalytics.trackResolveSaveCredentialStart(credentialToSave);
                status.startResolutionForResult(googleSmartLockControllerImpl.activity, 234);
            } catch (SendIntentException e) {
                GoogleSmartLockAnalytics.trackResolveSaveCredentialFailed("Failed to send resolution: " + e);
            }
        } else {
            GoogleSmartLockAnalytics.trackResolveSaveCredentialFailed("Failed to save resolution for status: " + status);
        }
    }

    public void deleteInvalidCredential(Credential credential) {
        if (this.credentialsApi.isConnected()) {
            doDeleteInvalidCredential(credential);
        } else {
            GoogleSmartLockAnalytics.trackDeleteCredentialFailed("Google Api client not connected");
        }
    }

    private void doDeleteInvalidCredential(Credential credential) {
        Auth.CredentialsApi.delete(this.credentialsApi, credential).setResultCallback(GoogleSmartLockControllerImpl$$Lambda$3.lambdaFactory$(credential));
    }

    static /* synthetic */ void lambda$doDeleteInvalidCredential$2(Credential credential, Status result) {
        Status status = result.getStatus();
        if (status.isSuccess()) {
            GoogleSmartLockAnalytics.trackDeleteCredentialSuccess(credential);
        } else {
            GoogleSmartLockAnalytics.trackDeleteCredentialFailed("Status: " + status.getStatusMessage());
        }
    }

    public boolean isRequestingCredential() {
        return this.isRequestingCredential;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.isResolving = false;
        switch (requestCode) {
            case RC_READ /*233*/:
                if (resultCode != -1 || data == null) {
                    GoogleSmartLockAnalytics.trackResolveCredentialRequestFailed("User cancelled");
                    this.listener.onCredentialRetrievalCanceled();
                    return;
                }
                Credential credential = (Credential) data.getParcelableExtra(Credential.EXTRA_KEY);
                GoogleSmartLockAnalytics.trackCredentialRetrieved(credential);
                this.listener.onCredentialRetrievalSuccess(credential);
                return;
            case 234:
                if (resultCode == -1) {
                    GoogleSmartLockAnalytics.trackResolveSaveCredentialSuccess();
                    return;
                } else {
                    GoogleSmartLockAnalytics.trackResolveSaveCredentialCancel();
                    return;
                }
            default:
                return;
        }
    }

    public void ignoreCredentialResponse() {
        this.ignoreCredentialResponse = true;
    }

    public void onConnected(Bundle bundle) {
        C0715L.m1189d(TAG, "onConnected: " + bundle);
        if (this.requestCredentialOnConnect) {
            doRequestCredentials();
            this.requestCredentialOnConnect = false;
        }
    }

    public void onConnectionSuspended(int cause) {
        C0715L.m1189d(TAG, "onConnectionSuspended: " + cause);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        C0715L.m1189d(TAG, "onConnectionFailed: " + connectionResult);
    }
}
