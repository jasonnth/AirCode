package com.airbnb.android.cohosting.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.cohosting.epoxycontrollers.AcceptCohostInvitationEpoxyController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class AcceptCohostInvitationEpoxyController$$Icepick<T extends AcceptCohostInvitationEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7796H = new Helper("com.airbnb.android.cohosting.epoxycontrollers.AcceptCohostInvitationEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.isLoading = f7796H.getBoolean(state, "isLoading");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7796H.putBoolean(state, "isLoading", target.isLoading);
    }
}
