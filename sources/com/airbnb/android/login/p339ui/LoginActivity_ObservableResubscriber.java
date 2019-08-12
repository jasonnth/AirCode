package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.LoginActivity_ObservableResubscriber */
public class LoginActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public LoginActivity_ObservableResubscriber(LoginActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchExistingAccountUsingOAuthListener, "LoginActivity_fetchExistingAccountUsingOAuthListener");
        group.resubscribeAll(target.fetchExistingAccountUsingOAuthListener);
        setTag((AutoTaggableObserver) target.fetchExistingAccountUsingEmailListener, "LoginActivity_fetchExistingAccountUsingEmailListener");
        group.resubscribeAll(target.fetchExistingAccountUsingEmailListener);
        setTag((AutoTaggableObserver) target.fetchSocialAccountInfoListener, "LoginActivity_fetchSocialAccountInfoListener");
        group.resubscribeAll(target.fetchSocialAccountInfoListener);
        setTag((AutoTaggableObserver) target.loginRequestListener, "LoginActivity_loginRequestListener");
        group.resubscribeAll(target.loginRequestListener);
    }
}
