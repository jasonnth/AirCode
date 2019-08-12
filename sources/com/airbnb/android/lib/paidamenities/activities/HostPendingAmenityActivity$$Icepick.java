package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.lib.paidamenities.activities.HostPendingAmenityActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class HostPendingAmenityActivity$$Icepick<T extends HostPendingAmenityActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9606H = new Helper("com.airbnb.android.lib.paidamenities.activities.HostPendingAmenityActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9606H.getString(state, "confirmationCode");
            target.threadId = f9606H.getLong(state, ThreadDataModel.THREADID);
            target.listingId = f9606H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9606H.putString(state, "confirmationCode", target.confirmationCode);
        f9606H.putLong(state, ThreadDataModel.THREADID, target.threadId);
        f9606H.putLong(state, "listingId", target.listingId);
    }
}
