package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.login.p339ui.LoginActivity;
import com.airbnb.android.registration.models.AccountLoginData;
import com.google.android.gms.auth.api.credentials.Credential;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.LoginActivity$$Icepick */
public class LoginActivity$$Icepick<T extends LoginActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10055H = new Helper("com.airbnb.android.login.ui.LoginActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loginData = (AccountLoginData) f10055H.getParcelable(state, "loginData");
            target.isSignUp = f10055H.getBoolean(state, "isSignUp");
            target.credentialFromSmartLock = (Credential) f10055H.getParcelable(state, "credentialFromSmartLock");
            target.login = (Login) f10055H.getParcelable(state, "login");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10055H.putParcelable(state, "loginData", target.loginData);
        f10055H.putBoolean(state, "isSignUp", target.isSignUp);
        f10055H.putParcelable(state, "credentialFromSmartLock", target.credentialFromSmartLock);
        f10055H.putParcelable(state, "login", target.login);
    }
}
