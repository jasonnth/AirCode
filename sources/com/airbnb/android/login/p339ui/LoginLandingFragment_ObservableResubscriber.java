package com.airbnb.android.login.p339ui;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

/* renamed from: com.airbnb.android.login.ui.LoginLandingFragment_ObservableResubscriber */
public class LoginLandingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LoginLandingFragment_ObservableResubscriber(LoginLandingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.countriesResponseRequestListener, "LoginLandingFragment_countriesResponseRequestListener");
        group.resubscribeAll(target.countriesResponseRequestListener);
    }
}
