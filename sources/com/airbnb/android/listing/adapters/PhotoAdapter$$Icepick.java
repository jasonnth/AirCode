package com.airbnb.android.listing.adapters;

import android.os.Bundle;
import com.airbnb.android.listing.adapters.PhotoAdapter;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class PhotoAdapter$$Icepick<T extends PhotoAdapter> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9798H = new Helper("com.airbnb.android.listing.adapters.PhotoAdapter$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.caption = f9798H.getString(state, "caption");
            target.coverPhoto = f9798H.getBoolean(state, "coverPhoto");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9798H.putString(state, "caption", target.caption);
        f9798H.putBoolean(state, "coverPhoto", target.coverPhoto);
    }
}
