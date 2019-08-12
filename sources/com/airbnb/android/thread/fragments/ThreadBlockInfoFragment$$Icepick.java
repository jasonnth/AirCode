package com.airbnb.android.thread.fragments;

import android.os.Bundle;
import com.airbnb.android.thread.fragments.ThreadBlockInfoFragment;
import com.airbnb.android.thread.fragments.ThreadBlockInfoFragment.InfoType;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadBlockInfoFragment$$Icepick<T extends ThreadBlockInfoFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2559H = new Helper("com.airbnb.android.thread.fragments.ThreadBlockInfoFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.infoType = (InfoType) f2559H.getSerializable(state, "infoType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2559H.putSerializable(state, "infoType", target.infoType);
    }
}
