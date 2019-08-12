package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.lib.cancellation.host.CancellationUncomfortableBehaviorFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CancellationUncomfortableBehaviorFragment$$Icepick<T extends CancellationUncomfortableBehaviorFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9508H = new Helper("com.airbnb.android.lib.cancellation.host.CancellationUncomfortableBehaviorFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.message = f9508H.getString(state, "message");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9508H.putString(state, "message", target.message);
    }
}
