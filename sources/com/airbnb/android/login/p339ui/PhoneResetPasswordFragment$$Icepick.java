package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.login.p339ui.PhoneResetPasswordFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Icepick */
public class PhoneResetPasswordFragment$$Icepick<T extends PhoneResetPasswordFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10059H = new Helper("com.airbnb.android.login.ui.PhoneResetPasswordFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.airPhone = (AirPhone) f10059H.getParcelable(state, "airPhone");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10059H.putParcelable(state, "airPhone", target.airPhone);
    }
}
