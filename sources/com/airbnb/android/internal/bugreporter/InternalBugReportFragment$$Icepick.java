package com.airbnb.android.internal.bugreporter;

import android.os.Bundle;
import com.airbnb.android.internal.bugreporter.InternalBugReportFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class InternalBugReportFragment$$Icepick<T extends InternalBugReportFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8209H = new Helper("com.airbnb.android.internal.bugreporter.InternalBugReportFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.logFiles = f8209H.getStringArrayList(state, "logFiles");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8209H.putStringArrayList(state, "logFiles", target.logFiles);
    }
}
