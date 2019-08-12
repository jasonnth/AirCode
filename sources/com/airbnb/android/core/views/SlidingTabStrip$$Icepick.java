package com.airbnb.android.core.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.SlidingTabStrip;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class SlidingTabStrip$$Icepick<T extends SlidingTabStrip> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8507H = new Helper("com.airbnb.android.core.views.SlidingTabStrip$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.selectedPosition = f8507H.getInt(state, "selectedPosition");
        target.selectionOffset = f8507H.getFloat(state, "selectionOffset");
        return super.restore(target, f8507H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8507H.putParent(super.save(target, p));
        f8507H.putInt(state, "selectedPosition", target.selectedPosition);
        f8507H.putFloat(state, "selectionOffset", target.selectionOffset);
        return state;
    }
}
