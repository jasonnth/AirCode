package com.airbnb.android.booking.fragments.alipay;

import android.os.Bundle;
import com.airbnb.android.booking.fragments.alipay.AlipayPhoneFragment;
import com.airbnb.android.core.models.AirPhone;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AlipayPhoneFragment$$Icepick<T extends AlipayPhoneFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7439H = new Helper("com.airbnb.android.booking.fragments.alipay.AlipayPhoneFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.airPhone = (AirPhone) f7439H.getParcelable(state, "airPhone");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7439H.putParcelable(state, "airPhone", target.airPhone);
    }
}
