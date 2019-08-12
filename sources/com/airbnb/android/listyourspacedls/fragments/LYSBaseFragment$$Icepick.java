package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import com.airbnb.android.listyourspacedls.fragments.LYSBaseFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LYSBaseFragment$$Icepick<T extends LYSBaseFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9929H = new Helper("com.airbnb.android.listyourspacedls.fragments.LYSBaseFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.userAction = (UserAction) f9929H.getSerializable(state, "userAction");
            target.comingFromBackstack = f9929H.getBoolean(state, "comingFromBackstack");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9929H.putSerializable(state, "userAction", target.userAction);
        f9929H.putBoolean(state, "comingFromBackstack", target.comingFromBackstack);
    }
}
