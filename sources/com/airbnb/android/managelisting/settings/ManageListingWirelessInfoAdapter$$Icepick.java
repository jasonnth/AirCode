package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import com.airbnb.android.managelisting.settings.ManageListingWirelessInfoAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ManageListingWirelessInfoAdapter$$Icepick<T extends ManageListingWirelessInfoAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10192H = new Helper("com.airbnb.android.managelisting.settings.ManageListingWirelessInfoAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.wifiSsid = f10192H.getString(state, "wifiSsid");
            target.wifiPassword = f10192H.getString(state, "wifiPassword");
            target.enabled = f10192H.getBoolean(state, "enabled");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10192H.putString(state, "wifiSsid", target.wifiSsid);
        f10192H.putString(state, "wifiPassword", target.wifiPassword);
        f10192H.putBoolean(state, "enabled", target.enabled);
    }
}
