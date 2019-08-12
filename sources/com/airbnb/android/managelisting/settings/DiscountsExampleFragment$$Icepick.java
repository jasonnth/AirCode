package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.core.models.LongTermPricingExample;
import com.airbnb.android.managelisting.settings.DiscountsExampleFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class DiscountsExampleFragment$$Icepick<T extends DiscountsExampleFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10173H = new Helper("com.airbnb.android.managelisting.settings.DiscountsExampleFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.example = (LongTermPricingExample) f10173H.getParcelable(state, "example");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10173H.putParcelable(state, "example", target.example);
    }
}
