package com.airbnb.android.cohosting.epoxycontrollers;

import android.os.Bundle;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingListingLevelNotificationEpoxyController;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class CohostingListingLevelNotificationEpoxyController$$Icepick<T extends CohostingListingLevelNotificationEpoxyController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f7798H = new Helper("com.airbnb.android.cohosting.epoxycontrollers.CohostingListingLevelNotificationEpoxyController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.muteType = (MuteType) f7798H.getSerializable(state, "muteType");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f7798H.putSerializable(state, "muteType", target.muteType);
    }
}
