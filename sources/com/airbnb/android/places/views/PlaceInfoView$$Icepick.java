package com.airbnb.android.places.views;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.places.views.PlaceInfoView;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.View;
import java.util.HashMap;
import java.util.Map;

public class PlaceInfoView$$Icepick<T extends PlaceInfoView> extends View<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f11170H = new Helper("com.airbnb.android.places.views.PlaceInfoView$$Icepick.", BUNDLERS);

    public Parcelable restore(T target, Parcelable p) {
        Bundle state = (Bundle) p;
        target.airmoji = f11170H.getString(state, "airmoji");
        return super.restore(target, f11170H.getParent(state));
    }

    public Parcelable save(T target, Parcelable p) {
        Bundle state = f11170H.putParent(super.save(target, p));
        f11170H.putString(state, "airmoji", target.airmoji);
        return state;
    }
}
