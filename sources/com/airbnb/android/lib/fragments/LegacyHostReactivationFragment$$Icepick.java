package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import com.airbnb.android.lib.fragments.LegacyHostReactivationFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LegacyHostReactivationFragment$$Icepick<T extends LegacyHostReactivationFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9537H = new Helper("com.airbnb.android.lib.fragments.LegacyHostReactivationFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.bodyText = f9537H.getString(state, "bodyText");
            target.helpLinkTitle = f9537H.getString(state, "helpLinkTitle");
            target.helpLinkUrl = f9537H.getString(state, "helpLinkUrl");
            target.canReactivate = f9537H.getBoolean(state, "canReactivate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9537H.putString(state, "bodyText", target.bodyText);
        f9537H.putString(state, "helpLinkTitle", target.helpLinkTitle);
        f9537H.putString(state, "helpLinkUrl", target.helpLinkUrl);
        f9537H.putBoolean(state, "canReactivate", target.canReactivate);
    }
}
