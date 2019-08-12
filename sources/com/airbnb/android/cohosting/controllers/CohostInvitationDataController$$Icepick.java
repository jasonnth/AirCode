package com.airbnb.android.cohosting.controllers;

import android.os.Bundle;
import com.airbnb.android.cohosting.controllers.CohostInvitationDataController;
import com.airbnb.android.core.models.CohostInvitation;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostInvitationDataController$$Icepick<T extends CohostInvitationDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7792H = new Helper("com.airbnb.android.cohosting.controllers.CohostInvitationDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.loading = f7792H.getBoolean(state, "loading");
            target.cohostInvitation = (CohostInvitation) f7792H.getParcelable(state, "cohostInvitation");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7792H.putBoolean(state, "loading", target.loading);
        f7792H.putParcelable(state, "cohostInvitation", target.cohostInvitation);
    }
}
