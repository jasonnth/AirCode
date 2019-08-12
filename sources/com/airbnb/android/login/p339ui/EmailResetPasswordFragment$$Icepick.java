package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.login.p339ui.EmailResetPasswordFragment;
import com.airbnb.android.registration.models.AccountLoginData;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment$$Icepick */
public class EmailResetPasswordFragment$$Icepick<T extends EmailResetPasswordFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10053H = new Helper("com.airbnb.android.login.ui.EmailResetPasswordFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.secret = f10053H.getString(state, "secret");
            target.email = f10053H.getString(state, "email");
            target.newPassword = f10053H.getString(state, "newPassword");
            target.loginData = (AccountLoginData) f10053H.getParcelable(state, "loginData");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10053H.putString(state, "secret", target.secret);
        f10053H.putString(state, "email", target.email);
        f10053H.putString(state, "newPassword", target.newPassword);
        f10053H.putParcelable(state, "loginData", target.loginData);
    }
}
