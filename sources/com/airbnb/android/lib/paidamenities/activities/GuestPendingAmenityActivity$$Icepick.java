package com.airbnb.android.lib.paidamenities.activities;

import android.os.Bundle;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.lib.paidamenities.activities.GuestPendingAmenityActivity;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class GuestPendingAmenityActivity$$Icepick<T extends GuestPendingAmenityActivity> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9604H = new Helper("com.airbnb.android.lib.paidamenities.activities.GuestPendingAmenityActivity$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9604H.getString(state, "confirmationCode");
            target.threadId = f9604H.getLong(state, ThreadDataModel.THREADID);
            target.listingId = f9604H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9604H.putString(state, "confirmationCode", target.confirmationCode);
        f9604H.putLong(state, ThreadDataModel.THREADID, target.threadId);
        f9604H.putLong(state, "listingId", target.listingId);
    }
}
