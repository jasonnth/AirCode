package com.airbnb.android.thread.fragments;

import android.os.Bundle;
import com.airbnb.android.thread.fragments.ThreadBlockReasonFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadBlockReasonFragment$$Icepick<T extends ThreadBlockReasonFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2560H = new Helper("com.airbnb.android.thread.fragments.ThreadBlockReasonFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedReason = f2560H.getString(state, "selectedReason");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2560H.putString(state, "selectedReason", target.selectedReason);
    }
}
