package com.airbnb.android.nestedlistings.fragments;

import android.os.Bundle;
import com.airbnb.android.nestedlistings.fragments.NestedListingsChooseChildrenFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class NestedListingsChooseChildrenFragment$$Icepick<T extends NestedListingsChooseChildrenFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f10528H = new Helper("com.airbnb.android.nestedlistings.fragments.NestedListingsChooseChildrenFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.showEntireHomeWarningSnackbar = f10528H.getBoolean(state, "showEntireHomeWarningSnackbar");
            target.showClearChildrenConfirmationSnackbar = f10528H.getBoolean(state, "showClearChildrenConfirmationSnackbar");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f10528H.putBoolean(state, "showEntireHomeWarningSnackbar", target.showEntireHomeWarningSnackbar);
        f10528H.putBoolean(state, "showClearChildrenConfirmationSnackbar", target.showClearChildrenConfirmationSnackbar);
    }
}
