package com.airbnb.android.thread.activities;

import android.os.Bundle;
import com.airbnb.android.core.intents.ThreadBlockActivityIntents;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.thread.activities.ThreadBlockActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadBlockActivity$$Icepick<T extends ThreadBlockActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2556H = new Helper("com.airbnb.android.thread.activities.ThreadBlockActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.thread = (Thread) f2556H.getParcelable(state, ThreadBlockActivityIntents.ARG_THREAD);
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2556H.putParcelable(state, ThreadBlockActivityIntents.ARG_THREAD, target.thread);
    }
}
