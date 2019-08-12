package com.airbnb.android.core.fragments;

import android.os.Bundle;
import com.airbnb.android.core.fragments.LocalAttractionsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class LocalAttractionsFragment$$Icepick<T extends LocalAttractionsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8451H = new Helper("com.airbnb.android.core.fragments.LocalAttractionsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.mLoopingListingPosition = f8451H.getInt(state, "mLoopingListingPosition");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8451H.putInt(state, "mLoopingListingPosition", target.mLoopingListingPosition);
    }
}
