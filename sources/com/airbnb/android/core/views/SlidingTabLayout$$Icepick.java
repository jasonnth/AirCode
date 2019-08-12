package com.airbnb.android.core.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.SlidingTabLayout;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class SlidingTabLayout$$Icepick<T extends SlidingTabLayout> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8506H = new Helper("com.airbnb.android.core.views.SlidingTabLayout$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.oldPosition = f8506H.getInt(state, "oldPosition");
        target.tabSelectedTextColor = f8506H.getBoxedInt(state, "tabSelectedTextColor");
        target.tabUnselectedTextColor = f8506H.getBoxedInt(state, "tabUnselectedTextColor");
        return super.restore(target, f8506H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8506H.putParent(super.save(target, p));
        f8506H.putInt(state, "oldPosition", target.oldPosition);
        f8506H.putBoxedInt(state, "tabSelectedTextColor", target.tabSelectedTextColor);
        f8506H.putBoxedInt(state, "tabUnselectedTextColor", target.tabUnselectedTextColor);
        return state;
    }
}
