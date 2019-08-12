package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.identity.AirbnbTakeSelfieFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AirbnbTakeSelfieFragment$$Icepick<T extends AirbnbTakeSelfieFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9177H = new Helper("com.airbnb.android.identity.AirbnbTakeSelfieFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.cameraID = f9177H.getInt(state, "cameraID");
            target.hasFrontCamera = f9177H.getBoolean(state, "hasFrontCamera");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9177H.putInt(state, "cameraID", target.cameraID);
        f9177H.putBoolean(state, "hasFrontCamera", target.hasFrontCamera);
    }
}
