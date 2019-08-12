package com.airbnb.android.lib.china5a;

import android.os.Bundle;
import com.airbnb.android.lib.china5a.FiveAxiomRepoImpl.FlowModel;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class FiveAxiomRepoImpl$FlowModel$$Icepick<T extends FlowModel> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9516H = new Helper("com.airbnb.android.lib.china5a.FiveAxiomRepoImpl$FlowModel$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.curStepIdx = f9516H.getInt(state, "curStepIdx");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9516H.putInt(state, "curStepIdx", target.curStepIdx);
    }
}
