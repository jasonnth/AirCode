package com.airbnb.android.login.p339ui;

import android.os.Bundle;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordConfirmSMSCodeFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment$$Icepick */
public class PhoneForgotPasswordConfirmSMSCodeFragment$$Icepick<T extends PhoneForgotPasswordConfirmSMSCodeFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10057H = new Helper("com.airbnb.android.login.ui.PhoneForgotPasswordConfirmSMSCodeFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.SMSConfirmationCode = f10057H.getString(state, "SMSConfirmationCode");
            target.airPhone = (AirPhone) f10057H.getParcelable(state, "airPhone");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10057H.putString(state, "SMSConfirmationCode", target.SMSConfirmationCode);
        f10057H.putParcelable(state, "airPhone", target.airPhone);
    }
}
