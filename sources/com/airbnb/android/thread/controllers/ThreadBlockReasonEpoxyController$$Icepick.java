package com.airbnb.android.thread.controllers;

import android.os.Bundle;
import com.airbnb.android.thread.controllers.ThreadBlockReasonEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ThreadBlockReasonEpoxyController$$Icepick<T extends ThreadBlockReasonEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2557H = new Helper("com.airbnb.android.thread.controllers.ThreadBlockReasonEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.selectedReason = f2557H.getString(state, "selectedReason");
            target.userFlagDetails = f2557H.getParcelableArrayList(state, "userFlagDetails");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2557H.putString(state, "selectedReason", target.selectedReason);
        f2557H.putParcelableArrayList(state, "userFlagDetails", target.userFlagDetails);
    }
}
