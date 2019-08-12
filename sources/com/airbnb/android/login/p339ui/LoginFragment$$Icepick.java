package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.login.p339ui.LoginFragment;
import com.airbnb.android.login.p339ui.LoginFragment.LoginMode;
import com.airbnb.android.registration.models.AccountLoginData;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.LoginFragment$$Icepick */
public class LoginFragment$$Icepick<T extends LoginFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10056H = new Helper("com.airbnb.android.login.ui.LoginFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.countryCodeItem = (CountryCodeItem) f10056H.getParcelable(state, "countryCodeItem");
            target.airPhone = (AirPhone) f10056H.getParcelable(state, "airPhone");
            target.loginMode = (LoginMode) f10056H.getSerializable(state, "loginMode");
            target.loginData = (AccountLoginData) f10056H.getParcelable(state, "loginData");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10056H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
        f10056H.putParcelable(state, "airPhone", target.airPhone);
        f10056H.putSerializable(state, "loginMode", target.loginMode);
        f10056H.putParcelable(state, "loginData", target.loginData);
    }
}
