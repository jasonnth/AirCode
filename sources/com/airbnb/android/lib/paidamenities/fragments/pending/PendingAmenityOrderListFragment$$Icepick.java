package com.airbnb.android.lib.paidamenities.fragments.pending;

import android.os.Bundle;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import java.util.HashMap;
import java.util.Map;

public class PendingAmenityOrderListFragment$$Icepick<T extends PendingAmenityOrderListFragment> extends BasePendingAmenityFragment$$Icepick<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f9620H = new Helper("com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.paidAmenityOrders = f9620H.getParcelableArrayList(state, "paidAmenityOrders");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f9620H.putParcelableArrayList(state, "paidAmenityOrders", target.paidAmenityOrders);
    }
}
