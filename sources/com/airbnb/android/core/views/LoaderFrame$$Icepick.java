package com.airbnb.android.core.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.LoaderFrame;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class LoaderFrame$$Icepick<T extends LoaderFrame> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8504H = new Helper("com.airbnb.android.core.views.LoaderFrame$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.animating = f8504H.getBoolean(state, "animating");
        return super.restore(target, f8504H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8504H.putParent(super.save(target, p));
        f8504H.putBoolean(state, "animating", target.animating);
        return state;
    }
}
