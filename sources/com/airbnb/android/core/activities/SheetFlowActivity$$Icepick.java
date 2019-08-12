package com.airbnb.android.core.activities;

import android.os.Bundle;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class SheetFlowActivity$$Icepick<T extends SheetFlowActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8421H = new Helper("com.airbnb.android.core.activities.SheetFlowActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.currentTheme = (SheetTheme) f8421H.getSerializable(state, "currentTheme");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8421H.putSerializable(state, "currentTheme", target.currentTheme);
    }
}
