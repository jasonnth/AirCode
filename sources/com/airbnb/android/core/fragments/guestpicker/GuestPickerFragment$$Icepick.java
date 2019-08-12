package com.airbnb.android.core.fragments.guestpicker;

import android.os.Bundle;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GuestPickerFragment$$Icepick<T extends GuestPickerFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8455H = new Helper("com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isChinaStyle = f8455H.getBoolean(state, "isChinaStyle");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8455H.putBoolean(state, "isChinaStyle", target.isChinaStyle);
    }
}
