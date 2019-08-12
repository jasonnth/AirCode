package com.airbnb.p027n2.components;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.p027n2.components.ToggleActionRow;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.airbnb.n2.components.ToggleActionRow$$Icepick */
public class ToggleActionRow$$Icepick<T extends ToggleActionRow> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2703H = new Helper("com.airbnb.n2.components.ToggleActionRow$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.checked = f2703H.getBoolean(state, "checked");
        return super.restore(target, f2703H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f2703H.putParent(super.save(target, p));
        f2703H.putBoolean(state, "checked", target.checked);
        return state;
    }
}
