package com.airbnb.android.lib.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.lib.views.SaveButton;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class SaveButton$$Icepick<T extends SaveButton> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9670H = new Helper("com.airbnb.android.lib.views.SaveButton$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.viewState = f9670H.getInt(state, "viewState");
        target.saveButtonEnabled = f9670H.getBoolean(state, "saveButtonEnabled");
        return super.restore(target, f9670H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f9670H.putParent(super.save(target, p));
        f9670H.putInt(state, "viewState", target.viewState);
        f9670H.putBoolean(state, "saveButtonEnabled", target.saveButtonEnabled);
        return state;
    }
}
