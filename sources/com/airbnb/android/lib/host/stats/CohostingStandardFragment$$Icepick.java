package com.airbnb.android.lib.host.stats;

import android.os.Bundle;
import com.airbnb.android.core.models.CohostingStandard;
import com.airbnb.android.lib.host.stats.CohostingStandardFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingStandardFragment$$Icepick<T extends CohostingStandardFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9593H = new Helper("com.airbnb.android.lib.host.stats.CohostingStandardFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.standard = (CohostingStandard) f9593H.getParcelable(state, "standard");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9593H.putParcelable(state, "standard", target.standard);
    }
}
