package com.airbnb.android.core.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.views.UrgencyView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class UrgencyView$$Icepick<T extends UrgencyView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8508H = new Helper("com.airbnb.android.core.views.UrgencyView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.hasAnimated = f8508H.getBoolean(state, "hasAnimated");
        return super.restore(target, f8508H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f8508H.putParent(super.save(target, p));
        f8508H.putBoolean(state, "hasAnimated", target.hasAnimated);
        return state;
    }
}
