package com.airbnb.android.lib.paidamenities.fragments.purchase;

import android.os.Bundle;
import com.airbnb.android.core.messaging.p007db.ThreadDataModel;
import com.airbnb.android.lib.paidamenities.fragments.purchase.BasePurchaseAmenityFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class BasePurchaseAmenityFragment$$Icepick<T extends BasePurchaseAmenityFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9621H = new Helper("com.airbnb.android.lib.paidamenities.fragments.purchase.BasePurchaseAmenityFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.confirmationCode = f9621H.getString(state, "confirmationCode");
            target.threadId = f9621H.getLong(state, ThreadDataModel.THREADID);
            target.listingId = f9621H.getLong(state, "listingId");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9621H.putString(state, "confirmationCode", target.confirmationCode);
        f9621H.putLong(state, ThreadDataModel.THREADID, target.threadId);
        f9621H.putLong(state, "listingId", target.listingId);
    }
}
