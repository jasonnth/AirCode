package com.airbnb.android.login.oauth.strategies;

import android.app.Activity;
import android.content.Intent;
import android.support.p002v7.app.AppCompatActivity;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.utils.LoginUtils;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class FacebookLoginStrategy extends OAuthLoginStrategy {
    public static final List<String> FACEBOOK_PERMISSIONS = Arrays.asList(new String[]{"email", "user_friends", "user_birthday", "user_education_history", "user_hometown", "user_likes", "user_location"});
    public static final List<String> FACEBOOK_REQUIRED_PERMISSIONS = Arrays.asList(new String[]{"email", "user_birthday"});
    CallbackManager callbackManager;

    protected FacebookLoginStrategy(AppCompatActivity appCompatActivity, OAuthLoginManager oauthLoginManager) {
        super(appCompatActivity, oauthLoginManager);
    }

    public void login() {
        if (this.callbackManager == null) {
            this.callbackManager = Factory.create();
        }
        LoginManager.getInstance().registerCallback(this.callbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                if (loginResult.getAccessToken().getPermissions().containsAll(FacebookLoginStrategy.FACEBOOK_REQUIRED_PERMISSIONS)) {
                    FacebookLoginStrategy.this.finishWithToken(loginResult.getAccessToken().getToken());
                } else {
                    LoginManager.getInstance().logInWithReadPermissions((Activity) FacebookLoginStrategy.this.getActivity(), (Collection<String>) LoginUtils.getDeclinedFilteredPermissions(loginResult.getAccessToken().getDeclinedPermissions()));
                }
            }

            public void onCancel() {
                FacebookLoginStrategy.this.finishWithCanceled();
            }

            public void onError(FacebookException error) {
                FacebookLoginStrategy.this.finishWithError();
            }
        });
        LoginManager.getInstance().logInWithReadPermissions((Activity) getActivity(), (Collection<String>) FACEBOOK_PERMISSIONS);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (this.callbackManager != null && !this.callbackManager.onActivityResult(requestCode, resultCode, data)) {
            finishWithCanceled();
        }
    }

    /* access modifiers changed from: protected */
    public OAuthOption getOAuthOption() {
        return OAuthOption.Facebook;
    }
}
