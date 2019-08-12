package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.cancellation.host.HostCancellationReasonsFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostCancellationReasonsFragment$$Icepick<T extends HostCancellationReasonsFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9510H = new Helper("com.airbnb.android.lib.cancellation.host.HostCancellationReasonsFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.checkInDate = (AirDate) f9510H.getParcelable(state, "checkInDate");
            target.checkOutDate = (AirDate) f9510H.getParcelable(state, "checkOutDate");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9510H.putParcelable(state, "checkInDate", target.checkInDate);
        f9510H.putParcelable(state, "checkOutDate", target.checkOutDate);
    }
}
