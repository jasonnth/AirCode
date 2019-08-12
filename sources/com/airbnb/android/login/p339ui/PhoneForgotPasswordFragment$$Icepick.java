package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Icepick */
public class PhoneForgotPasswordFragment$$Icepick<T extends PhoneForgotPasswordFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10058H = new Helper("com.airbnb.android.login.ui.PhoneForgotPasswordFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.airPhone = (AirPhone) f10058H.getParcelable(state, "airPhone");
            target.countryCodeItem = (CountryCodeItem) f10058H.getParcelable(state, "countryCodeItem");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10058H.putParcelable(state, "airPhone", target.airPhone);
        f10058H.putParcelable(state, "countryCodeItem", target.countryCodeItem);
    }
}
